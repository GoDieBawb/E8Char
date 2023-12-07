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
public class UnknownPostResponse extends BasicResponse {
    
    public UnknownPostResponse() {
        outcome      = "failed";
        responseType = "UnknownPostResponse";
    }
    
}
