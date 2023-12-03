/*
 * Created by Bawb
 * 
 * 
 */
package webserver.authentication;

import java.util.HashMap;

/**
 *
 * @author MeanC
 */
public class Authenticator {
    
    HashMap<String, User> tokenMap;
    
    public Authenticator() {
        tokenMap = new HashMap<>();
    }
    
    public HashMap<String, User> getTokenMap() {
        return tokenMap;
    }
    
    //returns the user name for that token if valid
    public String Authenticate(String token) {
        
        if (tokenMap.containsKey(token)) {
            Long lastAct = tokenMap.get(token).lastAct;
            Long curTime = System.currentTimeMillis();
            
            if ((curTime-lastAct)/1000 > 60) {
                System.out.println("TOKEN EXPIRED: " + (curTime-lastAct)/1000 +" seconds.");
                return null;
            }
            
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
    
    //TODO: Check Username and Password Here
    private boolean verify(String userName, String password) {
        return true;
    }
    
}
