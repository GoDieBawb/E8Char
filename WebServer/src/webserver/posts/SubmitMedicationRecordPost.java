package webserver.posts;

import webserver.WebServer;

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
        String queryString = "INSERT INTO MedicationRecords (ClientId, MedicationName, Dosage, FrequencyOfDosage, PrescribedBy, HasSideEffects, HasAllergies, ServiceCode, EnteredBy, EnteredDate, ServiceId) " +
                             "values(?,?,?,?,?,?,?,?,?,?,?)";

        WebServer.dbHandler.securePost(queryString, new Object[] {
            clientId, medicationName, dosage, frequencyOfDosage, prescribedBy, hasSideEffects,
            hasAllergies, serviceCode, enteredby, entereddate, serviceId
        });
    }    
}
