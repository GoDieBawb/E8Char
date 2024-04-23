package webserver.posts;

import webserver.WebServer;

public class ClinicDeactivationPost extends BasicPost {
    int clinicId;
    String password;
    
    public void publish() {
        WebServer.dbHandler.securePost("DELETE FROM Clinics WHERE id = ?", new Object[] { clinicId });
    }
}
