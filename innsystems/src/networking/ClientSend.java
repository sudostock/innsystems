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
    private InetAddress server;
    private int particle;
    private int epoch;
    private  double error;
    private final int port = 7778;
    
    
    public ClientSend(InetAddress server, int particle, int epoch, double error) {
        this.server = server;
        this.particle = particle;
        this.epoch = epoch;
        this.error = error;
        
        Thread t = new Thread(this);
        t.start();
        
    }

    public void run() {
        
        Socket sock;
        DataOutputStream out;
        
        try {
            
            sock = new Socket(server, port);
            out = new DataOutputStream(sock.getOutputStream());
            out.writeInt(particle);
            out.writeInt(epoch);
            out.writeDouble(error);
            out.flush();
            out.close();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            sock.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
