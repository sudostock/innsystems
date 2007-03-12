/*
 * Results.java
 *
 * Created on March 12, 2007, 4:34 PM
 *
 */

package networking;

import java.util.*;

/**
 * Class is designed to handle the data coming back from the individual clients and
 * keep track of what is missing and store the data until it is ready to be passed back
 * to the master
 *
 * @author Alex Filby
 */
public class Results {
    private int particles;
    private LinkedList <Integer> resultLeft;
    private double[][] results;
    private boolean resultsB;
    
    public Results(int particles) {
        this.particles = particles;
        results = new double[particles][2];
        resultLeft = new LinkedList();
        resetList();
    }
    
    
    public synchronized void inputResults(int particle, int epochs, double error) {
        results[particle][0] = epochs;
        results[particle][1] = error;
        resultLeft.remove(new Integer(particle));
        if(resultLeft.isEmpty() == true){
            resultsB = true;
            notify();
        }
        
    }
    
    public synchronized double[][] getResults() {
        if(!resultsB) {
            try{
                System.out.println("No results yet, waiting...");
                wait();
            }catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        resultsB = false;
        resetList();
        return results;
    }
    
    public void resetList() {
        for(int i = 0; i < particles; i++) {
            resultLeft.add(i);
        }
        
    }
    
}
