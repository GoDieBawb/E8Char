package webserver.posts;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

public class SubmitImmunizationRecordPost extends ServicePost {
    public String vaccineName;
    public String dateAdministered;
    public String administeringLocation;
    public String nextDoseDate;
    
    public void publish() {
        serviceCode = 4;
        
        String queryString = "INSERT INTO `ImmunizationRecords` (vaccineName, dateAdministered, administeringLocation, nextDoseDate, enteredBy, enteredDate) "
                + "VALUES (?,?,?,?,?,?)";
        
        WebServer.dbHandler.securePost(queryString, new Object[] {
            vaccineName , dateAdministered , administeringLocation , nextDoseDate, enteredby , entereddate
        });

        DataResponse dr = WebServer.dbHandler.secureGet("SELECT MAX(id) FROM ImmunizationRecords", null);
        generateService((Number)dr.getValueAtRowAndColumn(1, "MAX(id)"));
    }
}
