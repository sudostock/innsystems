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
 * This class handles most of the
 * @author Alex Filby
 *
 */
public class netController {
    private int particles;
    private CDQueue  clients;
    private CDQueue  dQ;
    private List lClients;
    private boolean addr;
    private boolean resultsB;
    private boolean dataS;
    private Results result;
    
    
    private  Thread send;
    private Thread rec;
    private Thread mas;
    
    public netController(int particles) {
        this.particles = particles;
        addr = false;
        dataS = false;
        clients = new CDQueue(particles);
        dQ = new CDQueue(particles);
        result = new Results(particles);
    }
    
    public void addQClient(InetAddress address) {
        clients.put(address);
//        try {
//            qClients.put(address);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//        addr = true;
    }
//
    public InetAddress pullQClient() {
        return (InetAddress) clients.take();
        
//        InetAddress tempA = null;
//        System.out.println(addr + "clients");
//      /*  if(!addr)
//            try{
//                System.out.println("ABOUT TO WAIT FOR CLIENTS");
//                wait();
//            }catch(InterruptedException ex) {
//                ex.printStackTrace();
//            }*/
//        try {
//            tempA = (InetAddress) qClients.take();
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//
//      /*  if(qClients.isEmpty() == true)
//            addr = false;
//        else
//            addr = true;*/
//        return tempA;
        
    }
    
    
    public void storeResults(int particle, int epochs, double error) {
        result.inputResults(particle, epochs, error);
    }
    
    public double[][] getResults() {
        return result.getResults();
    }
    
    
    public void storeTestData(double testData[][]) {
        double testInfo[];
        for(int i = 0; i < particles; i++) {
            testInfo = new double[4];
            testInfo[0] = i;
            testInfo[1] = testData[i][0];
            testInfo[2] = testData[i][1];
            testInfo[3] = testData[i][2];
            testInfo[3] = testData[i][2];
            
            dQ.put(testInfo);
        }
        System.out.println("Stored Data!");
    }
    
    
    public double[] retrieveTestData() {
        double[] temp = null;
        System.out.println("Getting Test Data to send off!");
        
        temp =(double[]) dQ.take();
        
        System.out.println("Got test data");
        return temp;
    }
    
    public void setThreads(Thread master, Thread send, Thread recieve) {
        mas = master;
        this.send = send;
        rec = recieve;
    }
    
}
