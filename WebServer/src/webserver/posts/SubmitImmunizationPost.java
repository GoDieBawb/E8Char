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
    
    public void debug() {
        System.out.println("vaccineName: " + vaccineName);
        System.out.println("dateAdministered: " + dateAdministered);
        System.out.println("administeringLocation: " + administeringLocation);
        System.out.println("nextDoseDate: " + nextDoseDate);
        System.out.println("covidVaccination: " + covidVaccination);
        System.out.println("covidFirstDoseDate: " + covidFirstDoseDate);
        System.out.println("covidSecondDoseDate: " + covidSecondDoseDate);
        System.out.println("firstBoosterDate: " + firstBoosterDate);
        System.out.println("secondBoosterDate: " + secondBoosterDate);
        System.out.println("vaccineTypeFirstDose: " + vaccineTypeFirstDose);
        System.out.println("vaccineTypeSecondDose: " + vaccineTypeSecondDose);
        System.out.println("vaccineTypeBooster: " + vaccineTypeBooster);
        System.out.println("serviceCode: " + serviceCode);
    }
    
    public void publish() {
        String serviceId = generateService();
        
        String queryString = "INSERT INTO ImmunizationRecord (VaccineName, DateAdministered, AdministeringLocation, NextDoseDate, CovidVaccination, CovidFirstDoseDate, CovidSecondDoseDate, FirstBoosterDate, SecondBoosterDate, VaccineTypeFirstDose, VaccineTypeSecondDose, VaccineTypeBooster, ServiceCode, EnteredBy, EnteredDate, ServiceId) "
                + "VALUES ('" + vaccineName + "', '" + dateAdministered + "', '" + administeringLocation + "', '" + nextDoseDate + "', '" + covidVaccination + "', '" + covidFirstDoseDate + "', '" + covidSecondDoseDate + "', '" + firstBoosterDate + "', '" + secondBoosterDate + "', '" + vaccineTypeFirstDose + "', '" + vaccineTypeSecondDose + "', '" + vaccineTypeBooster + "', '" + serviceCode + "', '" + enteredby + "', '" + entereddate + "', '" + serviceId + "')";
        
        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);
    }
}
