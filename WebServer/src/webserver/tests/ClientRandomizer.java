/*
 * Created by Bawb
 * 
 * 
 */
package webserver.tests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import webserver.posts.SubmitDemographicPost;

/**
 *
 * @author MeanC
 */
public class ClientRandomizer {
    
    public void createRandomClient() {
        
        SubmitDemographicPost client = new SubmitDemographicPost();
        client.enteredby   = 1;
        client.entereddate = randomDate(2024, 2024).toString();
        client.dob         = randomDate(1950, 2019).toString();
        client.firstname   = randomFirstName();
        client.lastname    = randomLastName();
        client.phonenumber = randomInt(100,999) + "" + randomInt(1000000, 9999999)+"";
        client.address     = randomInt(100, 5555) + " " + randomCity();
        client.city        = randomCity();
        client.state       = randomState();
        client.zip         = randomInt(10000, 99999);
        client.debug();
        client.publish();
    }
    
    private String randomFirstName() {
        return RandomData.NAMES[randomInt(0, RandomData.NAMES.length -1)].split(" ")[0];
    }
    
    private String randomLastName() {
        return RandomData.NAMES[randomInt(0, RandomData.NAMES.length -1)].split(" ")[1];
    }
    
    private String randomCity() {
        return RandomData.STREETS[randomInt(0, RandomData.STREETS.length -1)];
    }
    
    private String randomState() {
        return RandomData.STATES[randomInt(0, RandomData.STATES.length -1)];
    }
    
    public LocalDate randomDate(int minYear, int maxYear) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        
        int year  = randomInt(minYear, maxYear); 
        int month = randomInt(1,12);
        int day   = randomInt(1,30);
        
        LocalDate date = LocalDate.parse(year+"-"+month+"-"+day, formatter);
        //LocalDate date = LocalDate.parse("1994-05-09", formatter);
        return date;
    }
    
    private int randomInt(int min, int max) {
        Random r = new Random();
        int randomNum = r.nextInt((max - min) + 1) + min;
        
        return randomNum;
    }    
    
}
