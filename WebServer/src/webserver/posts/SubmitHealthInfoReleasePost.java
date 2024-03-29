package webserver.posts;

import webserver.SQLUtil;


public class SubmitHealthInfoReleasePost extends BasicPost {
    
    public String clientName;
    public String doctorName;
    public String date; 		
    
    public SubmitHealthInfoReleasePost() {
        postType = "submitHealthInfoRelease";
    }
    
    public void debug() {
        System.out.println("clientName: " + clientName);
        System.out.println("doctorName: " + doctorName);
        System.out.println("date: " + date);
    }
    
    public void publish() {
        String queryString = "INSERT INTO HealthInfoRelease (ClientName, DoctorName, Date) VALUES ('" + clientName + "', '" + doctorName + "', '" + date + "');";

        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);
    }
    
}
