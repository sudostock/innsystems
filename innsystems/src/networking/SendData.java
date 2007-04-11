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
        while(!stop) {
            
            data = controller.retrieveTestData();
            gen = (int) data[0];
            System.out.println("Send Generation " + gen);
            particle = data[1];
            System.out.println("Send Particle #: " + particle);
            numInput = data[2];
            numHidden = data[3];
            learnrate = data[4];
            
            client = controller.pullQClient();
            System.out.println("Ready to send to "+client);
            sendC(client, gen, particle, numInput, numHidden, learnrate);
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
            System.out.println("Sent");
            //Find error codes to catch
        }catch(BindException e) {
            System.out.println(e);
            addData(generation, particle, numInput, numHidden, learnrate);
            controller.addQClient(client);
        }catch(ConnectException e) {
            System.out.println(e);
            addData(generation, particle, numInput, numHidden, learnrate);
            System.out.println("Removing client from list.");
        } catch (IOException ex) {
            System.out.println("Computer not reachable");
            ex.printStackTrace();
            addData(generation, particle, numInput, numHidden, learnrate);
            //controller.addQClient(client);
            System.out.println("Data not sent, put back in queue.");
        }
        
        
    }
    
    private void addData(double generation, double particle, double numInput, double numHidden, double learnrate) {
        double[] data = new double[5];
        data[0] = generation;
        data[1] = particle;
        data[2] = numInput;
        data[3] = numHidden;
        data[4] = learnrate;
        
        controller.addTestData(data);
    }
    
}
