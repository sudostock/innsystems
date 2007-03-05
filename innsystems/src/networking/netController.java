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
    private List lClients;
    private boolean addr;
    private boolean resultsB;
    private double[][] results;
    private LinkedList <Integer> resultLeft;
    
    public netController(int particles) {
        this.particles = particles;
        addr = false;
        qClients = new ArrayBlockingQueue(particles);
        results = new double[particles][2];
        resultLeft = new LinkedList();
        initializeList();
        broadcastS clientget = new broadcastS();
        
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
        if(!addr)
            try{
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
    public void storeResults(int particle, int epochs, double error) {
        results[particle][0] = epochs;
        results[particle][1] = error;
        resultLeft.remove(particle);
        if(resultLeft.isEmpty() == true){
            resultsB = true;
            notify();
        }
        
    }
    
    /* Check for notify() or anything else */
    public double[][] getResults() {
        if(!resultsB)
            try{
                wait();
            }catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        resultsB = false;
        return results;
    }
    
    public void initializeList() {
        for(int i = 0; i < particles; i++) {
            resultLeft.add(i);
        }
    }
    
}
