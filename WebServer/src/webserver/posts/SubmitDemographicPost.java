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
    public int         weight;
    
    public void publish() {
        String queryString = String.format("insert into Client (FirstName, LastName, Phone, DOB, Address, City, State, Zip, EnteredBy, EnteredDate, " +
                                            "EmergencyPhone, Ethnicity, Insurance, InsuranceId, Pharmacy, PrimaryLanguage, Race, SSN, Sex, Weight) " +
                                            "values('%s', '%s', '%s', '%s', '%s', '%s', '%s', %d, %d, '%s', '%s', '%s', '%s', %d, '%s', '%s', '%s', %d, '%s', %d)",
                                            firstName, lastName, phoneNumber, dob, address, city, state, zip, enteredBy, enteredDate, emergencyPhone, ethnicity,
                                            insurance, insuranceId, pharmacy, primaryLanguage, race, ssn, sex, weight);

        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);

        String idQuery = "SELECT MAX(id) FROM Client;";
        String id = sql.queryDatabase(idQuery).split(":")[1]; //Grabs the most recent ID. Sus but works
        System.out.println("ADDED CLIENT ID: " + id);
    }
    
}
