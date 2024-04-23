package webserver.posts;

import webserver.WebServer;

public class StaffDeactivationPost extends BasicPost {
    int staffId;
    String password;

    public void publish() {
        WebServer.dbHandler.securePost("DELETE FROM Staff WHERE id = ?", new Object[] { staffId });
    }
}
