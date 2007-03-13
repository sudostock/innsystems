/*
 * clientComm.java
 *
 * Created on February 28, 2007, 10:53 PM
 *
 *
 */

package networking;

import java.io.*;
import java.net.*;

/**
 * Adjusted for known server
 *
 * @author Alex Filby
 */
public class clientComm implements Runnable {
    private InetAddress server;
    private ServerSocket recieve;
    private boolean stop;
    private DataInputStream in;
    
    
    public clientComm(String ServerIP) {
        try {
            server = InetAddress.getByName("10.10.31.15");
            //  server = InetAddress.getByName(ServerIP);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        Thread t = new Thread(this);
        stop = false;
        t.start();
        
    }
    
    public void run() {
        Socket sock;
        try {
            
            sock = new Socket(server, 7776);
            sock.close();
            System.out.println("Socket Closed");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        
        /* Rest of Code */
        
        
        try {
            System.out.println("Starting Socket");
            recieve = new ServerSocket(7780);
            stop = false;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        try{
        recieve.setSoTimeout(5);
        }catch(SocketException e) {
            e.printStackTrace();
        }
        System.out.println("Ready to recieve");
        while(!stop) {
            try{
                Socket s = recieve.accept();
                System.out.println("Right here");
                msg(s);
                s.close();
            }catch(SocketTimeoutException e) {
                
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    private void msg(Socket s) {
        double particle;
        double numInput;
        double numHidden;
        double learnrate;
        System.out.println("About to get data!");
        try {
            in = new DataInputStream(s.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        try{
            particle = in.readDouble();
            System.out.println(particle);
            numInput = in.readDouble();
            System.out.println(numInput);
            numHidden = in.readDouble();
            System.out.println(numHidden);
            learnrate = in.readDouble();
            System.out.println(learnrate);
            System.out.println("done, recieved test data!");
            in.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    
    private void runNet() {
        
    }
    
    
    
    
}
