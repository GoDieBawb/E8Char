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
            Client c = new Client();           
            String[] fieldStrings = s.split(", ");
            
            for (int i = 0; i < fieldStrings.length; i++) {
                String fieldName = fieldStrings[i].split(":")[0];
                String fieldValue = fieldStrings[i].split(":")[1];

                if (fieldName.equals("Id"))
                    c.clientId = fieldValue;
                else if (fieldName.equals("FirstName"))
                    c.firstName = fieldValue;
                else if (fieldName.equals("LastName"))
                    c.lastName = fieldValue;
                else if (fieldName.equals("DOB"))
                    c.DOB = fieldValue; 
            }

            clients.add(c);
        }

        return clients; 
    }
}
