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
import networking.netHost;


/**
 * Class that creates a thread to listen for broadcast pings, store clientips
 * and return the server ip to the client then listen
 *
 * @author Alex Filby
 * @version 0.0.1 2/24/07
 */
public class hostBC implements Runnable {
    private boolean listen;
    private byte[] serverip;
    private ServerSocket sock;
    private final int portListen = netComm.hostListen;
    
    public hostBC() {
        Thread t = new Thread(this);
        listen = true;
        t.start();
    }
    
    public hostBC(byte[] serverip) {
        // this.serverip = serverip;
        Thread t = new Thread(this);
        listen = true;
        t.start();
    }
    
    public void run() {
        
        System.out.println("SERVER ONLINE!!");
        try{
            sock = new ServerSocket(portListen);
            while(listen) {
                try{
                    sock.setSoTimeout(10);
                    Socket s = sock.accept();
                    netHost.fromBC(s);
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
        listen = false;
    }
    
}
