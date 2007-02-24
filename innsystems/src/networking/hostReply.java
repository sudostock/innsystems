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

/**
 * Class creates a thread to listen for connections for the results of the test
 * runs
 *
 * @author Alex Filby
 * @version 0.0.1 2/24/07
 */
public class hostReply implements Runnable{
    public static boolean listen;
    private int numClients;
    private ServerSocket sock;
    private int portListen = netComm.hostConnListen;
    
    public hostReply(int numClients) {
        this.numClients = numClients;
    }

    public void run() {
        try{
            sock = new ServerSocket(portListen);
            listen = true;
            System.out.println("Host Listening on port 7776...");
        }catch(IOException e) {
            e.printStackTrace();
        }
        while(listen) {
            try {
                Socket s = sock.accept();
                // Pass socket to another class to deal with retriving data
                s.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
           
        }
    }
    
}
