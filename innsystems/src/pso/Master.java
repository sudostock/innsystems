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
        this.netC = netC;
        PSO = new Methods(P);
        Thread t = new Thread(this);
        t.start();
    }
    
    public void run() {
        PSO.initialize();
        PSO.assign_neighbors();
        PSO.assign_fitness();
        
        for(int i=0; i<maxEpochs; i++){
            netC.setGeneration(i);
            netC.storeTestData(P.getxyz());
            
            data=netC.getResults();
            
            getResults(data);
            
            PSO.calculate_gbest();
            PSO.calculate_nbest();
            
            PSO.adjust_velocity();
            PSO.adjust_position();
            
            System.out.println("Epoch["+i+"] gbest: "+P.getgFitness());
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






