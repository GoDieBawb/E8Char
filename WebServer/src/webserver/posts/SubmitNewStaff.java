package webserver.posts;

import webserver.WebServer;

public class SubmitNewStaff extends ServicePost {
    public String username;
    public String firstName;
    public String lastName;
    public String password;
    public String phoneNumber;
    public String dob;
    public String address;
    public String city;
    public String state;
    public int zip;
    public String position;
    public String department;
    public String employmentDate;
    
    public void publish() {
        String queryString = "INSERT INTO `Staff` (Username, FirstName, LastName, Password, Phone, DOB, Address, City, State, Zip, Position, Department, EmploymentDate)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        WebServer.dbHandler.securePost(queryString, new Object[] {
            username, firstName , lastName , password, phoneNumber, dob, address, city, 
            state, zip, position, department, employmentDate
        });
    }
}
