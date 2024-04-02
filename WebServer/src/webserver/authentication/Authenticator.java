/*
 * Created by Bawb
 * 
 * 
 */
package webserver.authentication;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.util.HashMap;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

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
            
            //15 minute time out for token
            //if ((curTime-lastAct)/1000 > 60*15) {
            if ((curTime-lastAct)/1000 > 60*3600) {
                //System.out.println("TOKEN EXPIRED: " + (curTime-lastAct)/1000 +" seconds.");
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
            //System.out.println("User: " + userName + " Authenticated Token: " + token);           
            User u        = new User();
            u.userName    = userName;
            u.userId      = getUserId(userName);
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
        DataResponse response = WebServer.dbHandler.secureGet("SELECT Password FROM Staff WHERE Username = ?", new Object[] { userName });

        // Clinician doesn't exist.
        if (response.size() == 0) 
            return false;
        
        String passHash = (String)response.getValueAtRowAndColumn(1, "Password");
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), passHash);

        return result.verified;
    }
    
    //Can Probably be moved to verify
    private int getUserId(String userName) {
        DataResponse response = WebServer.dbHandler.secureGet("SELECT Id FROM Staff WHERE Username = ?", new Object[] { userName });
        return (Integer)response.getValueAtRowAndColumn(1, "Id");
    }
    
    public int getUserIdByToken(String accessToken) {
        if (tokenMap.containsKey(accessToken)) {
            return tokenMap.get(accessToken).userId;
        }
        return -1;
    }
    
    public String getUserNameByToken(String accessToken) {
        if (tokenMap.containsKey(accessToken)) {
            return tokenMap.get(accessToken).userName;
        }
        return null;
    }
    
}
