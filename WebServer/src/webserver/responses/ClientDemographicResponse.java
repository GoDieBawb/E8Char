package webserver.responses;

import java.util.HashMap;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

public class ClientDemographicResponse extends BasicResponse {
   
    public HashMap<String, Object> demographicData;

    public ClientDemographicResponse(int clientID) {
        outcome = "success";
        responseType = "ClientDemographicResponse";
        demographicData = new HashMap<String, Object>();

        getDemographicData(clientID);
    }

    // Note: the data is dependent on the header names of the Client table in the database.
    private void getDemographicData(int clientID) {
        DataResponse dr = WebServer.dbHandler.secureGet("SELECT * FROM Clients WHERE id = ?", new Object[] { clientID });

        // if there's no results, then return nothing.
        if (dr.size() == 0)
            return;
        
        HashMap<String, Object> row = dr.getRowAt(1);
        row.forEach((key, value) -> demographicData.put(key, value));
    }
}
