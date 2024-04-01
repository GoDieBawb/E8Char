/*
 * Created by Bawb
 * 
 * 
 */
package webserver.posts;

import java.lang.reflect.Field;

import webserver.SQLUtil;

/**
 *
 * @author MeanC
 */
public class ServicePost extends BasicPost {
    public int         clientId;
    public int         enteredby;
    public String      entereddate;    
    public int         serviceCode;
    
    protected String generateService() {
        SQLUtil sql         = new SQLUtil();
        String serviceQuery = "INSERT INTO Service (ServiceCode, ClientId, ServiceDate, EnteredBy)"
            + "VALUES ("
                + "'" + serviceCode + "', "
                + "'" + clientId    + "', "
                + "'" + entereddate + "', "
                + "'" + enteredby   + "')";

        sql.queryDatabase(serviceQuery);
        
        String idQuery   = "SELECT MAX(id) FROM Service;";
        String s         = sql.queryDatabase(idQuery);
        String serviceId = s.split(":")[1].replace("]", "");
        return serviceId;
    }
    
    public void debug()
    {
        Field[] members = this.getClass().getDeclaredFields();

        for (Field member: members)
        {
            try {
                System.out.println("(" + member.getType().getSimpleName() + ") " + member.getName() + ": " + member.get(this));
            }
            catch(Exception ex){}
        }
    }
}
