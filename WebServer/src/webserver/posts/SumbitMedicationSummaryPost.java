package webserver.posts;

import webserver.SQLUtil;
//Created another class to make it more understandble
public class MedicationSummaryPost extends ServicePost {
    
    
    public MedicationSummaryPost() {
        postType = "medicationSummary";
        serviceCode = 5; // Assuming a unique service code for medication summary posts
    }
    
    public void debug() {
        System.out.println("orderNumber: " + orderNumber);
        System.out.println("contents: " + contents);
        System.out.println("date: " + date);
        System.out.println("serviceCode: " + serviceCode);
    }
    
    public void publish() {
        String serviceId = generateService();
        
        String queryString = "INSERT INTO MedicationSummary (OrderNumber, Contents, Date, ServiceCode, EnteredBy, EnteredDate, ServiceId) "
                + "VALUES ('" + orderNumber + "', '" + contents + "', '" + date + "', '" + serviceCode + "', '" + enteredby + "', '" + entereddate + "', '" + serviceId + "')";
        
        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);
    }
}
