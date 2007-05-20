/*
 * Master.java
 *
 * Created on March 1, 2007, 8:15 PM
 *
 *
 */

package pso;

import networking.netController;
import java.io.*;



/**
 *
 * @author Kevin Beale
 *
 */

public class Master implements Runnable {
    private Particles P;
    private Methods PSO;
    private String filename;
    private boolean stop;
    private int maxEpochs;
    private BufferedWriter outFile = null;
    netController netC;
    
    /** Creates a new instance of Master */
    public Master(int particles, int neighbors, int maxEpochs, String filename, netController netC) {
        P = new Particles(particles, neighbors);
        this.maxEpochs = maxEpochs;
        this.netC = netC;
        this.filename = filename;
        PSO = new Methods(P);
        Thread t = new Thread(this);
        stop = false;
        t.start();
        
        try {
            outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public void run() {
        PSO.initialize();
        double[] results = null;
        for(int i=0; i<maxEpochs; i++){
            netC.setGeneration(i);
            netC.storeTestData(P.getxyz());
            System.out.println("Sending Test Data: Generation " + i);
            getResults(netC.getResults());
            System.out.println("Calculating Results...");
            PSO.calculate_gbest();
            PSO.calculate_nbest();
            
            PSO.adjust_velocity();
            PSO.adjust_position();
            results = P.getgBest();
            try {
                //System.out.println("Epoch["+i+"] gbest: "+P.getgFitness());
                outFile.write("Epoch["+i+"] gbest: "+P.getgFitness());
                outFile.newLine();
                outFile.write("Position " + results[0] + " " + results[1]
                        + " " + results[2]);
                outFile.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(stop) {
                try {
                    outFile.flush();
                    outFile.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            }
        }
        try {
            outFile.flush();
            outFile.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Finished Run");
        netC.finished();
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






