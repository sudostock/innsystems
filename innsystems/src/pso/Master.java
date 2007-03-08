/*
 * Master.java
 *
 * Created on March 1, 2007, 8:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
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
    private double data[][];
    netController netC;
    private Thread t;
    
    /** Creates a new instance of Master */
    public Master(int particles, int neighbors, netController netC) {
        P = new Particles(particles, neighbors);
        stop=false;
        this.netC = netC;
        PSO = new Methods(P);
        t = new Thread(this);
        t.start();
    }
    
    public void run() {
        System.out.println("RUN");
        PSO.initialize();
        System.out.println("About to store test data");
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        netC.storeTestData(P.getxyz());
        
        System.out.println("hello");
        while(!stop){
            System.out.println("about to sleep!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("about to get results!!!");
            data=netC.getResults();
            System.out.println("YO !!");
            getResults(data);
            //  netC.pullQClient();
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
    
    public void stopit(){
        stop = true;
    }
    
    public Thread getThread() {
        return t;
    }
}






