package webserver.responses;

import java.util.HashMap;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

public class ClientDemographicResponse extends BasicResponse {
   
    public HashMap<String, Object> demographicData;

    public ClientDemographicResponse(int clinicianId, int patientId) {
        outcome = "success";
        responseType = "ClientDemographicResponse";
        demographicData = new HashMap<String, Object>();

        // Note: the data is dependent on the header names of the Client table in the database.
        DataResponse dr = WebServer.dbHandler.secureGet("SELECT * FROM Patients WHERE id = ? AND enteredBy = ?", new Object[] { patientId, clinicianId });

        // if there's no results, then return nothing.
        if (dr.size() == 0)
            return;
        
        HashMap<String, Object> row = dr.getRowAt(1);
        row.forEach((key, value) -> demographicData.put(key, value));
    }
}
