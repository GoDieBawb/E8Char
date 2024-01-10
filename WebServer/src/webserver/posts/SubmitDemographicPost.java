/*
 * Created by Bawb
 * 
 * 
 */
package webserver.posts;

import webserver.SQLUtil;

/**
 *
 * @author MeanC
 */
public class SubmitDemographicPost extends BasicPost {
    
    public String      firstname;
    public String      lastname;
    public String      phonenumber;
    public String      dob; 		
    public String      address;   	
    public String      city;   	   
    public String      state;   	
    public String      zip;
    public int         enteredby;
    public String      entereddate;
    
    public SubmitDemographicPost() {
        postType = "submitDemographic";
    }
    
    public void debug() {
        System.out.println("firstname: " +    firstname);
        System.out.println("lastname: " +     lastname);
        System.out.println("phonenumber: " +  phonenumber);
        System.out.println("dob: " +          dob);
        System.out.println("address: " +      address);
        System.out.println("city: " +         city);
        System.out.println("state: " +        state);
        System.out.println("zip: " +          zip);
    }
    
    public void publish() {
        
        String queryString = "INSERT INTO Client (FirstName, LastName, Phone, DOB, Address, City, State, Zip, EnteredBy, EnteredDate)"
                + "VALUES ('" + firstname + "', '" + lastname + "', '" + phonenumber + "', '" + dob + "', '" + address + "', '" + city 
                + "', '" + state + "', '" + zip + "', '" + enteredby + "', '" + entereddate + "')";

        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);
    }
    
}
