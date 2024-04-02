/*
 * Created by Bawb
 * 
 * 
 */
package webserver.posts;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

/**
 *
 * @author MeanC
 */
public class ServicePost extends BasicPost {
    public int         clientId;
    public int         enteredby;
    public String      entereddate;    
    public int         serviceCode;
    
    protected String generateService() {
        String serviceQuery = "INSERT INTO Service (ServiceCode, ClientId, ServiceDate, EnteredBy) VALUES (?, ?, ?, ?)";

        WebServer.dbHandler.securePost(serviceQuery, new Object[] {
            serviceCode, clientId, entereddate, enteredby
        });

        DataResponse dr = WebServer.dbHandler.secureGet("SELECT MAX(id) FROM Service", new Object[0]);

        return Integer.toString((Integer)dr.getValueAtRowAndColumn(1, "MAX(id)"));
    }
}
