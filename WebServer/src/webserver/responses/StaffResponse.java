package webserver.responses;

import java.util.ArrayList;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;


public class StaffResponse extends BasicResponse {
    ArrayList<Staff> staff;

    public class Staff {
        int staffId;
        String name;
        String employmentDate;
        int staffType;
        String phone;
        String department;
        String position;
        int clinicId;
    }

    // send a list of staff to the administrator user.
    public StaffResponse() {
        staff = new ArrayList<>();

        DataResponse dr = WebServer.dbHandler.secureGet("SELECT id, firstName, lastName, employmentDate, phone, `admin`, department, position, clinicId FROM Staff", null);

        // sends a list of clinics to the administrator user.
        for (int i = 1; i <= dr.size(); i++) {
            Staff s = new Staff();
            s.staffId = (Integer)dr.getValueAtRowAndColumn(i, "id");
            s.name = (String)dr.getValueAtRowAndColumn(i, "firstName") + " " + (String)dr.getValueAtRowAndColumn(i, "lastName");
            s.employmentDate = (String)dr.getValueAtRowAndColumn(i, "employmentDate");
            s.staffType = (Integer)dr.getValueAtRowAndColumn(i, "admin");
            s.department = (String)dr.getValueAtRowAndColumn(i, "department");
            s.position = (String)dr.getValueAtRowAndColumn(i, "position");
            s.phone = (String)dr.getValueAtRowAndColumn(i, "phone");
            s.clinicId = (Integer)dr.getValueAtRowAndColumn(i, "clinicId");
            staff.add(s);
        }
    }
}
