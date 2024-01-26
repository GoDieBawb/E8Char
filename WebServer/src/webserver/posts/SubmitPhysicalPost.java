/*
 * Created by Bawb
 * 
 * 
 */
package webserver.posts;

import webserver.SQLUtil;

/**
 *
 * @author MeanC
 */
public class SubmitPhysicalPost extends ServicePost {
       
    public int         height;
    public int         weight;
    public String      bloodPressure;
    public int         heartRate;
    public float       temperature;    
    
    SubmitPhysicalPost() {
        postType    = "submitPhysicalEval";
        serviceCode = 1;
    }    
    
    public void debug() {
        System.out.println("clientId: " + clientId);
        System.out.println("enteredby: " + enteredby);
        System.out.println("entereddate: " + entereddate);
        System.out.println("serviceCode:  " + serviceCode);    
        System.out.println("height: " + height);
        System.out.println("weight: " + weight);
        System.out.println("bloodPressure: " + bloodPressure);
        System.out.println("heartRate: " + heartRate);
        System.out.println("temperature:  " + temperature);        
    }
    
    public void publish() {
        
        String serviceId = generateService();
        
        String queryString = "INSERT INTO PhysicalEval (ClientId, Height, Weight, BloodPressure, HeartRate, Temperature, ServiceCode, EnteredBy, EnteredDate, ServiceId)"
                + "VALUES ("
                + "'"  + clientId      + "',"
                + " '" + height        + "',"
                + " '" + weight        + "',"
                + " '" + bloodPressure + "',"
                + " '" + heartRate     + "',"
                + " '" + temperature   + "',"
                + " '" + serviceCode   + "',"
                + " '" + enteredby     + "',"
                + " '" + entereddate   + "',"
                + " '" + serviceId     + "')";
        
        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);
        
    }        
    
}
