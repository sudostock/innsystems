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
 * This class handles most of the controller functions
 *
 * @author Alex Filby
 *
 */
public class netController {
    private int particles;
    private int generation;
    private CDQueue  clients;
    private CDQueue  dQ;
    private List lClients;
    private boolean addr;
    private boolean resultsB;
    private boolean dataS;
    private Results result;
    
    private double[][] testData;
    
    
    public netController(int particles) {
        this.particles = particles;
        clients = new CDQueue(particles);
        dQ = new CDQueue(particles);
        result = new Results(particles);
    }
    
    public void addQClient(InetAddress address) {
        clients.put(address);
    }
    
    public InetAddress pullQClient() {
        return (InetAddress) clients.take();
    }
    
    public void storeResults(int particle, int epochs, double error) {
        result.inputResults(particle, epochs, error);
    }
    
    public double[][] getResults() {
        return result.getResults();
    }
    
    
    public void storeTestData(double testData[][]) {
        this.testData = testData;
        double testInfo[];
        for(int i = 0; i < particles; i++) {
            testInfo = new double[5];
            testInfo[0] = generation;
            testInfo[1] = i;
            testInfo[2] = testData[i][0];
            testInfo[3] = testData[i][1];
            testInfo[4] = testData[i][2];
            
            dQ.put(testInfo);
        }
        System.out.println("Stored Data!");
    }
    
    public void addTestData(double data[]) {
        dQ.put(data);
    }
    
    
    public double[] retrieveTestData() {
        return (double[]) dQ.take();
    }
    
    public void setGeneration(int generation) {
        this.generation = generation;
    }
    
    public int getGeneration() {
        return generation;
    }
    
    
}
