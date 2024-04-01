package webserver.posts;

import webserver.SQLUtil;

public class SubmitBloodDrawnPost extends ServicePost {
    public String patientName;
    public String dateOfBirth;
    public String medicalRecordNumber;
    public String physicianName;
    public String bloodTest;
    public String labLocation;
    public String isFasting;
    public String signature;
    
    public void publish() {
        serviceCode = 3;
        
        String serviceId = generateService();
        String queryString = "INSERT INTO BloodDrawnRequest (PatientName, DateOfBirth, MedicalRecordNumber, PhysicianName, BloodTest, LabLocation, IsFasting, Signature, ServiceCode, EnteredBy, EnteredDate, ServiceId) "
                + "VALUES ('" + patientName + "', '" + dateOfBirth + "', '" + medicalRecordNumber + "', '" + physicianName + "', '" + bloodTest + "', '" + labLocation + "', '" + isFasting + "', '" + signature + "', '" + serviceCode + "', '" + enteredby + "', '" + entereddate + "', '" + serviceId + "')";
        
        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);
    }
}
