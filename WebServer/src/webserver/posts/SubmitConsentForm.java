package webserver.posts;

import webserver.SQLUtil;

//Created a public class to make it more understandble
public class ConsentFormPost extends ServicePost {

    //Post types
    public ConsentFormPost() {
        postType = "submitConsentForm";
        serviceCode = 6;  //Service code number for 6
    }

    public void debug() {
        System.out.println("procedure: " + procedure);
        System.out.println("risks: " + risks);
        System.out.println("benefits: " + benefits);
        System.out.println("anesthesia: " + anesthesia);
        System.out.println("consentType: " + consentType);
        System.out.println("additionalInfo: " + additionalInfo);
        System.out.println("personName: " + personName);
        System.out.println("consentGiven: " + consentGiven);
    }

    public void publish() {
        String serviceId = generateService();

        String queryString = "INSERT INTO ConsentFormData (Procedure, Risks, Benefits, Anesthesia, ConsentType, AdditionalInfo, PersonName, ConsentGiven, EnteredBy, EnteredDate, ServiceId) "
                + "VALUES ('" + procedure + "', '" + risks + "', '" + benefits + "', '" + anesthesia + "', '" + consentType + "', '" + additionalInfo + "', '" + personName + "', '" + consentGiven + "', '" + enteredby + "', '" + entereddate + "', '" + serviceId + "')";

        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);
    }
}
