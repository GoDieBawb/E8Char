/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver.posts;

import com.google.gson.Gson;
import webserver.authentication.Authenticator;

/**
 *
 * @author MeanC
 */
public class PostHandler {
    
    private final Authenticator authenticator;
    
    public PostHandler() {
        authenticator = new Authenticator();
    }
    
    public String handle(String json) {
        
        Gson      g   = new Gson();
        BasicPost b   = g.fromJson(json, BasicPost.class);
        
        //Handle Login
        if (b.postType.equals("Login")) {
            LoginPost l = g.fromJson(json, LoginPost.class);
            System.out.println("Checking Login: " + l.username + " " + l.password);
            String token = authenticator.Authenticate(l.username, l.password);
            return token;
        }  
        
        //Verify Token
        else {
            System.out.println("Checking Token: " + b.accessToken);
            String user = authenticator.Authenticate(b.accessToken);
            if (user == null) {
                System.out.println("BAD TOKEN");
                return user;
            }
        }
        
        switch (b.postType) {
            default:
                System.out.println("ERROR: Unregistered Post Type: " + b.postType);
                return null;
        }
        
    }
    
}
