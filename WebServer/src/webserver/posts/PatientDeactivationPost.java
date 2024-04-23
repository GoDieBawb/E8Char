package webserver.posts;

import webserver.WebServer;

public class PatientDeactivationPost extends BasicPost {
    String password;
    
    public void publish() {
        WebServer.dbHandler.securePost("DELETE FROM Patients WHERE id = ?", new Object[] { patientId });
    }
}
