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

    public void publish() {
        serviceCode = 1;
        
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
