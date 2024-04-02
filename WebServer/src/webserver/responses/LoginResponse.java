/*
 * Created by Bawb
 * 
 * 
 */
package webserver.responses;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

/**
 *
 * @author MeanC
 */
public class LoginResponse extends BasicResponse {
    
    public String accessToken;
    public String clinicianName;
    public String clinicianId;
    
    public LoginResponse(String username) {
        responseType = "login";

        DataResponse dr = WebServer.dbHandler.secureGet("select Id, FirstName, LastName from Staff where username = ?", new Object[] { username });
        
        if (dr.size() == 0) {
            outcome = "failed";
            responseType = "LoginResponse";
            return;
        }

        clinicianName = (String)dr.getValueAtRowAndColumn(1, "FirstName") + " " + (String)dr.getValueAtRowAndColumn(1, "LastName");
        clinicianId = Integer.toString((Integer)dr.getValueAtRowAndColumn(1, "Id"));
    }
    
}
