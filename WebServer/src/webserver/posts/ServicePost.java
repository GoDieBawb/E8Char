/*
 * Created by Bawb
 * 
 * 
 */
package webserver.posts;

import webserver.WebServer;

/**
 *
 * @author MeanC
 */
public class ServicePost extends BasicPost {
    public int         patientId;
    public int         enteredby;
    public String      entereddate;    
    public int         serviceCode;
    
    protected void generateService(Number referenceServiceId) {
        /* Generate a specific service record before generating this service entry, so that 
        the ReferencingServiceId field can hold the specific service record number. */
        String serviceQuery = "INSERT INTO `Services` (referencingServiceId, serviceCode, patientId, serviceDate, enteredBy) VALUES (?,?,?,?,?)";

        WebServer.dbHandler.securePost(serviceQuery, new Object[] {
            referenceServiceId, serviceCode, patientId, entereddate, enteredby
        });
    }
}
