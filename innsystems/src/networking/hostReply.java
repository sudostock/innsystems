/*
 * hostReply.java
 *
 * Created on February 24, 2007, 3:27 PM
 *
 *
 */

package networking;

import java.io.*;
import java.net.*;
import networking.netHost;

/**
 * Class creates a thread to listen for connections for the results of the test
 * runs
 *
 * @author Alex Filby
 * @version 0.0.1 2/24/07
 */
public class hostReply implements Runnable{
    public boolean listen;
    private int numClients;
    private ServerSocket sock;
    private int portListen = netComm.hostConnListen;
    
    public hostReply(int numClients) {
        this.numClients = numClients;
        Thread t = new Thread();
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
                    netHost.fromRY(s);
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

  
    
}
