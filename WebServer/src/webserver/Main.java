package webserver;

import webserver.tests.Tester;


public class Main {
    
    public static void main(String[] args) throws Exception {
        WebServer w = new WebServer();
        w.start();
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