package webserver.posts;

import webserver.SQLUtil;

public class SubmitBloodDrawnRequestPost extends ServicePost {
    
    public String patientName;
    public String dateOfBirth;
    public String medicalRecordNumber;
    public String physicianName;
    public String bloodTest;
    public String labLocation;
    public String isFasting;
    public String signature;
    
    public SubmitBloodDrawnRequestPost() {
        postType = "submitBloodDrawnRequest";
        serviceCode = 3; 
    }
    
    public void debug() {
        System.out.println("patientName: " + patientName);
        System.out.println("dateOfBirth: " + dateOfBirth);
        System.out.println("medicalRecordNumber: " + medicalRecordNumber);
        System.out.println("physicianName: " + physicianName);
        System.out.println("bloodTest: " + bloodTest);
        System.out.println("labLocation: " + labLocation);
        System.out.println("isFasting: " + isFasting);
        System.out.println("signature: " + signature);
        System.out.println("serviceCode: " + serviceCode);
    }
    
    public void publish() {
        String serviceId = generateService();
        
        String queryString = "INSERT INTO BloodDrawnRequest (PatientName, DateOfBirth, MedicalRecordNumber, PhysicianName, BloodTest, LabLocation, IsFasting, Signature, ServiceCode, EnteredBy, EnteredDate, ServiceId) "
                + "VALUES ('" + patientName + "', '" + dateOfBirth + "', '" + medicalRecordNumber + "', '" + physicianName + "', '" + bloodTest + "', '" + labLocation + "', '" + isFasting + "', '" + signature + "', '" + serviceCode + "', '" + enteredby + "', '" + entereddate + "', '" + serviceId + "')";
        
        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);
    }
}
