package webserver.responses;

import java.util.ArrayList;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

public class PatientsResponse extends BasicResponse {
    ArrayList<Patient> patients;

    public class Patient {
        int patientId;
        String name;
        String dob;
        String registrationDate;
    }

    public PatientsResponse() {
        patients = new ArrayList<>();

        DataResponse dr = WebServer.dbHandler.secureGet("SELECT id, firstName, lastName, dob, enteredDate FROM Patients", null);

        // sends a list of patients to the administrator user.
        for (int i = 1; i <= dr.size(); i++) {
            Patient p = new Patient();
            p.patientId = (Integer)dr.getValueAtRowAndColumn(i, "id");
            p.name = (String)dr.getValueAtRowAndColumn(i, "firstName") + " " + (String)dr.getValueAtRowAndColumn(i, "lastName");
            p.dob = (String)dr.getValueAtRowAndColumn(i, "dob");
            p.registrationDate = (String)dr.getValueAtRowAndColumn(i, "enteredDate");
            patients.add(p);
        }
    }
}
