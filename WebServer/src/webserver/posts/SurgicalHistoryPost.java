package webserver.posts;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

public class SurgicalHistoryPost extends ServicePost {
    public String firstName;
    public String lastName;
    public String surgeonName;
    public String dateOfSurgery;
    public String procedurePerformed;
    public String hospitalName;

    public void publish() {
        serviceCode = 8;

        String queryString = "INSERT INTO `SurgicalHistories` (firstName, lastName, dateOfSurgery, procedurePerformed, surgeonName, hospitalName, enteredBy, enteredDate) "
                + "VALUES (?,?,?,?,?,?,?,?)";

        WebServer.dbHandler.securePost(queryString, new Object[] {
            firstName, lastName, dateOfSurgery , procedurePerformed , surgeonName , hospitalName , enteredby , entereddate
        });

        DataResponse dr = WebServer.dbHandler.secureGet("SELECT MAX(id) FROM SurgicalHistories", null);
        generateService((Number)dr.getValueAtRowAndColumn(1, "MAX(id)"));
    }
}
