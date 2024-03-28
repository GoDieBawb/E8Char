/*
 * Created by Bawb
 * 
 * 
 */
package webserver.responses;

import webserver.SQLUtil;

/**
 *
 * @author MeanC
 */
public class LoginResponse extends BasicResponse {
    
    public String accessToken;
    public String physicianName;
    
    public LoginResponse(String username) {
        responseType = "login";

        SQLUtil u = new SQLUtil();
        String response = u.queryDatabase("select FirstName, LastName from Staff where username = '" + username + "'");
        response = response.replaceAll("[\\[\\],:]|FirstName|LastName", "");

        physicianName = response;
    }
    
}
