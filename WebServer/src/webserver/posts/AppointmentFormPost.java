package webserver.posts;

import webserver.WebServer;

public class AppointmentFormPost extends ServicePost {
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String physicianName;
    public String dateTime;
    public String reason;

    public void publish() {
        serviceCode = 7;

        String serviceId = generateService();
        String queryString = "INSERT INTO AppointmentFormData (FirstName, LastName, Email, PhoneNumber, Physician, Date, Reason, EnteredBy, EnteredDate, ServiceId) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        WebServer.dbHandler.securePost(queryString, new Object[] {
            firstName, lastName, email, phoneNumber, physicianName, 
            dateTime, reason, enteredby, entereddate, serviceId
        });
    }
}

