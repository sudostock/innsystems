/*
 * HostComm.java
 *
 *
 */

package networking;

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

/**
 *
 * @author Alex Filby
 *
 */
public class netController {
    private int particles;
    private ArrayBlockingQueue qClients;
    private ArrayBlockingQueue dQ;
    private List lClients;
    private boolean addr;
    private boolean resultsB;
    private boolean dataS;
    private double[][] results;
    private LinkedList <Integer> resultLeft;
    
    public netController(int particles) {
        this.particles = particles;
        addr = false;
        dataS = false;
        qClients = new ArrayBlockingQueue(particles);
        dQ = new ArrayBlockingQueue(particles);
        results = new double[particles][2];
        resultLeft = new LinkedList();
        resetList();
        
        
    }
    
    public synchronized void addQClient(InetAddress address) {
        try {
            qClients.put(address);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        addr = true;
        notify();
    }
    
    public synchronized InetAddress pullQClient() {
        InetAddress tempA = null;
        System.out.println(addr);
        if(!addr)
            try{
                System.out.println("ABOUT TO WAIT FOR CLIENTS");
                wait();
            }catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        try {
            tempA = (InetAddress) qClients.take();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        if(qClients.isEmpty() == true)
            addr = false;
        else
            addr = true;
        return tempA;
        
    }
    
    /* Need to check and verify about the notify as to whether the comm thread should pause */
    public synchronized void storeResults(int particle, int epochs, double error) {
        results[particle][0] = epochs;
        results[particle][1] = error;
        resultLeft.remove(particle);
        if(resultLeft.isEmpty() == true){
            resultsB = true;
            notify();
        }
        
    }
    
    /* Check for notify() or anything else */
    public synchronized double[][] getResults() {
        System.out.println(resultsB);
        if(!resultsB) {
            System.out.println("Im in!");
            try{
                System.out.println("about to wait for RESULTS!");
                wait();
            }catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        resultsB = false;
        return results;
    }
    
    public void storeTestData(double testData[][]) {
        double testInfo[];
        for(int i = 0; i < particles; i++) {
            testInfo = new double[4];
            testInfo[0] = i;
            testInfo[1] = testData[i][0];
            testInfo[2] = testData[i][1];
            testInfo[3] = testData[i][2];
            try {
                dQ.put(testInfo);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            dataS = true;
            notify();
        }
    }
    
    public synchronized double[] retrieveTestData() {
        double[] temp = null;
        if(!dataS)
            try{
                System.out.println("about to wait for TESTDATA");
                wait();
            }catch(Exception e) {
                e.printStackTrace();
            }
        try {
            temp =(double[]) dQ.take();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        if(dQ.isEmpty())
            dataS = false;
        return temp;
    }
    
    public void resetList() {
        for(int i = 0; i < particles; i++) {
            resultLeft.add(i);
        }
    }
    
}
