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

    public void publish() {
        String serviceId = generateService();

        String queryString = "INSERT INTO SurgicalHistory (PatientName, DateOfSurgery, ProcedurePerformed, SurgeonName, HospitalName, EnteredBy, EnteredDate, ServiceId) "
                + "VALUES ('" + patientName + "', '" + dateOfSurgery + "', '" + procedurePerformed + "', '" + surgeonName + "', '" + hospitalName + "', '" + enteredby + "', '" + entereddate + "', '" + serviceId + "')";

        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);
    }
}
