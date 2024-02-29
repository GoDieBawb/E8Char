/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver.posts;

import com.google.gson.Gson;
import java.time.LocalDate;
import webserver.authentication.Authenticator;
import webserver.responses.BadTokenResponse;
import webserver.responses.BasicResponse;
import webserver.responses.ClientServicesResponse;
import webserver.responses.LoginResponse;
import webserver.responses.UserClientsResponse;
import webserver.responses.UnknownPostResponse;

/**
 *
 * @author MeanC
 */
public class PostHandler {
    
    private final Authenticator authenticator;
    
    public PostHandler() {
        authenticator = new Authenticator();
    }
    
    public BasicResponse handle(String json) {
        
        Gson      g   = new Gson();
        BasicPost b   = g.fromJson(json, BasicPost.class);
        
        //Handle Login
        if (b.postType.equals("Login")) {
            LoginPost l = g.fromJson(json, LoginPost.class);
            //System.out.println("Checking Login: " + l.username + " " + l.password);
            String token    = authenticator.Authenticate(l.username, l.password);
            LoginResponse r = new LoginResponse();
            //If Token is Null Login Failed
            if (token == null) {
                r.outcome       = "failed";
                r.accessToken   = "none";
                return r;
            }
            
            r.outcome       = "success";
            r.accessToken   = token;
            return r;
        }  
        
        //Verify Token
        else {
            //System.out.println("Checking Token: " + b.accessToken);
            String user = authenticator.Authenticate(b.accessToken);
            
            //If user is null then the Access Token is Bad
            if (user == null) {
                System.out.println("BAD TOKEN");
                return new BadTokenResponse();
            }
            // System.out.println("Verified!");
            
        }
        
        //Once Token Verified Determine Which Post Type
        switch (b.postType) {
            
            case "submitDemographic":
                SubmitDemographicPost sdp = g.fromJson(json, SubmitDemographicPost.class);
                sdp.enteredby   = authenticator.getUserIdByToken(sdp.accessToken);
                sdp.entereddate = LocalDate.now().toString();
                sdp.publish();                    
                return new BasicResponse("Success");
                
            case "submitMentalHealthEvaluation":
                SubmitMHEvalPost smhe = g.fromJson(json, SubmitMHEvalPost.class);
                smhe.enteredby   = authenticator.getUserIdByToken(smhe.accessToken);
                smhe.entereddate = LocalDate.now().toString();
                smhe.publish();
                return new BasicResponse("Success");
               
            case "submitPhysicalEvaluation":
                SubmitPhysicalPost smpe = g.fromJson(json, SubmitPhysicalPost.class);
                smpe.enteredby   = authenticator.getUserIdByToken(smpe.accessToken);
                smpe.entereddate = LocalDate.now().toString();
                smpe.publish();
                return new BasicResponse("Success");
                
            case "requestUserClients":
                int userId                          = authenticator.getUserIdByToken(b.accessToken);
                UserClientsResponse clientsResponse = new UserClientsResponse(userId);
                //System.out.println("GSON CLIENTS: " + g.toJson(clientsResponse));
                return clientsResponse;
              
            case "requestClientServices":
                int selectedClient         = g.fromJson(json, RequestClientServicesPost.class).clientId;
                int requestingUser         = authenticator.getUserIdByToken(b.accessToken);
                ClientServicesResponse csr = new ClientServicesResponse(requestingUser,selectedClient);
                return csr;
                
            default:
                return new UnknownPostResponse();
                
        }
        
    }
    
}
