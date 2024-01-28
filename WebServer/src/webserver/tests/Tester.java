/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver.tests;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Scanner;
import util.FileReader;
import webserver.SQLUtil;
import webserver.authentication.TokenGenerator;
import webserver.posts.BasicPost;
import webserver.posts.LoginPost;
import webserver.posts.PostHandler;
import webserver.responses.LoginResponse;

/**
 *
 * @author MeanC
 */
public class Tester {
    
    public void sqlTest() {
        SQLUtil u = new SQLUtil();
        String userName  = "testy";
        String response  = u.queryDatabase("SELECT Password FROM Staff WHERE Username = '" + userName+"'");
         
        System.out.println("SQL Response: " + response); //print raw response
        
        response = response.substring(1, response.length() - 1); //Remove first and last character
        String password  = response.split(": ")[1]; //Split at ": " to get requested field    
        System.out.println("Pass: " + password);
    }

    public void sqlTest2() {
        SQLUtil u = new SQLUtil();
        String userName  = "testy";
        String response  = u.queryDatabase("SELECT * FROM Staff");
         
        System.out.println("SQL Response: " + response); //print raw response
        
        response = response.substring(1, response.length() - 1); //Remove first and last character
        String password  = response.split(": ")[1]; //Split at ": " to get requested field    
        System.out.println("Pass: " + password);
    }

    protected class GsonTest {
        
        boolean w;
        int     x;
        String  y;
        float   z;

        public GsonTest() {
            w = false;
            x = 50;
            y = "Some bullshit";
            z = 5.5f;
            
        }
    }

    public void gsonTest() {
        Gson     g = new Gson();
        GsonTest t = new GsonTest();
        String   s = g.toJson(t);
        System.out.println("Json String: " + s);
        GsonTest a = g.fromJson(s, GsonTest.class);
        System.out.println("Gson Test Y: " + a.y);
    }
    
    public void postTest() {
        
        PostHandler p =  new PostHandler();
        
        LoginPost l   = new LoginPost();
        l.accessToken = "None";
        l.postType    = "Login";
        l.username    = "testy";
        l.password    = "$test$";
        Gson      g   = new Gson();
        String    s   = g.toJson(l);       
        
        System.out.println("JSON String: " + s);
        
        LoginResponse response  = (LoginResponse) p.handle(s);
        
        
        
        BasicPost b   = new BasicPost();
        b.accessToken = response.accessToken;
        b.postType    = "Fake Post Type";
        p.handle(g.toJson(b));

        try {
            Thread.sleep(61*1000);
        } catch (InterruptedException ex) {
        }

        p.handle(g.toJson(b));        
        
    }
    
    public void readTest() {
        
        ArrayList<String> lines = new FileReader().getLines("pass.txt");
        String db = "";
        for (String line : lines) {
            if (line.contains("db")) {
                db = line;
                break;
            }
        }

        String info = db.split(":")[1];
        String user = info.split(",")[0];
        String pass = info.split(",")[1];
        System.out.println("User: " + user);
        System.out.println("Pass: " + pass);
        
    }
    
    public void clientsResponseTest() {
        
        System.out.print("Enter DB Password: ");
        String pass = new Scanner(System.in).nextLine();
        
        
        PostHandler p = new PostHandler();
        LoginPost l   = new LoginPost();
        l.accessToken = "None";
        l.postType    = "Login";
        l.username    = "testy";
        l.password    = pass;
        Gson      g   = new Gson();
        String    s   = g.toJson(l);       
        
        LoginResponse response  = (LoginResponse) p.handle(s);
        
        BasicPost b   = new BasicPost();
        b.accessToken = response.accessToken;
        b.postType    = "requestUserClients";
        p.handle(g.toJson(b));
        
    }
    
    public void tokenTest() {
    
        TokenGenerator t = new TokenGenerator();
        int x = 1;
        while (x < 10) {
            System.out.println(x+": " + t.generateToken());
            x++;
        }
        
    }
    
}
