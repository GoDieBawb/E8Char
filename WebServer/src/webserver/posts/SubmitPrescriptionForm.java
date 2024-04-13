package webserver.posts;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

public class SubmitPrescriptionForm extends ServicePost {
    public String medicationName;
    public String dosage;
    public String frequencyOfDosage;
    public String prescribedBy;
    public String hasSideEffects;
    public String hasAllergies;
    public int refills;

    public void publish() {
        serviceCode = 9;
        
        String queryString = "INSERT INTO `Prescriptions` (medicationName, dosage, frequencyOfDosage, prescribedBy, hasSideEffects, hasAllergies, refills, enteredBy, enteredDate) " +
                             "values(?,?,?,?,?,?,?,?,?)";

        WebServer.dbHandler.securePost(queryString, new Object[] {
            medicationName, dosage, frequencyOfDosage, prescribedBy, hasSideEffects,
            hasAllergies, refills, enteredby, entereddate
        });

        DataResponse dr = WebServer.dbHandler.secureGet("SELECT MAX(id) FROM Prescriptions", null);
        generateService((Number)dr.getValueAtRowAndColumn(1, "MAX(id)"));
    }    
}
