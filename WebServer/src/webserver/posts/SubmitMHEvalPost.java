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
public class SubmitMHEvalPost  extends ServicePost {
    
    public int         stressLevel;    
    public int         anxietyLevel;   
    public int         depressionLevel;
    public String      sleepQuality;   
    public int         overallHealth;
    
    SubmitMHEvalPost() {
        postType    = "submitMHEval";
        serviceCode = 2;
    }
    
    public void debug() {
        System.out.println("clientId: " +        clientId);
        System.out.println("enteredby: " +       enteredby);
        System.out.println("entereddate: " +     entereddate);
        System.out.println("stressLevel: " +     stressLevel);
        System.out.println("anxietyLevel: " +    anxietyLevel);
        System.out.println("depressionLevel: " + depressionLevel);
        System.out.println("sleepQuality: " +    sleepQuality);
        System.out.println("overallHealth: " +   overallHealth);
        System.out.println("serviceCode: " +     serviceCode);    
    }
    
    public void publish() {

        String serviceId = this.generateService();
        
        String queryString = "INSERT INTO MHEval (ClientId, StressLevel, AnxietyLevel, DepressionLevel, SleepQuality, OverallHealth, ServiceCode, EnteredBy, EnteredDate, ServiceId)"
                + "VALUES ("
                + "'" + clientId        + "', "
                + "'" + stressLevel     + "', "
                + "'" + anxietyLevel    + "', "
                + "'" + depressionLevel + "', "
                + "'" + sleepQuality    + "', "
                + "'" + overallHealth   + "', "
                + "'" + serviceCode     + "', "
                + "'" + enteredby       + "', "
                + "'" + entereddate     + "', "
                + "'" + serviceId       + "')";

        SQLUtil sql         = new SQLUtil();
        sql.queryDatabase(queryString);
    }    
    
}
