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
        outcome = "success";
        responseType = "login";

        DataResponse dr = WebServer.dbHandler.secureGet("select id, firstName, lastName from Staff where username = ?", new Object[] { username });
        
        if (dr.size() == 0) {
            outcome = "failed";
            return;
        }

        clinicianName = (String)dr.getValueAtRowAndColumn(1, "firstName") + " " + (String)dr.getValueAtRowAndColumn(1, "lastName");
        clinicianId = Integer.toString((Integer)dr.getValueAtRowAndColumn(1, "id"));
    }
}
