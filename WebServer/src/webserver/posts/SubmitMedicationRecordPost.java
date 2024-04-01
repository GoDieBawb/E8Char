package webserver.posts;

import webserver.SQLUtil;


public class SubmitMedicationRecordPost extends ServicePost {
    public String medicationName;
    public String dosage;
    public String frequencyOfDosage;
    public String prescribedBy;
    public String hasSideEffects;
    public String hasAllergies;

    public void publish() {
        serviceCode = 9;
        
        String serviceId = this.generateService();
        String queryString = "INSERT INTO MedicationRecords (ClientId, MedicationName, Dosage, FrequencyOfDosage, PrescribedBy, HasSideEffects, HasAllergies, ServiceCode, EnteredBy, EnteredDate, ServiceId)"
                + "VALUES ("
                + "'" + clientId        + "', "
                + "'" + medicationName  + "', "
                + "'" + dosage          + "', "
                + "'" + frequencyOfDosage + "', "
                + "'" + prescribedBy    + "', "
                + "'" + hasSideEffects  + "', "
                + "'" + hasAllergies    + "', "
                + "'" + serviceCode     + "', "
                + "'" + enteredby       + "', "
                + "'" + entereddate     + "', "
                + "'" + serviceId       + "')";

        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);
    }    
}
