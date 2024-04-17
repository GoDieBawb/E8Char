package webserver.responses;

import java.util.ArrayList;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

public class CliniciansResponse extends BasicResponse {
   
    public ArrayList<String[]> clinicians;
    public String              patientName;
    
    public CliniciansResponse(int patientId) {
        outcome      = "success";
        responseType = "CliniciansResponse";
        clinicians   = new ArrayList<>();
        
        DataResponse cr = WebServer.dbHandler.secureGet("SELECT * FROM Patients WHERE id = ?", new Object[]{patientId});
        patientName = cr.getRowAt(1).get("firstName").toString() + " " + cr.getRowAt(1).get("lastName").toString();
        
        // Note: the data is dependent on the header names of the Client table in the database.
        DataResponse dr = WebServer.dbHandler.secureGet("SELECT * FROM Staff", new Object[]{});

        // if there's no results, then return nothing.
        if (dr.size() == 0)
            return;
        
        for (int i = 1; i < dr.size() + 1; i++) {
            String[] data = {dr.getRowAt(i).get("id").toString(), "Dr. " + dr.getRowAt(i).get("firstName").toString() + " " + dr.getRowAt(i).get("lastName").toString()};
            clinicians.add(data);
        }
    }
}