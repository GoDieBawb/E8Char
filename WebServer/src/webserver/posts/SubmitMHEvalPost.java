/*
 * Created by Bawb
 * 
 * 
 */
package webserver.posts;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

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
        
        String queryString = "INSERT INTO `MentalHealthEvaluations` (stressLevel, anxietyLevel, depressionLevel, sleepQuality, overallHealth, enteredBy, enteredDate)"
                + "VALUES (?,?,?,?,?,?,?)";

        WebServer.dbHandler.securePost(queryString, new Object[] {
            stressLevel, anxietyLevel, depressionLevel, sleepQuality, overallHealth,
            enteredby, entereddate
        });

        DataResponse dr = WebServer.dbHandler.secureGet("SELECT MAX(id) FROM MentalHealthEvaluations", null);
        generateService((Number)dr.getValueAtRowAndColumn(1, "MAX(id)"));
    }    
    
}
