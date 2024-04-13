package webserver.posts;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

public class AppointmentFormPost extends ServicePost {
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String preferredDoctor;
    public String date;
    public String reason;

    public void publish() {
        serviceCode = 7;

        String queryString = "INSERT INTO `Appointments` (firstName, lastName, email, phoneNumber, preferredDoctor, date, reason, enteredBy, enteredDate) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";

        WebServer.dbHandler.securePost(queryString, new Object[] {
            firstName, lastName, email, phoneNumber, preferredDoctor, 
            date, reason, enteredby, entereddate
        });

        DataResponse dr = WebServer.dbHandler.secureGet("SELECT MAX(id) FROM Appointments", null);
        generateService((Number)dr.getValueAtRowAndColumn(1, "MAX(id)"));
    }
}

