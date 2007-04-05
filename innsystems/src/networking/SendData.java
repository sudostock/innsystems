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
public class SendData extends Thread {
    private boolean stop;
    private double[] data;
    private int gen;
    private double particle;
    private double numInput;
    private double numHidden;
    private double learnrate;
    private netController controller;
    private InetAddress client;
    private Thread t;
    
    public SendData(netController controller) {
        this.controller = controller;
        stop = false;
        t = new Thread(this);
        t.start();
        
    }
    
    public void run() {
        System.out.println("Running send");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(!stop) {
            
            gen = controller.getGeneration();
            System.out.println("Send Generation " + gen);
            data = controller.retrieveTestData();
            particle = data[0];
            numInput = data[1];
            numHidden = data[2];
            learnrate = data[3];
            
            client = controller.pullQClient();
            System.out.println("Ready to send to "+client);
            sendC(client, gen, particle, numInput, numHidden, learnrate);
            System.out.println("Sent");
        }
    }
    
    private void sendC(InetAddress client, int generation, double particle, double numInput, double numHidden, double learnrate) {
        DataOutputStream out;
        Socket sock = null;
        try {
            sock = new Socket(client, 7780);
            out = new DataOutputStream(sock.getOutputStream());
            out.writeDouble(generation);
            out.writeDouble(particle);
            out.writeDouble(numInput);
            out.writeDouble(numHidden);
            out.writeDouble(learnrate);
            out.flush();
            out.close();
            sock.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
          
}
