package webserver.responses;

import java.util.HashMap;
import webserver.SQLUtil;

public class ClientDemographicResponse extends BasicResponse {
   
    public HashMap<String, Object> demographicData;

    public ClientDemographicResponse(int clientID) {
        outcome = "success";
        responseType = "ClientServicesResponse";
        demographicData = new HashMap<String, Object>();

        getDemographicData(clientID);
    }

    // Note: the data is dependent on the header names of the Client table in the database.
    private void getDemographicData(int clientID) {
        SQLUtil sql = new SQLUtil();
        String response = sql.queryDatabase("SELECT * FROM Client WHERE id = '" + clientID + "'");
        response = response.replace("[", "");

        // if there's no results, then return nothing.
        if (response.equals(""))
            return;
        
        String[] clientStrings = response.split("]");

        for (String s : clientStrings) {     
            String[] fieldStrings = s.split(", ");
            
            for (int i = 0; i < fieldStrings.length; i++) {
                String fs = fieldStrings[i];
                String key = fs.split(":")[0];
                String value = fs.split(":")[1];

                if (!key.equals("Id") && !key.equals("EnteredDate"))
                    demographicData.put(key, value);
            }
        }
    }
}
