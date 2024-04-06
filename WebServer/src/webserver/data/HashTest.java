/*
 * Created by Bawb
 * 
 * 
 */
package webserver.data;

import at.favre.lib.crypto.bcrypt.BCrypt;


/**
 *
 * @author MeanC
 */
public class HashTest {
    
    public void hashPassword(String password) {
        String hash = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);
        System.out.println("HASH: " + hash);
        System.out.println("RESULT: " + result.verified);
    }
    
}
