/*
 * Created by Bawb
 * 
 * 
 */
package webserver.posts;

import java.lang.reflect.Field;

import webserver.SQLUtil;

/**
 *
 * @author MeanC
 */
public class SubmitDemographicPost extends BasicPost {
    
    public String      firstname;
    public String      lastname;
    public String         phonenumber;
    public String      dob; 		
    public String      address;   	
    public String      city;   	   
    public String      state;   	
    public int         zip;
    public int         enteredby;
    public String      entereddate;
    public String         emergencyPhone;
    public String      ethnicity;
    public String      insurance;
    public int         insuranceId;
    public String      pharmacy;
    public String      primaryLanguage;
    public String      race;
    public int         ssn;
    public String      sex;
    public int         weight;
    
    public SubmitDemographicPost() {
        postType = "submitDemographic";
    }
    
    public void publish() {
        String queryString = String.format("insert into Client (FirstName, LastName, Phone, DOB, Address, City, State, Zip, EnteredBy, EnteredDate, " +
                                            "EmergencyPhone, Ethnicity, Insurance, InsuranceId, Pharmacy, PrimaryLanguage, Race, SSN, Sex, Weight) " +
                                            "values('%s', '%s', '%s', '%s', '%s', '%s', '%s', %d, %d, '%s', '%s', '%s', '%s', %d, '%s', '%s', '%s', %d, '%s', %d)",
                                            firstname, lastname, phonenumber, dob, address, city, state, zip, enteredby, entereddate, emergencyPhone, ethnicity,
                                            insurance, insuranceId, pharmacy, primaryLanguage, race, ssn, sex, weight);

        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);

        String idQuery = "SELECT MAX(id) FROM Client;";
        String id = sql.queryDatabase(idQuery).split(":")[1]; //Grabs the most recent ID. Sus but works
        System.out.println("ADDED CLIENT ID: " + id);
    }
    
}
