/*
 * MultiRun.java
 *
 * Created on May 20, 2007, 2:27 PM
 *
 * Allows one to take multiple test sets and run them all in sequence without having to reset everything
 */

package networking;

import java.io.*;
import java.util.StringTokenizer;
import pso.*;

/**
 *
 * @author Alex Filby
 */
public class MultiRun implements Runnable {
    private netController control;
    private RecieveComm rec;
    private SendData sendD;
    private Master pso;
    private int[][] testdata;
    private int particles;
    private int neighbors;
    private int epochs;
    private int lengthA;
    
    public MultiRun(String testSet) {
        init(testSet);
    }
    
    private void init(String testSet) {
        BufferedReader inFile = null;
        try {
            inFile = new BufferedReader(new InputStreamReader(new FileInputStream(testSet)));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        String num = null;
        try {
            num = inFile.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        lengthA = Integer.parseInt(num);
        testdata = new int[lengthA][3];
        
        for(int i = 0;; i++) {
            String data = null;
            try {
                data = inFile.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(data == null) break;
            
            StringTokenizer token = new StringTokenizer(data, " ,\t");
            testdata[i][0] = Integer.parseInt(token.nextToken());
            testdata[i][1] = Integer.parseInt(token.nextToken());
            testdata[i][2] = Integer.parseInt(token.nextToken());
        }
        try {
            inFile.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        particles = testdata[0][0];
        neighbors = testdata[0][1];
        epochs = testdata[0][2];
        
        control = new netController(particles);
        broadcastS broad = new broadcastS(control);
        
    }
    
    public void run() {
        sendD = new SendData(control);
        rec = new RecieveComm(control);
        
        String filename = (particles + "_" + neighbors + "_" + epochs + ".txt");
        pso = new Master(particles, neighbors, epochs, filename, control);
        
        for(int i = 1; i < lengthA; i++) {
            control.next();
            
            particles = testdata[i][0];
            neighbors = testdata[i][1];
            epochs = testdata[i][2];
            
            control.reset_Controller(particles);
            control.resetSwit();
            filename = (particles + "_" + neighbors + "_" + epochs + ".txt");
            pso = new Master(particles, neighbors, epochs, filename, control);
        }
    }
    
    public void start() {
        Thread t = new Thread(this);
        t.start();
    }
    
}
