package webserver.responses;

public class UnauthorizedResponse extends BasicResponse {
    public UnauthorizedResponse() {
        outcome      = "failed";
        responseType = "unauthorized";
    }
}
