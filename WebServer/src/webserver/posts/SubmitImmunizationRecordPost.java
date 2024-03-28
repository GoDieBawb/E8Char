package webserver.posts;

import webserver.SQLUtil;

public class SubmitImmunizationRecordPost extends ServicePost {
    
    public String vaccineName;
    public String dateAdministered;
    public String administeringLocation;
    public String nextDoseDate;
    public String covidVaccination;
    public String covidFirstDoseDate;
    public String covidSecondDoseDate;
    public String firstBoosterDate;
    public String secondBoosterDate;
    public String vaccineTypeFirstDose;
    public String vaccineTypeSecondDose;
    public String vaccineTypeBooster; 
    
    public SubmitImmunizationRecordPost() {
        postType = "submitImmunizationRecord";
        serviceCode = 4; //Service code number 4 
    }
    
    public void publish() {
        String serviceId = generateService();
        
        String queryString = "INSERT INTO ImmunizationRecord (VaccineName, DateAdministered, AdministeringLocation, NextDoseDate, CovidVaccination, CovidFirstDoseDate, CovidSecondDoseDate, FirstBoosterDate, SecondBoosterDate, VaccineTypeFirstDose, VaccineTypeSecondDose, VaccineTypeBooster, ServiceCode, EnteredBy, EnteredDate, ServiceId) "
                + "VALUES ('" + vaccineName + "', '" + dateAdministered + "', '" + administeringLocation + "', '" + nextDoseDate + "', '" + covidVaccination + "', '" + covidFirstDoseDate + "', '" + covidSecondDoseDate + "', '" + firstBoosterDate + "', '" + secondBoosterDate + "', '" + vaccineTypeFirstDose + "', '" + vaccineTypeSecondDose + "', '" + vaccineTypeBooster + "', '" + serviceCode + "', '" + enteredby + "', '" + entereddate + "', '" + serviceId + "')";
        
        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);
    }
}
