/*
 * ClientSend.java
 *
 *
 */

package networking;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;


/**
 * This class returns the values from the testing of the neural net to the controller on the network
 *
 * @author Alex Filby
 */
public class ClientSend implements Runnable {
    private clientComm cComm;
    private InetAddress server;
    private final int port = 7780;
    private boolean stop;
    
    /** Creates an instance of ClientSend
     * @param cComm a netController object
     * @param InetAddress address of the server
     */
    public ClientSend(clientComm cComm, InetAddress server) {
        this.cComm = cComm;
        this.server = server;
        stop = false;
        Thread t = new Thread(this);
        t.start();
        
    }
    
    public void run() {
        double[] testResults = null;
        
        while(!stop) {
            testResults = cComm.getTestResults();
            sendData(testResults);
        }
        
    }
    
    /** Sends data in queue back to server
     * @param double[] results that have to be sent to server
     */
    private void sendData(double[] testResults) {
        Socket sock;
        DataOutputStream out;
        
        try {
            
            sock = new Socket(server, port);
            out = new DataOutputStream(sock.getOutputStream());
            out.writeInt((int) testResults[0]);
            out.writeInt((int) testResults[1]);
            out.writeInt((int) testResults[2]);
            out.writeDouble(testResults[3]);
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
