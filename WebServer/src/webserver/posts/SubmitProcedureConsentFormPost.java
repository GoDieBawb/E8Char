package webserver.posts;

import webserver.WebServer;

public class SubmitProcedureConsentFormPost extends ServicePost {
    public String procedure;
    public String risks;
    public String benefits;
    public String anesthesia;
    public String consentType;
    public String additionalInfo;
    public String personName;
    public String personSignature;
    public String consentGiven;

    public void publish() {
        serviceCode = 6;

        String serviceId = generateService();
        String queryString = "INSERT INTO `ConsentFormData` (ConsentProcedure, Risks, Benefits, Anesthesia, ConsentType, AdditionalInfo, PersonName, ConsentGiven, EnteredBy, EnteredDate, PertainingServiceId) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        
        WebServer.dbHandler.securePost(queryString, new Object[] {
            procedure , risks , benefits , anesthesia , consentType , additionalInfo , personName , 
            consentGiven , enteredby , entereddate , serviceId
        });
    }
}
