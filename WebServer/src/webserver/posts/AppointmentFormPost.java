package webserver.posts;

import webserver.SQLUtil;

public class AppointmentFormPost extends ServicePost {

    public String firstname;
    public String lastname;
    public String email;
    public String phonenumber;
    public String physician;
    public String date;
    public String reason;

//Post types
    public AppointmentFormPost() {
        postType = "submitAppointmentForm";
        serviceCode = 7; //service code number 7
    }

    public void debug() {
        System.out.println("firstname: " + firstname);
        System.out.println("lastname: " + lastname);
        System.out.println("email: " + email);
        System.out.println("phonenumber: " + phonenumber);
        System.out.println("physician: " + physician);
        System.out.println("date: " + date);
        System.out.println("reason: " + reason);
    }

    public void publish() {
        String serviceId = generateService();

        String queryString = "INSERT INTO AppointmentFormData (FirstName, LastName, Email, PhoneNumber, Physician, Date, Reason, EnteredBy, EnteredDate, ServiceId) "
                + "VALUES ('" + firstname + "', '" + lastname + "', '" + email + "', '" + phonenumber + "', '" + physician + "', '" + date + "', '" + reason + "', '" + enteredby + "', '" + entereddate + "', '" + serviceId + "')";

        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);
    }
}

