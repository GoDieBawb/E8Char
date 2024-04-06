/*
 * Created by Bawb
 * 
 * 
 */
package webserver.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

/**
 *
 * @author MeanC
 */
public final class DataManager {

    public void populate() {
    }
    
    public String randomDate(int minYear, int maxYear) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        
        int year  = randomInt(minYear, maxYear); 
        int month = randomInt(1,12);
        int day   = randomInt(1,30);
        
        LocalDate date = LocalDate.parse(year+"-"+month+"-"+day, formatter);
        //LocalDate date = LocalDate.parse("1994-05-09", formatter);
        return date.toString();
    }
    
    private int randomInt(int min, int max) {
        Random r = new Random();
        int randomNum = r.nextInt((max - min) + 1) + min;
        
        return randomNum;
    }    
    
}
