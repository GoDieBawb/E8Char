package webserver.posts;

import webserver.WebServer;

public class SubmitNewStaff extends ServicePost {
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String dob;
    public String address;
    public String city;
    public String state;
    public int zip;
    public String position;
    public String department;
    public String employmentDate;
    public int hourlyRate;
    
    public void publish() {
        String queryString = "INSERT INTO Staff (FirstName, LastName, Phone, DOB, Address, City, State, Zip, EmploymentDate, hourlyrate)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        WebServer.dbHandler.securePost(queryString, new Object[] {
            firstName , lastName , phoneNumber , dob , address , city , 
            state , zip , employmentDate , hourlyRate
        });
    }
}
