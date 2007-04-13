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
        stop = false;
        t.start();
    }
    
    public void run() {
        PSO.initialize();
                
        for(int i=0; i<maxEpochs; i++){
            netC.setGeneration(i);
            System.out.println("Generation " + i);
            netC.storeTestData(P.getxyz());
            
            data=netC.getResults();
            
            getResults(data);
            
            PSO.calculate_gbest();
            PSO.calculate_nbest();
            
            PSO.adjust_velocity();
            PSO.adjust_position();
            
            System.out.println("Epoch["+i+"] gbest: "+P.getgFitness());
            if(stop) break;
        }
        double[] results = P.getgBest();
        for(int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }
        
    }
    
    public void getResults(double results[][]){
        int particles_num = P.getnumParticles();
        for(int b=0; b< particles_num; b++){
            PSO.calculate_fitness((int)results[b][0], results[b][1], b);
        }
    }
    
    public void stop(){
        stop = true;
    }
    
    
}






