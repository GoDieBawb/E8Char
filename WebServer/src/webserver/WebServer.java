/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyStore;
import java.util.ArrayList;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManagerFactory;
import util.FileReader;
import webserver.posts.PostHandler;
import webserver.responses.BasicResponse;

/**
 *
 * @author MeanC
 */
public class WebServer {
    
//    public static void main(String[] args) throws Exception {
//        
//        System.out.println("Starting Server");
//        HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
//        server.createContext("/", new Handler());
//        server.setExecutor(null); // creates a default executor
//        server.start();
//        System.out.println("Server Started");
//        
//    }
    
    static int PORT = 80;
    private final static PostHandler POSTHANDLER = new PostHandler();
    
    private String getLoginString() {
        ArrayList<String> lines = new FileReader().getLines("pass.txt");
        for (String line : lines) {
            if (line.contains("https")) {
                return line.split(" ")[1];
            }
        }
        return null;
    }
    
    //Creates the HTTPS Server
    public void start() throws Exception {
        System.out.println("Starting Server");
        try {

            // initialise the HTTPS server
            HttpsServer httpsServer = HttpsServer.create(new InetSocketAddress(PORT), 0);
            SSLContext sslContext = SSLContext.getInstance("TLS");

            // Initialise the keystore
            char[] password = getLoginString().toCharArray();
            KeyStore ks = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream("lig.keystore");
            ks.load(fis, password);

            // setup the key manager factory
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, password);

            // setup the trust manager factory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ks);

            // setup the HTTPS context and parameters
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
                public void configure(HttpsParameters params) {
                    try {
                        // initialise the SSL context
                        SSLContext context = getSSLContext();
                        SSLEngine engine = context.createSSLEngine();
                        params.setNeedClientAuth(false);
                        params.setCipherSuites(engine.getEnabledCipherSuites());
                        params.setProtocols(engine.getEnabledProtocols());

                        // Set the SSL parameters
                        SSLParameters sslParameters = context.getSupportedSSLParameters();
                        params.setSSLParameters(sslParameters);

                    } catch (Exception ex) {
                        System.out.println("Failed to create HTTPS port");
                    }
                }
            });
            httpsServer.createContext("/", new Handler());
            httpsServer.setExecutor(null); // creates a default executor
            httpsServer.start();

        } catch (Exception exception) {
            System.out.println("Failed to create HTTPS server on port " + PORT + " of localhost");
            exception.printStackTrace();

        }
        System.out.println("Server Started");
    }
    
    //Handles HTTP Requests
    static class Handler implements HttpHandler {
        
        @Override
        public void handle(HttpExchange t) throws IOException {
            
            if (t.getRequestMethod().equals("POST")) {
                handlePost(t);
                return;
            }
            
            if (t.getRequestMethod().equals("GET")) {
                handleGet(t);
                return;
            }
            
        }
        
        //Handles GET Calls From the Client
        public void handleGet(HttpExchange t) {
            
            System.out.println("Handling GET");
            
            String path = t.getRequestURI().getPath();
            System.out.println("Path: " + path);
            
            //https://www.elechart.com/
            if (path.equals("")) path = "Login";
            
            if (path.contains("images")) {}
            
            else if (!path.toLowerCase().contains("html")) path += ".html";
            
            File file = new File("html"+path);
            
            try (OutputStream os = t.getResponseBody()) {
                t.sendResponseHeaders(200, file.length());
                Files.copy(file.toPath(), os);
            }
            catch (IOException e) {
            }
            
        }
        
        //Handles POST calls to the Server
        public void handlePost(HttpExchange t) {
            
            System.out.println("Handling POST");        
            String json = "";
            try {
                json = new String(t.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            }
            catch(IOException e) {}

            BasicResponse response     = POSTHANDLER.handle(json);
            String        jsonResponse = new Gson().toJson(response);
            System.out.println("JSON Response: " + jsonResponse);
            try {
                t.sendResponseHeaders(200, jsonResponse.length());
                OutputStream os = t.getResponseBody();
                os.write(jsonResponse.getBytes());
                os.close();
            }
            catch (IOException f){}
            
        }
        
    }    
    
}
