/*
 * Created by Bawb
 * 
 * 
 */
package webserver.authentication;

import java.util.HashMap;
import webserver.SQLUtil;

/**
 *
 * @author MeanC
 */
public class Authenticator {
    
    HashMap<String, User> tokenMap; //Maps Access Token Strings to User Objects
    
    public Authenticator() {
        tokenMap = new HashMap<>();
    }
    
    public HashMap<String, User> getTokenMap() {
        return tokenMap;
    }
    
    //TODO Multiple Logins Need to Return the same token
    
    //returns the user name for that token if valid
    public String Authenticate(String token) {
        
        if (tokenMap.containsKey(token)) {
            Long lastAct = tokenMap.get(token).lastAct;
            Long curTime = System.currentTimeMillis();
            
            if ((curTime-lastAct)/1000 > 60) {
                System.out.println("TOKEN EXPIRED: " + (curTime-lastAct)/1000 +" seconds.");
                tokenMap.remove(token);
                return null;
            }
            tokenMap.get(token).lastAct = System.currentTimeMillis(); //Rest Timer
            return tokenMap.get(token).userName;
        }
        else return null;
    }
    
    //Returns a token if verified, null if not
    public String Authenticate(String userName, String password) {
        
        if (verify(userName, password)) {
            String token  = new TokenGenerator().generateToken();
            System.out.println("User: " + userName + " Authenticated Token: " + token);
            User u        = new User();
            u.userName    = userName;
            u.accessToken = token;
            u.lastAct     = System.currentTimeMillis();
            tokenMap.put(token, u);
            return token;
        }
        else {
            System.out.println("Authentication Failed For User: " + userName);
            return null;
        }
        
    }
    
    //Checks Username and Password
    private boolean verify(String userName, String password) {
        SQLUtil u        = new SQLUtil();
        String response  = u.queryDatabase("SELECT Password FROM Staff WHERE Username = '" + userName+"'");
        
        if (response.equals("")) return false;
        
        response         = response.substring(1, response.length() - 1); //Remove first and last character
        String pass      = response.split(": ")[1]; //Split at ": " to get requested field
        System.out.println("Pass: " + pass);
        return password.equals(pass);
    }
    
}
