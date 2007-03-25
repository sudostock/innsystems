/*
 * ClientSend.java
 *
 *
 */

package networking;

import java.io.*;
import java.net.*;


/**
 * This class returns the values from the testing of the neural net to the controller on the network
 *
 * @author Alex Filby
 */
public class ClientSend implements Runnable {
    private clientComm client;
    private InetAddress server;
    private final int port = 7780;
    private boolean stop;
    
    
    public ClientSend(clientComm client, InetAddress server) {
        this.client = client;
        this.server = server;
        stop = false;
        Thread t = new Thread(this);
        t.start();
        
    }
    
    public void run() {
        double[] testResults;
        
        while(!stop) {
            testResults = client.getTestResults();
            sendData(testResults);
        }
        
    }
    
    private void sendData(double[] testResults) {
        Socket sock;
        DataOutputStream out;
        
        try {
            
            sock = new Socket(server, port);
            out = new DataOutputStream(sock.getOutputStream());
            out.writeInt((int) testResults[0]);
            out.writeInt((int) testResults[1]);
            out.writeDouble(testResults[2]);
            out.flush();
            out.close();
            sock.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void stop() {
        stop = true;
    }
    
    
    
}
