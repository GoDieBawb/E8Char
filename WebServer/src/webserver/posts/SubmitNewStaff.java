package webserver.posts;

import webserver.SQLUtil;

public class SubmitNewStaff extends ServicePost {
    
    public String firstname;
    public String lastname;
    public String email;
    public String phone;
    public String dob;
    public String address;
    public String city;
    public String state;
    public String zip;
    public String position;
    public String department;
    public String employmentdate;
    public int hourlyrate;
    
    public SubmitNewStaff() {
        postType = "submitStaff";
        //serviceCode = 3; 
    }
    
    public void debug() { 
        System.out.println("patientName: " + firstname + " " + lastname);
        System.out.println("email: " + email);
        System.out.println("dateOfBirth: " + dob);
        System.out.println("phone: " + phone);
        System.out.println("address: " + address);
        System.out.println("location: " + city + ", " + state + " " + zip);
        System.out.println("position: " + position);
        System.out.println("department: " + department);
        System.out.println("employmentDate: " + employmentdate);
        System.out.println("pay: " + hourlyrate);
    }
    
    public void publish() {
        String serviceId = generateService();
        String queryString = "INSERT INTO Staff (FirstName, LastName, Phone, DOB, Address, City, State, Zip, EmploymentDate, hourlyrate)"
                + "VALUES ('" + firstname + "', '" + lastname + "', '" + phone + "', '" + dob + "', '" + address + "', '" + city 
                + "', '" + state + "', '" + zip + "', '" + employmentdate + "', '" + hourlyrate + "'); ";

        
        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);
    }
}
