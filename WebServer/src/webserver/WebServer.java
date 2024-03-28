/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 //https://www.elechart.com/

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
import java.nio.file.Path;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.concurrent.Executors;
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
        System.setProperty("http.keepAlive", "false");
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
            httpsServer.setExecutor(Executors.newCachedThreadPool()); // creates a default executor
            httpsServer.start();
        } catch (Exception exception) {
            System.out.println("Failed to create HTTPS server on port " + PORT + " of localhost");

        }
        System.out.println("Server Started");
    }
    
    public class NetworkRequestThread implements Runnable {

        HttpExchange t;
        Handler h;
        String type;
        
        public NetworkRequestThread(String type, Handler h, HttpExchange t) {
            this.t = t;
            this.h = h;
            this.type = type;
        }
        
        @Override
        public void run() {
            if (type.equals("Post"))
                new Handler().handlePost(t);
            else
                new Handler().handleGet(t);
        }
        
    }
    
    //Handles HTTP Requests
    class Handler implements HttpHandler {
        
        
        public Handler() {
        }
        
        @Override
        public void handle(HttpExchange t) throws IOException {
            
            System.out.println("Received Exchange Type " + t.getRequestMethod());
            Long start = System.currentTimeMillis();
            
            if (t.getRequestMethod().equals("POST")) {
                NetworkRequestThread grt = new NetworkRequestThread("Post",this, t);
                Thread th = new Thread(grt);
                th.start();                
                //handlePost(t);
            }
            
            else if (t.getRequestMethod().equals("GET")) {
                NetworkRequestThread grt = new NetworkRequestThread("Get",this, t);
                Thread th = new Thread(grt);
                th.start();
                //handleGet(t);
            }
            
            System.out.println("Finished in " + (System.currentTimeMillis()-start) + "ms");
            
        }
        
        /* Handles GET Calls From the Client. Assumes that all HTML files (and only HTML files) reside under the "html" directory.
           An HTML page name with a ".html" extension in a URL is optional. */
        public void handleGet(HttpExchange t) {
            final Path relativePath = Path.of(t.getRequestURI().getPath());
            final String parentPath = relativePath.getParent().toString();
            final String desiredPath = "html" + relativePath + ((parentPath.equals("\\") || parentPath.equals("/")) && !relativePath.toString().endsWith(".html") ? ".html" : "");

            System.out.println("Handling GET: " + relativePath);

            final File file = new File(desiredPath);
            
            try (OutputStream os = t.getResponseBody()) {
                t.sendResponseHeaders(200, file.length());
                Files.copy(file.toPath(), os);
                os.flush();
                os.close();
            }
            catch (IOException e) {
                System.out.println(e.getClass().getCanonicalName() + " thrown in handle get: " + e.getMessage());
                sendNoContentResponse(t);
            }
        }
        
        private void sendNoContentResponse(HttpExchange t) {
            try {
                t.sendResponseHeaders(204, 0L);
            } catch (IOException ex) {
                
            }
        }
        
        //Handles POST calls to the Server
        public void handlePost(HttpExchange t) {
            String json = "";
            try {
                json = new String(t.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            }
            catch(IOException e) {}

            System.out.println("Handling POST: " + json);

            BasicResponse response     = POSTHANDLER.handle(json);
            String        jsonResponse = new Gson().toJson(response);
            //System.out.println("JSON Response: " + jsonResponse);
            try {
                t.sendResponseHeaders(200, jsonResponse.length());
                OutputStream os = t.getResponseBody();
                os.write(jsonResponse.getBytes());
                os.close();
            }
            catch (IOException f){
                System.out.println("EXCEPTION THROWN IN HANDLE POST");
            }
            
        }
        
    }    
    
}
