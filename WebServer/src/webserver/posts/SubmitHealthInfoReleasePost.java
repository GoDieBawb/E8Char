package webserver.posts;

import webserver.WebServer;

public class SubmitHealthInfoReleasePost extends BasicPost {
    public String patientName;
    public String doctorName;
    public String date;
    public String consented;
    
    public void publish() {
        String queryString = "INSERT INTO `HealthInfoRelease` (patientName, doctorName, date) VALUES (?,?,?)";

        WebServer.dbHandler.securePost(queryString, new Object[] {
            patientName, doctorName, date
        });
    }
}
