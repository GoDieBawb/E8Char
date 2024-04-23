package webserver.responses;

import webserver.WebServer;
import webserver.DataOMatic.DataResponse;

public class IsAdminResponse extends BasicResponse {
    public boolean isAdmin;

    public IsAdminResponse(int staffId) {
        DataResponse dr = WebServer.dbHandler.secureGet("SELECT COUNT(id) from Staff WHERE id = ? AND `admin` = 1", new Object[]{ staffId });
        String count = dr.getRowAt(1).get("COUNT(id)").toString();

        if (count.equals("0"))
            isAdmin = false;
        else
            isAdmin = true;

        outcome = "success";
        responseType = "isAdminResponse";
    }
}
