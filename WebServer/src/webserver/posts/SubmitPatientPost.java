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
public class SubmitPatientPost extends BasicPost {
    public String      firstName;
    public String      lastName;
    public String      phoneNumber;
    public String      dob; 		
    public String      maritalStatus; 		
    public String      streetAddress;   	
    public String      city;   	   
    public String      state;   	
    public int         zip;
    public int         enteredBy;
    public String      enteredDate;
    public String      emergencyPhone;
    public String      ethnicity;
    public String      insurance;
    public String      insuranceId;
    public String      pharmacy;
    public String      primaryLanguage;
    public String      race;
    public String      otherRace;
    public String      ssn;
    public String      sex;
    public String      pregnancyStatus;
    public String      otherLanguage;
    
    public void publish() {
        String queryString = "insert into `Patients` (firstName, lastName, phone, dob, streetAddress, city, state, zip, enteredBy, enteredDate, " +
                             "emergencyPhone, ethnicity, insurance, insuranceId, pharmacy, primaryLanguage, race, ssn, sex, maritalStatus, otherRace, pregnancyStatus, otherLanguage) " +
                             "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        WebServer.dbHandler.securePost(queryString, new Object[] {
            firstName, lastName, phoneNumber, dob, streetAddress, city, state, zip, enteredBy, enteredDate, 
            emergencyPhone, ethnicity, insurance, insuranceId, pharmacy, primaryLanguage, race, ssn, sex, maritalStatus, otherRace, pregnancyStatus, otherLanguage
        });
    }
}
