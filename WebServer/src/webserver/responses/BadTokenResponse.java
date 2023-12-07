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
public class BadTokenResponse extends BasicResponse {
    
    public BadTokenResponse() {
        outcome      = "failed";
        responseType = "badtoken";
    }
    
}
