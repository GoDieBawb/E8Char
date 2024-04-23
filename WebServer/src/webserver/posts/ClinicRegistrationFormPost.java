package webserver.posts;

import webserver.WebServer;

public class ClinicRegistrationFormPost extends BasicPost {
    String name;
    String address;
    String phone;

    public void publish() {
        WebServer.dbHandler.securePost("INSERT INTO Clinics (name, address, phone) VALUES(?,?,?)", new Object[]{ name, address, phone });
    }
}
