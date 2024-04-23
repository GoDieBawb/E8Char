package webserver.posts;

import java.util.HashMap;
import webserver.DataOMatic;
import webserver.WebServer;

public class SubmitHealthInfoReleasePost extends ServicePost {
    public String patientName;
    public String staffId;
    public String date;
    public String dob;
    public String consented;
    
    public void publish() {
        
        serviceCode = 10;
        
        DataOMatic.DataResponse cr = WebServer.dbHandler.secureGet("SELECT * FROM Patients WHERE id = ?", new Object[]{patientId});
        DataOMatic.DataResponse cr2 = WebServer.dbHandler.secureGet("SELECT firstName, lastName FROM Staff WHERE id = ?", new Object[]{staffId});
        
        HashMap<String, Object> row  =  cr.getRowAt(1);
        
        String dob       = row.get("dob").toString();
        String lastName  = row.get("lastName").toString();
        String firstName = row.get("firstName").toString();
        String staffName = "Dr. " + (String)cr2.getValueAtRowAndColumn(1, "firstName") + (String)cr2.getValueAtRowAndColumn(1, "lastName");
        String queryString = "INSERT INTO `Releases` (patientId, firstName, lastName, dob, staffId, staffName, date) VALUES (?,?,?,?,?,?,?)";

        WebServer.dbHandler.securePost(queryString, new Object[] {
            patientId, firstName, lastName, dob, staffId, staffName, date
        });
        
        DataOMatic.DataResponse dr = WebServer.dbHandler.secureGet("SELECT MAX(id) FROM Releases", null);
        generateService((Number)dr.getValueAtRowAndColumn(1, "MAX(id)"));        
        
    }
}
