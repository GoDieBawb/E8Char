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
public class BasicResponse {
    
    public String responseType;
    public String outcome;
    
    public BasicResponse() {}
    public BasicResponse(String outcome) {this.outcome = outcome;}
    
}
