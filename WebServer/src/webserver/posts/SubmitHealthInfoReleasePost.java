package webserver.posts;

import webserver.WebServer;

public class SubmitHealthInfoReleasePost extends BasicPost {
    public String clientName;
    public String doctorName;
    public String date;
    public String consented;
    
    public void publish() {
        String queryString = "INSERT INTO `HealthInfoRelease` (ClientName, DoctorName, Date) VALUES (?,?,?)";

        WebServer.dbHandler.securePost(queryString, new Object[] {
            clientName, doctorName, date
        });
    }
}
