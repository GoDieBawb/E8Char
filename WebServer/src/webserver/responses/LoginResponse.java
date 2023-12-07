/*
 * Created by Bawb
 * 
 * 
 */
package webserver.responses;

/**
 *
 * @author MeanC
 */
public class LoginResponse extends BasicResponse {
    
    public String accessToken;
    
    public LoginResponse() {
        responseType = "login";
        
    }
    
}
