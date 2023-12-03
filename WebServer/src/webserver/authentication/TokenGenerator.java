/*
 * Created by Bawb
 * 
 * 
 */
package webserver.authentication;

import java.util.Random;

/**
 *
 * @author MeanC
 */
public class TokenGenerator {
 
    public String generateToken() {
    
//        for (int i = 0; i < 125;  i++) {
//            System.out.println( i + ": " + (char) i);
//        }
        
        char[] charArray = new char[15];
        Random r = new Random();
        for (int i = 0; i < 15; i++) {
            int randomNum = r.nextInt((122 - 33) + 1) + 33;
            char c = (char) randomNum;
            charArray[i] = c;
        }
        return new String(charArray);
    }
    
}
