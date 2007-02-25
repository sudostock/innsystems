/*
 * hostBC.java
 *
 * Created on February 24, 2007, 2:43 PM
 *
 *
 */

package networking;

import java.io.*;
import java.net.*;


/**
 * Class that creates a thread to listen for broadcast pings, store clientips
 * and return the server ip to the client then listen
 *
 * @author Alex Filby
 * @version 0.0.1 2/24/07
 */
public class hostBC implements Runnable {
    private boolean stop;
    private byte[] serverip;
    private final int portListen = netComm.hostListen;
    
    public hostBC() {
        Thread t = new Thread(this);
        t.run();
    }
    
    public hostBC(byte[] serverip) {
       // this.serverip = serverip;
        Thread t = new Thread(this);
        stop = false;
        t.run();
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
                    System.out.println("hello!!!");
                    Socket s = acceptSocket.accept();
                    //Com.fromServer(s);
                    s.close();
                }catch(SocketTimeoutException c) {
                }
                System.out.println("WTF!!!!");
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
