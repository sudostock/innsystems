/*
 * clientC.java
 *
 * Created on February 24, 2007, 4:21 PM
 *
 *
 */

package networking;

import java.io.*;
import java.net.*;

/**
 * Creates a thread that listens for incoming data from the host controller then
 * passes the connection to the main program to retrieve the data in the stream
 *
 * @author Alex Filby
 * @version 0.0.1 2/24/07
 */

public class clientC implements Runnable {
    private final int portListen = netComm.clientListen;
    private ServerSocket sock;
    public static boolean listen;
    
    public clientC() {
        
    }

    public void run() {
        try{
            sock = new ServerSocket(portListen);
            listen = true;
            System.out.println("Client Listening on port 7777...");
        }catch(IOException e) {
            e.printStackTrace();
        }
        while(listen) {
            try {
                Socket s = sock.accept();
                // Pass socket to another class to deal with sending data back
                s.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
        
    }
    
    
    
}
