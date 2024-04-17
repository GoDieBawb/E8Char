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
    
    public HashMap<String,ArrayList<Service>> serviceMap;
    public HashMap<String, String> availableServices;
    
    public ClientServicesResponse(int userId, int patientId) {
        outcome       = "success";
        responseType  = "ClientServicesResponse";
        serviceMap    = new HashMap<>();
        availableServices = new HashMap<>();
        getServices(userId, patientId);
    }
    
    public class Service {
        public String serviceType;
        public String serviceId;
        public String serviceFile;
        public String formFiler;
        public HashMap<String, Object> readOnlyData;
    }
    
    private void getServices(int userId, int patientId) {
        String query = "SELECT Services.id, serviceType, ServiceType.fileName, serviceCode, referencingServiceId, Staff.firstName, Staff.lastName FROM Services "
                     + "JOIN Test.ServiceType on serviceCode = ServiceType.id "
                     + "JOIN Test.Staff ON Staff.id = enteredBy "
                     + "WHERE patientId = ?";
                                
        DataResponse dr = WebServer.dbHandler.secureGet(query, new Object[] { patientId });
        
        if (dr.size() > 0) {

            // Make an organized list of services for a given patient.
            for (int i = 1; i <= dr.size(); i++) {
                Service svc = new Service(); 

                svc.serviceId = Integer.toString((Integer)dr.getValueAtRowAndColumn(i, "id"));
                svc.serviceType = (String)dr.getValueAtRowAndColumn(i, "serviceType");
                svc.serviceFile = (String)dr.getValueAtRowAndColumn(i, "fileName");
                svc.formFiler = (String)dr.getValueAtRowAndColumn(i, "lastName") + ", " + (String)dr.getValueAtRowAndColumn(i, "firstName");

                /* If not already present in the serviceMap, make a list of services
                of this service type and insert it into the service map for subsequent
                insertions of this service type.*/
                if (!serviceMap.containsKey(svc.serviceType)) {
                    ArrayList<Service> l = new ArrayList<>();
                    serviceMap.put(svc.serviceType, l);
                }

                int serviceCode = (int)dr.getValueAtRowAndColumn(i, "serviceCode");
                int referenceRecordId = (int)dr.getValueAtRowAndColumn(i, "referencingServiceId");

                String recordQuery = "";
                DataResponse recordData = null;

                // Get the service record data for this service record.
                // Strings must be updated to correctly reflect on any new or updated table names.
                switch (serviceCode) {
                    case 1:
                        recordQuery = "SELECT * From PhysicalEvaluations WHERE id = ?";
                        break;
                    case 2:
                        recordQuery = "SELECT * From MentalHealthEvaluations WHERE id = ?"; 
                        break;
                    case 3:
                        recordQuery = "SELECT * From BloodDraws WHERE id = ?"; 
                        break;
                    case 4:
                        recordQuery = "SELECT * From ImmunizationRecords WHERE id = ?"; 
                        break;
                    case 5:
                        recordQuery = "SELECT * From MedicationSummary WHERE id = ?"; 
                        break;
                    case 6:
                        recordQuery = "SELECT * From ConsentFormData WHERE id = ?"; 
                        break;
                    case 7:
                        recordQuery = "SELECT * From Appointments WHERE id = ?"; 
                        break;
                    case 8:
                        recordQuery = "SELECT * From SurgicalHistories WHERE id = ?"; 
                        break;
                    case 9:
                        recordQuery = "SELECT * From Prescriptions WHERE id = ?"; 
                        break;
                    case 10:
                        recordQuery = "SELECT * FROM Releases WHERE id = ?";
                        break;
                }

                recordData = WebServer.dbHandler.secureGet(recordQuery, new Object[]{ referenceRecordId }); 
                svc.readOnlyData = recordData.getRowAt(1);
                svc.readOnlyData.remove("id");
                svc.readOnlyData.remove("enteredBy");

                serviceMap.get(svc.serviceType).add(svc);
            }
        }

        // Populate available services.
        DataResponse dr2 = WebServer.dbHandler.secureGet("SELECT serviceType, fileName FROM ServiceType", null);

        for (int i = 1; i <= dr2.size(); i++)
            availableServices.put((String)dr2.getValueAtRowAndColumn(i, "serviceType"), (String)dr2.getValueAtRowAndColumn(i, "fileName"));
    }
}
