/*
 * Created by Bawb
 * 
 * 
 */
package webserver.responses;

import java.util.ArrayList;
import java.util.HashMap;
import webserver.SQLUtil;

/**
 *
 * @author MeanC
 */
public class ClientServicesResponse extends BasicResponse {
    
    public int clientId;
    public int userId;
    public HashMap<String,ArrayList<Service>> serviceMap;
    
    public ClientServicesResponse(int userId, int clientId) {
        this.userId   = userId;
        this.clientId = clientId;
        outcome       = "success";
        responseType  = "ClientServicesResponse";
        serviceMap    = new HashMap<>();
        getServices();
    }
    
    public class Service {
    
        public String date;
        public String serviceType;
        public String serviceId;
        public String serviceFile;
        
    }
    
    private void getServices() {
    
        String query            = "SELECT Service.Id, Service.ServiceDate, ServiceType, ServiceType.FileName FROM Service "
                                + "JOIN Test.ServiceType on ServiceCode = ServiceType.Id "
                                + "WHERE ClientId = " + clientId + " AND EnteredBy = " + userId;
                                
        //System.out.println(query);
        SQLUtil sql             = new SQLUtil();
        String response         = sql.queryDatabase(query).replace("[", "");
        String[] serviceStrings = response.split("]");

        // if there's no results, then return nothing.
        if (response.equals(""))
            return;

        for (String s : serviceStrings) {
            
            Service svc = new Service();          
            String[] fieldStrings = s.split(", ");
            
            for (int i = 0; i < fieldStrings.length; i++) {
                String fs    = fieldStrings[i];
                String value = fs.split(":")[1];

                if (fs.contains("ServiceDate:"))
                    svc.date = value;
                else if (fs.contains("ServiceType:"))
                    svc.serviceType = value;
                else if (fs.startsWith("Id:"))
                    svc.serviceId = value;
                else if (fs.startsWith("FileName:"))
                    svc.serviceFile = value;
            }
            
            if (!serviceMap.containsKey(svc.serviceId)) {
                ArrayList<Service> l = new ArrayList<>();
                serviceMap.put(svc.serviceId, l);
            }
            
            serviceMap.get(svc.serviceId).add(svc);
            
        }
    }
}
