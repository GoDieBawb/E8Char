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
import webserver.responses.LoginResponse;
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
            System.out.println("Checking Login: " + l.username + " " + l.password);
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
            System.out.println("Checking Token: " + b.accessToken);
            String user = authenticator.Authenticate(b.accessToken);
            
            //If user is null then the Access Token is Bad
            if (user == null) {
                System.out.println("BAD TOKEN");
                return new BadTokenResponse();
            }
            System.out.println("Verified!");
            
        }
        
        //Once Token Verified Determine Which Post Type
        switch (b.postType) {
            case "submitDemographic":
                System.out.println("Submitting Demographic");
                try {
                    SubmitDemographicPost sdp = g.fromJson(json, SubmitDemographicPost.class);
                    sdp.enteredby   = authenticator.getUserIdByToken(sdp.accessToken);
                    sdp.entereddate = LocalDate.now().toString();
                    sdp.publish();                    
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Submitted!");
                BasicResponse br = new BasicResponse();
                br.outcome = "Success";
                return br;
            default:
                System.out.println("ERROR: Unregistered Post Type: " + b.postType);
                return new UnknownPostResponse();
        }
        
    }
    
}
