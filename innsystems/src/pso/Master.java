/*
 * Master.java
 *
 * Created on March 1, 2007, 8:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pso;


        
/**
 *
 * @author Kevin.Beale
 *
 */

public class Master implements Runnable {
    private Particles P;
    private Methods PSO;
    private boolean stop;
    private double data[][];
    
    
    /** Creates a new instance of Master */
    public Master(int particles, int neighbors) {
        P = new Particles(particles, neighbors);
        
        PSO = new Methods(P);
        Thread t = new Thread(this);
        t.start();
    }
    
    public void run() {
       
        PSO.initialize();
        PSO.assign_neighbors();
        while(stop){
            for(int a; a<P.getnumParticles(); a++){
                PSO.calculate_fitness(Epochs, delta, a);
            }
            PSO.calculate_gbest();
            PSO.calculate_nbest();
            
            PSO.adjust_velocity();
            PSO.adjust_velocity();
            
        }
        
    }
    
  
    
    
    
}
