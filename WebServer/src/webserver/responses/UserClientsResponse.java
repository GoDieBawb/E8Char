/*
 * Created by Bawb
 * 
 * 
 */
package webserver.responses;

import java.util.ArrayList;
import webserver.SQLUtil;

/**
 *
 * @author MeanC
 */
public class UserClientsResponse extends BasicResponse {
    
    public class Client {
        public String clientId;
        public String lastName;
        public String firstName;
        public String DOB;
    }
    
    int userId;
    ArrayList<Client> clients;
    
    public UserClientsResponse(int userId) {
        this.userId  = userId;
        outcome      = "Success";
        responseType = "UserClientsResponse";
        getClients();
    }
    
    private String getSQLResponse() {
        
        String query = "SELECT * FROM Client WHERE EnteredBy  = '" + userId + "'";
        
        SQLUtil sql = new SQLUtil();
        String response = sql.queryDatabase(query);
        
        return response;
    }
    
    private ArrayList<Client> getClients() {
        clients = new ArrayList<>();
        String response = getSQLResponse();
        
        response = response.replace("[", "");
        String[] clientStrings = response.split("]");
        
        // if there's no results, then return nothing.
        if (response.equals(""))
            return null;

        for (String s : clientStrings) {
            
            Client   c            = new Client();           
            String[] fieldStrings = s.split(", ");
            clients.add(c);
            
            for (int i = 0; i < fieldStrings.length; i++) {
                String fs    = fieldStrings[i];
                String value = fs.split(":")[1];
                
                if (fs.contains("Id:"))
                    c.clientId  = value;
                else if (fs.contains("FirstName:"))
                    c.firstName = value;
                else if (fs.contains("LastName:"))
                    c.lastName  = value;
                else if (fs.contains("DOB:"))
                    c.DOB       = value; 
            }
        }

        return clients; 
    }
}
