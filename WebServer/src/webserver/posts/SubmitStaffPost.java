package webserver.posts;

import webserver.WebServer;

public class SubmitStaffPost extends ServicePost {
    public String firstName;
    public String lastName;
    public String password;
    public String phoneNumber;
    public String dob;
    public String streetAddress;
    public String city;
    public String state;
    public int zip;
    public String position;
    public String department;
    public String employmentDate;

    public void publish() {
        String queryString = "INSERT INTO `Staff` (username, firstName, lastName, password, phone, dob, streetAddress, city, state, zip, position, department, employmentDate, clinicId, `admin`)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        String username = firstName.charAt(0) + lastName;
        int clinicId = 1;
        int admin = 0;

        WebServer.dbHandler.securePost(queryString, new Object[] {
            username, firstName , lastName , password, phoneNumber, dob, streetAddress, city, 
            state, zip, position, department, employmentDate, clinicId, admin
        });
    }
}
