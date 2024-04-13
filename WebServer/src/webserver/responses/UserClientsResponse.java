/*
 * Created by Bawb
 * 
 * 
 */
package webserver.responses;

import java.util.ArrayList;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

/**
 *
 * @author MeanC
 */
public class UserClientsResponse extends BasicResponse {
    
    public class Client {
        public String patientId;
        public String lastName;
        public String firstName;
        public String dob;
    }
    
    int userId;
    ArrayList<Client> patients;
    
    public UserClientsResponse(int userId) {
        this.userId  = userId;
        outcome      = "success";
        responseType = "UserClientsResponse";
        getClients();
    }
    
    private ArrayList<Client> getClients() {
        patients = new ArrayList<>();

        DataResponse dr = WebServer.dbHandler.secureGet("SELECT * FROM Patients WHERE enteredBy = ?", new Object[] { userId });
        
        // if there's no results, then return nothing.
        if (dr.size() == 0)
            return null;

        for (int i = 1; i <= dr.size(); i++) {
            Client c = new Client();   

            c.patientId = Integer.toString((Integer)dr.getValueAtRowAndColumn(i, "id"));
            c.lastName = (String)dr.getValueAtRowAndColumn(i, "lastName");
            c.firstName = (String)dr.getValueAtRowAndColumn(i, "firstName");
            c.dob = (String)dr.getValueAtRowAndColumn(i, "dob");

            patients.add(c);
        }

        return patients; 
    }
}
