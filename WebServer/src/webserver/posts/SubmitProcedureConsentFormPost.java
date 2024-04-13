package webserver.posts;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

public class SubmitProcedureConsentFormPost extends ServicePost {
    public String consentProcedure;
    public String risks;
    public String benefits;
    public String anesthesia;
    public String consentType;
    public String additionalInfo;
    public String personName;
    public String personSignature;
    public String consentGiven;
    public String signature;

    public void publish() {
        serviceCode = 6;

        String queryString = "INSERT INTO `ConsentFormData` (consentProcedure, risks, benefits, anesthesia, consentType, additionalInfo, personName, consentGiven, enteredBy, enteredDate, signature) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        
        WebServer.dbHandler.securePost(queryString, new Object[] {
            consentProcedure , risks , benefits , anesthesia , consentType , additionalInfo , personName , 
            consentGiven , enteredby , entereddate, signature
        });

        DataResponse dr = WebServer.dbHandler.secureGet("SELECT MAX(id) FROM ConsentFormData", null);
        generateService((Number)dr.getValueAtRowAndColumn(1, "MAX(id)"));
    }
}
