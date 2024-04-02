package webserver;

// import webserver.DataOMatic.DataResponse;
//import webserver.tests.Tester;

public class Main {
    public static void main(String[] args) throws Exception {
        WebServer w = new WebServer();
        w.start();

        // System.out.println(w.dbHandler.secureGet("select (id) from Client where id between ? and ?", new Object[] { 6, 10 }));
        // System.out.println(w.dbHandler.secureGet("select (id) from Client where FirstName = ?", new Object[] { "'; OR ''='" })); // injection test
        // w.dbHandler.securePost("insert into testTable (testColumn) values(?)", new Object[] { "TESTosterone" });

        //Tester t = new Tester();
        //t.clientServicesResponseTest();
        //t.clientsResponseTest();
        //t.tokenTest();
        //t.gsonTest();
        //t.sqlTest();
        //t.sqlTest2();
        //t.postTest();
        //t.readTest();
        //new ClientRandomizer().createRandomClient();
    }

}