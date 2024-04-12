package webserver.posts;

import webserver.WebServer;

public class MedicationSummaryPost extends ServicePost {
    public String orderNumber;
    public String contents;
    public String date;

    public void publish() {
        String serviceId = generateService();
        String queryString = "INSERT INTO `MedicationSummary` (OrderNumber, Contents, Date, ServiceCode, EnteredBy, EnteredDate, PertainingServiceId) "
                + "VALUES(?,?,?,?,?,?,?)";

        WebServer.dbHandler.securePost(queryString, new Object[] {
            orderNumber, contents, date, serviceCode, enteredby, 
            entereddate, serviceId
        });
    }
}
