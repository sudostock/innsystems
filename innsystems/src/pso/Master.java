/*
 * Master.java
 *
 * Created on March 1, 2007, 8:15 PM
 *
 *
 */

package pso;

import networking.netController;



/**
 *
 * @author Kevin Beale
 *
 */

public class Master implements Runnable {
    private Particles P;
    private Methods PSO;
    private boolean stop;
    private int maxEpochs;
    private double data[][];
    netController netC;
    
    /** Creates a new instance of Master */
    public Master(int particles, int neighbors, int maxEpochs, netController netC) {
        P = new Particles(particles, neighbors);
        this.maxEpochs = maxEpochs;
        stop=false;
        this.netC = netC;
        PSO = new Methods(P);
        Thread t = new Thread(this);
        t.start();
    }
    
    public void run() {
        PSO.initialize();
        
        netC.storeTestData(P.getxyz());
        
        while(!stop){
            
            data=netC.getResults();
            
            getResults(data);
            
            PSO.calculate_gbest();
            PSO.calculate_nbest();
            
            PSO.adjust_velocity();
            PSO.adjust_position();
        }
        
    }
    
    public void getResults(double results[][]){
        
        for(int b=0; b<P.getnumParticles(); b++){
            PSO.calculate_fitness((int)results[b][0], results[b][1], b);
        }
    }
    
    public void stop(){
        stop = true;
    }
    
    
}






