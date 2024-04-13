package webserver.posts;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

public class SubmitBloodDrawPost extends ServicePost {
    public String patientName;
    public String dob;
    public String medicalRecordNumber;
    public String physicianName;
    public String bloodTest;
    public String labLocation;
    public String isFasting;
    public String signature;
    
    public void publish() {
        serviceCode = 3;
        
        String queryString = "INSERT INTO `BloodDraws` (patientName, dob, medicalRecordNumber, physicianName, bloodTest, labLocation, isFasting, signature, enteredBy, enteredDate) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        WebServer.dbHandler.securePost(queryString, new Object[] {
            patientName , dob , medicalRecordNumber , physicianName , bloodTest , labLocation , 
            isFasting , signature , enteredby , entereddate
        });

        DataResponse dr = WebServer.dbHandler.secureGet("SELECT MAX(id) FROM BloodDraws", null);
        generateService((Number)dr.getValueAtRowAndColumn(1, "MAX(id)"));
    }
}
