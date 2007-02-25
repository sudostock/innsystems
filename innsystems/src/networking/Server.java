package networking;
/*
 * Server.java
 *
 * Setup a server socket connection and listens for a connection and any data
 * then transfers it to the appropriate method to handle it.
 */

/**
 *
 * @author Alex Filby
 */

import java.net.*;
import java.io.*;


public class Server implements Runnable{
    private int port = 28;
    private boolean stop = false;
    
    /** Creates a new instance of Server */
    public Server() {
        Thread t = new Thread(this);
        stop = false;
        t.start();
    }
    
    public Server(int port) {
        this.port = port;
        Thread t = new Thread(this);
        stop = false;
        t.start();
    }
    
    public void run() {
        
        System.out.println("SERVER ONLINE!!");
        try{
            System.out.println("wtf");
            ServerSocket acceptSocket;
            acceptSocket = new ServerSocket(7777);
            System.out.println("Fuck");
            while(!stop) {
                try{
                    acceptSocket.setSoTimeout(10);
                    Socket s = acceptSocket.accept();
                    //Com.fromServer(s);
                    s.close();
                }catch(SocketTimeoutException c) {
                }
            }
        }catch(SocketException e) {
            e.printStackTrace();
        }catch(IOException a) {
            a.printStackTrace();
        }
        System.out.println("Exiting...");
        
    }
    
    /** Stops the server thread */
    public void stop_Server() {
        stop = true;
    }
    
    
}
