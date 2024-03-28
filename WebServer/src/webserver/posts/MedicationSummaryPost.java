package webserver.posts;

import webserver.SQLUtil;

public class MedicationSummaryPost extends ServicePost {

    public String orderNumber;
    public String contents;
    public String date;

    public MedicationSummaryPost() {
        postType = "medicationSummary";
        serviceCode = 5; // Service code number 5
    }

    public void publish() {
        String serviceId = generateService();

        String queryString = "INSERT INTO MedicationSummary (OrderNumber, Contents, Date, ServiceCode, EnteredBy, EnteredDate, ServiceId) "
                + "VALUES ('" + orderNumber + "', '" + contents + "', '" + date + "', '" + serviceCode + "', '" + enteredby + "', '" + entereddate + "', '" + serviceId + "')";

        SQLUtil sql = new SQLUtil();
        sql.queryDatabase(queryString);
    }
}
