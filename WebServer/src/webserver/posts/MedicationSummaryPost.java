package webserver.posts;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

public class MedicationSummaryPost extends ServicePost {
    public String orderNumber;
    public String contents;
    public String date;

    public void publish() {
        String queryString = "INSERT INTO `MedicationSummary` (orderNumber, contents, date, enteredBy, enteredDate) "
                + "VALUES(?,?,?,?,?)";

        WebServer.dbHandler.securePost(queryString, new Object[] {
            orderNumber, contents, date, enteredby, entereddate
        });

        DataResponse dr = WebServer.dbHandler.secureGet("SELECT MAX(id) FROM MedicationSummary", null);
        generateService((Number)dr.getValueAtRowAndColumn(1, "MAX(id)"));
    }
}
