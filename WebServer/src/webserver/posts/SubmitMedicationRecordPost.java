package webserver.posts;

import webserver.SQLUtil;

/
public class SubmitMedicationRecordPost extends ServicePost {
    
    public String medicationName;
    public String dosage;
    public String frequencyOfDosage;
    public String prescribedBy;
    public String hasSideEffects;
    public String hasAllergies;
    
    SubmitMedicationRecordPost() {
        postType = "submitMedicationRecord";
        serviceCode = 9; //Service Code number 9
    }
    
    public void debug() {
        System.out.println("clientId: " + clientId);
        System.out.println("enteredby: " + enteredby);
        System.out.println("entereddate: " + entereddate);
        System.out.println("medicationName: " + medicationName);
        System.out.println("dosage: " + dosage);
        System.out.println("frequencyOfDosage: " + frequencyOfDosage);
        System.out.println("prescribedBy: " + prescribedBy);
        System.out.println("hasSideEffects: " + hasSideEffects);
        System.out.println("hasAllergies: " + hasAllergies);
        System.out.println("serviceCode: " + serviceCode);
    }
    
    public void publish() {
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
