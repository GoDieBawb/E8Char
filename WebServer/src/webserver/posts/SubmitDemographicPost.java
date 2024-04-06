/*
 * Created by Bawb
 * 
 * 
 */
package webserver.posts;

import webserver.WebServer;

/**
 *
 * @author MeanC
 */
public class SubmitDemographicPost extends BasicPost {
    public String      firstName;
    public String      lastName;
    public String      phoneNumber;
    public String      dob; 		
    public String      address;   	
    public String      city;   	   
    public String      state;   	
    public int         zip;
    public int         enteredBy;
    public String      enteredDate;
    public String      emergencyPhone;
    public String      ethnicity;
    public String      insurance;
    public int         insuranceId;
    public String      pharmacy;
    public String      primaryLanguage;
    public String      race;
    public int         ssn;
    public String      sex;
    
    public void publish() {
        String queryString = "insert into `Clients` (FirstName, LastName, Phone, DOB, Address, City, State, Zip, EnteredBy, EnteredDate, " +
                             "EmergencyPhone, Ethnicity, Insurance, InsuranceId, Pharmacy, PrimaryLanguage, Race, SSN, Sex) " +
                             "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        WebServer.dbHandler.securePost(queryString, new Object[] {
            firstName, lastName, phoneNumber, dob, address, city, state, zip, enteredBy, enteredDate, 
            emergencyPhone, ethnicity, insurance, insuranceId, pharmacy, primaryLanguage, race, ssn, sex
        });
    }
}
