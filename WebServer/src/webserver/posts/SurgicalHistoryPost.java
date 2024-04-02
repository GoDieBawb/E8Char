package webserver.posts;

import webserver.WebServer;

public class SurgicalHistoryPost extends ServicePost {
    public String patientName;
    public String surgeonName;
    public String dateOfSurgery;
    public String procedurePerformed;
    public String hospitalName;

    public void publish() {
        serviceCode = 8;

        String serviceId = generateService();
        String queryString = "INSERT INTO SurgicalHistory (PatientName, DateOfSurgery, ProcedurePerformed, SurgeonName, HospitalName, EnteredBy, EnteredDate, ServiceId) "
                + "VALUES (?,?,?,?,?,?,?,?)";

        WebServer.dbHandler.securePost(queryString, new Object[] {
            patientName , dateOfSurgery , procedurePerformed , surgeonName , hospitalName , enteredby , entereddate , serviceId
        });
    }
}
