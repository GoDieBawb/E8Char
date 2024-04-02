/*
 * Created by Bawb
 * 
 * 
 */
package webserver.responses;

import java.util.ArrayList;
import java.util.HashMap;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

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
                                + "WHERE ClientId = ? AND EnteredBy = ?";
                                
        DataResponse dr = WebServer.dbHandler.secureGet(query, new Object[] { clientId, userId });
        
        // if there's no results, then return nothing.
        if (dr.size() == 0)
            return;
        
        System.out.println("================== " + dr.size());

        // Make an organized list of services for a given patient.
        for (int i = 1; i <= dr.size(); i++) {
            Service svc = new Service(); 

            svc.serviceId = Integer.toString((Integer)dr.getValueAtRowAndColumn(i, "Id"));
            svc.date = (String)dr.getValueAtRowAndColumn(i, "ServiceDate");
            svc.serviceType = (String)dr.getValueAtRowAndColumn(i, "ServiceType");
            svc.serviceFile = (String)dr.getValueAtRowAndColumn(i, "FileName");

            /* If not already present in the serviceMap, make a list of services
               of this service type and insert it into the service map for subsequent
               insertions of this service type.*/
            if (!serviceMap.containsKey(svc.serviceType)) {
                ArrayList<Service> l = new ArrayList<>();
                serviceMap.put(svc.serviceType, l);
            }

            serviceMap.get(svc.serviceType).add(svc);
        }
    }
}
