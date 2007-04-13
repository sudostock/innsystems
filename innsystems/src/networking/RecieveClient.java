/*
 * RecieveClient.java
 *
 * Created on March 17, 2007, 11:42 PM
 *
 *
 */

package networking;

import java.net.*;
import java.io.*;

/**
 * This class is used to recieve all the incoming communications for the client
 *
 * @author Alex Filby
 */
public class RecieveClient implements Runnable {
    private clientComm cComm;
    private final int listenport = 7780;
    private ServerSocket sock;
    private boolean stop;
    private DataInputStream in;
    
    
    public RecieveClient(clientComm cComm) {
        this.cComm = cComm;
        Thread t = new Thread(this);
        stop = false;
        t.start();
    }
    
    public void run() {
        try {
            
            sock = new ServerSocket(listenport);
            sock.setSoTimeout(5);
        } catch(SocketException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        while(!stop) {
            
            try{
                Socket s = sock.accept();
                msg(s);
                s.close();
            }catch(SocketTimeoutException e) {
                
            }catch(Exception e) {
                
            }
        }
        
        
    }
    
    
    private void msg(Socket s) {
        double generation;
        double particle;
        double numInput;
        double numHidden;
        double learnrate;
        try {
            in = new DataInputStream(s.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        try{
            generation = in.readDouble();
            System.out.println(generation);
            particle = in.readDouble();
            System.out.println(particle);
            numInput = in.readDouble();
            System.out.println(numInput);
            numHidden = in.readDouble();
            System.out.println(numHidden);
            learnrate = in.readDouble();
            System.out.println(learnrate);
            in.close();
            
            double[] testData = new double[5];
            testData[0] = generation;
            testData[1] = particle;
            testData[2] = numInput;
            testData[3] = numHidden;
            testData[4] = learnrate;
            cComm.addTestData(testData);
                        
        }catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void stop() {
        stop = true;
    }
    
}
