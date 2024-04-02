/*
 * Created by Bawb
 * 
 * 
 */
package webserver.posts;

import webserver.WebServer;

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

    public void publish() {
        serviceCode = 2;
        
        String serviceId = this.generateService();
        String queryString = "INSERT INTO MHEval (ClientId, StressLevel, AnxietyLevel, DepressionLevel, SleepQuality, OverallHealth, ServiceCode, EnteredBy, EnteredDate, ServiceId)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";

        WebServer.dbHandler.securePost(queryString, new Object[] {
            clientId, stressLevel, anxietyLevel, depressionLevel, sleepQuality, overallHealth,
            serviceCode, enteredby, entereddate, serviceId
        });
    }    
    
}
