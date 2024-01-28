/*
 * Created by Bawb
 * 
 * 
 */
package webserver.authentication;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author MeanC
 */
public class TokenGenerator {

    ArrayList<Character> badChars;
    
    public String generateToken() {

        
        char[] charArray = new char[15];
        for (int i = 0; i < 15; i++) {

            charArray[i] = randomChar();
        }
        return new String(charArray);
    }
    
    private char randomChar() {
        
        char c = (char) randomCharNum();
        return c;        
    }
    
    private int randomCharNum() {
        Random r = new Random();
        int randomNum = r.nextInt((122 - 48) + 1) + 48;
        
        //Remove Special Characters
        if (randomNum >= 58 && randomNum <= 64) return randomCharNum();
        if (randomNum >= 91 && randomNum <= 96) return randomCharNum();
        
        return randomNum;
    }
    
}
