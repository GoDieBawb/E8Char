/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver.tests;

import com.google.gson.Gson;
import java.util.ArrayList;
import util.FileReader;
import webserver.SQLUtil;
import webserver.posts.BasicPost;
import webserver.posts.LoginPost;
import webserver.posts.PostHandler;

/**
 *
 * @author MeanC
 */
public class Tester {
    
    public void sqlTest() {
        SQLUtil u = new SQLUtil();
        String response = u.queryDatabase("SELECT * FROM Persons");
        System.out.println("SQL Response: " + response);
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
        l.username    = "TestUser";
        l.password    = "TestPassword";
        Gson      g   = new Gson();
        String    s   = g.toJson(l);       
        
        System.out.println("JSON String: " + s);
        
        String token  = p.handle(s);
        
        
        
        BasicPost b   = new BasicPost();
        b.accessToken = token;
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
    
}
