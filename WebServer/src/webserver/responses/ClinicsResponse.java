package webserver.responses;

import java.util.ArrayList;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

public class ClinicsResponse extends BasicResponse {
    ArrayList<Clinic> clinics;

    public class Clinic {
        String name;
        String address;
        String phone;
        int clinicId;
    }

    public ClinicsResponse() {
        clinics = new ArrayList<>();

        DataResponse dr = WebServer.dbHandler.secureGet("SELECT id, name, address, phone FROM Clinics", null);

        // sends a list of clinics to the administrator user.
        for (int i = 1; i <= dr.size(); i++) {
            Clinic c = new Clinic();
            c.name = (String)dr.getValueAtRowAndColumn(i, "name");
            c.address = (String)dr.getValueAtRowAndColumn(i, "address");
            c.phone = (String)dr.getValueAtRowAndColumn(i, "phone");
            c.clinicId = (Integer)dr.getValueAtRowAndColumn(i, "id");
            clinics.add(c);
        }
    }
}
