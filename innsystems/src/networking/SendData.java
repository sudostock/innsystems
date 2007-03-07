/*
 * SendData.java
 *
 * Created on March 5, 2007, 8:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package networking;


import java.io.*;
import java.net.*;

/**
 * Class takes data from the netController class and sends it to the appropriate client
 *
 * @author Alex Filby
 */
public class SendData implements Runnable {
    private boolean stop;
    private double[] data;
    private double particle;
    private double numInput;
    private double numHidden;
    private double learnrate;
    private netController controller;
    private InetAddress client;
    
    public SendData(netController controller) {
        this.controller = controller;
        stop = false;
        Thread t = new Thread(this);
        t.start();
    }
    
    public void run() {
        while(!stop) {
            data = controller.retrieveTestData();
            particle = data[0];
            numInput = data[1];
            numHidden = data[2];
            learnrate = data[3];
            
            client = controller.pullQClient();
            sendC(client, particle, numInput, numHidden, learnrate);
        }
    }
    
    private void sendC(InetAddress client, double particle, double numInput, double numHidden, double learnrate) {
        DataOutputStream out;
        Socket sock = null;
        try {
            sock = new Socket(client, 7778);
            out = new DataOutputStream(sock.getOutputStream());
            out.writeDouble(particle);
            out.writeDouble(numInput);
            out.writeDouble(numHidden);
            out.writeDouble(learnrate);
            out.flush();
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
}
