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
public class SubmitPhysicalPost extends ServicePost {
    public int         height;
    public int         weight;
    public String      bloodPressure;
    public int         heartRate;
    public float       temperature;    

    public void publish() {
        serviceCode = 1;
        
        String queryString = "INSERT INTO `PhysicalEvaluations` (height, weight, bloodPressure, heartRate, temperature, enteredBy, enteredDate)"
                + "VALUES(?,?,?,?,?,?,?)";
        
        WebServer.dbHandler.securePost(queryString, new Object[] {
            height , weight , bloodPressure , heartRate , temperature , 
            enteredby , entereddate
        });

        DataResponse dr = WebServer.dbHandler.secureGet("SELECT MAX(id) FROM PhysicalEvaluations", null);
        generateService((Number)dr.getValueAtRowAndColumn(1, "MAX(id)"));
    }        
}
