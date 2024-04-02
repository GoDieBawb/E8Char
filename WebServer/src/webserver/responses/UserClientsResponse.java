/*
 * Created by Bawb
 * 
 * 
 */
package webserver.responses;

import java.util.ArrayList;
import java.time.LocalDateTime;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

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
        outcome      = "success";
        responseType = "UserClientsResponse";
        getClients();
    }
    
    private ArrayList<Client> getClients() {
        clients = new ArrayList<>();

        DataResponse dr = WebServer.dbHandler.secureGet("SELECT * FROM Client WHERE EnteredBy = ?", new Object[] { userId });
        
        // if there's no results, then return nothing.
        if (dr.size() == 0)
            return null;

        for (int i = 1; i <= dr.size(); i++) {
            Client c = new Client();   

            c.clientId = Integer.toString((Integer)dr.getValueAtRowAndColumn(i, "Id"));
            c.lastName = (String)dr.getValueAtRowAndColumn(i, "LastName");
            c.firstName = (String)dr.getValueAtRowAndColumn(i, "FirstName");
            c.DOB = (String)dr.getValueAtRowAndColumn(i, "DOB");

            clients.add(c);
        }

        return clients; 
    }
}
