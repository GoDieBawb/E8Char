package webserver.posts;

import webserver.SQLUtil;

public class SurgicalHistoryPost extends ServicePost {

    public String patientName;
    public String dateOfSurgery;
    public String procedurePerformed;
    public String surgeonName;
    public String hospitalName;

    public SurgicalHistoryPost() {
        postType = "submitSurgicalHistoryForm";
        serviceCode = 8; //service code number 8
    }

    public void debug() {
        System.out.println("patientName: " + patientName);
        System.out.println("dateOfSurgery: " + dateOfSurgery);
        System.out.println("procedurePerformed: " + procedurePerformed);
        System.out.println("surgeonName: " + surgeonName);
        System.out.println("hospitalName: " + hospitalName);
    }

    public void publish() {
        String serviceId = generateService();

        String queryString = "INSERT INTO SurgicalHistory (PatientName, DateOfSurgery, ProcedurePerformed, SurgeonName, HospitalName, EnteredBy, EnteredDate, ServiceId) "
                + "VALUES ('" + patientName + "', '" + dateOfSurgery + "', '" + procedurePerformed + "', '" + surgeonName + "', '" + hospitalName + "', '" + enteredby + "', '" + entereddate + "', '" + serviceId + "')";

        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);
    }
}
