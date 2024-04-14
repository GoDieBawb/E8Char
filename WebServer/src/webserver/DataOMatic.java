/*  Hector: Make sure these files:

    "commons-pool2-2.12.0.jar",
    "commons-dbcp2-2.12.0.jar",
    "commons-logging-1.3.1.jar" and
    "mysql-connector-j-8.1.0.jar"

    ..are in the lib directory and classpath so 
    that the drivers and required features are properly 
    loaded and used.
*/

package webserver;

import util.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.sql.ResultSetMetaData;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**A class that allows data posting and retrieval to and from the database 
 * in a secure and concurrent fashion. 
**/
public final class DataOMatic {

    /** A class that encapsulates the result set of a query. **/
    public class DataResponse {
        private ArrayList<HashMap<String, Object>> table = new ArrayList<HashMap<String, Object>>(0);
        
        /** Retrieves a value at a given row and column with the given column name.
         *  Row number starts at 1. **/
        public Object getValueAtRowAndColumn(int row, String columnName) {
            Object value = table.get(row-1).get(columnName);

            if (value == null) {
                System.err.println("Data-o-Matic: Imminent null exception - no such column name '" + columnName + "'.");
                new Throwable().printStackTrace();
            }

            return value;
        }

        public HashMap<String, Object> getRowAt(int row) {
            return table.get(row-1);
        }

        /** Returns the number of rows contained in this response. **/
        public int size() {
            return table.size();
        }

        // Not to be used anywhere except by the DataOMatic.
        protected void putRow(HashMap<String, Object> newRow) {
            table.add(newRow);
        }
    }

    // Whether to use a remote (true) or local (false) database.
    private static final boolean IS_PRODUCTION = true;

    public DataOMatic() {
        // Get credentials.
        String dbInfo = null;
     
        final ArrayList<String> lines = new FileReader().getLines("pass.txt");

        for (String line : lines) {
            if (line.contains("db")) {
                dbInfo = line;
                break;
            }
        }

        if (dbInfo == null) {
            System.err.println("Data-o-Matic: Aborted initialization - invalid credentials!");
            return;
        }

        dbInfo = dbInfo.split(":")[1];
        final String user = dbInfo.split(",")[0].replace(" ", "");
        final String pass = dbInfo.split(",")[1].replace(" ", "");
        final String dbIP = IS_PRODUCTION ? "149.56.101.29:3306" : "127.0.0.1:3306";
        final String dbName = "Test";
        
        // Build a pool that can hold db connections. The connection factory needs a db URL, a db username and a db password.
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:mysql://" + dbIP + "/" + dbName,  user, pass);
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
        GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);

        poolableConnectionFactory.setPool(connectionPool);
        poolableConnectionFactory.setValidationQuery("SELECT 1;");

        connectionPool.setTestOnBorrow(false);
        connectionPool.setTestWhileIdle(true);
        connectionPool.setLifo(false);
        connectionPool.setDurationBetweenEvictionRuns(Duration.ofSeconds(6));

        PoolingDriver driver;

        try {
            Class.forName("org.apache.commons.dbcp2.PoolingDriver");
            driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(".. are the required JAR files present?");
            return;
        }

        // Register the pool (with a custom name) to the JDBC driver so that connections
        // running through the driver use this pool.
        driver.registerPool("mainpool", connectionPool);
    }

    /** Executes a prepared query intended for reading data.
     * @param preparedQuery an SQL query string that contains '?' as parameterized fields.
     * @param values a list of generic values to fill the fields with, and each must correspond exactly to each '?' field as before.
     * This parameter can be NULL if it's not needed.
     * @return  a numerically-indexed array of dictionaries, where a key is a column name and its value is the 
     *          pertaining column's value.
    **/
    public DataResponse secureGet(String preparedQuery, Object[] values) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;

        // This might slow things down, but whatever.
        DataResponse dr = new DataResponse();

        try {
            conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:mainpool");
            stmt = conn.prepareStatement(preparedQuery);

            if (values != null) {
                // Populate the parameterized fields.
                for (int i = 0; i < values.length; i++)
                    stmt.setObject(i+1, values[i]);
            }

            stmt.execute();
            rset = stmt.getResultSet();

            final ResultSetMetaData setData = rset.getMetaData();

            while (rset.next()) {
                /* Iterate through each row in the set and add each row to the data response object.
                Order is uncertain. */
                HashMap<String, Object> newRow = new HashMap<>();

                // Populate columns and their values.
                for (int i = 1; i <= setData.getColumnCount(); i++)
                    newRow.put(setData.getColumnLabel(i), rset.getObject(i));

                dr.putRow(newRow);
            }
        }
        catch(Exception ex) {
            System.err.println("Data-o-Matic: Couldn't get data - " + ex.getMessage());
            System.err.println(".. get query was: " + preparedQuery);
        }
        finally {
            // Free the resources and release the connection back to the pool.
            try { if (rset != null) rset.close(); } catch(SQLException e) {}
            try { if (stmt != null) stmt.close(); } catch(SQLException e) {}
            try { if (conn != null) conn.close(); } catch(SQLException e) {}
        }

        return dr;
    }
    
    /** Executes a prepared query intended for inserting, updating and deleting data. 
     * @param preparedQuery an SQL query string that contains '?' as parameterized fields.
     * @param values a list of generic values to fill the fields with, and each must correspond exactly to each '?' field as before.
     * This parameter can be NULL if it's not needed.
    **/
    public void securePost(String preparedQuery, Object[] values) {
        Connection conn = null;
        PreparedStatement stmt = null;
    
        try {
            conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:mainpool");

            stmt = conn.prepareStatement(preparedQuery);
            
            if (values != null) {
                // Populate the parameterized fields.
                for (int i = 0; i < values.length; i++)
                    stmt.setObject(i+1, values[i]);
            }

            stmt.execute();
        }
        catch(Exception ex) {
            System.err.println("Data-o-Matic: Couldn't post query - " + ex.getMessage());
            System.err.println(".. post query was: " + preparedQuery);
            ex.printStackTrace();
        }
        finally {
            try { if (stmt != null) stmt.close(); } catch(SQLException e) {}
            try { if (conn != null) conn.close(); } catch(SQLException e) {}
        }
    }
}
