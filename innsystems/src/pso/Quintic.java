/*
 * Quintic.java
 *
 * Created on March 5, 2007, 11:52 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pso;

/**
 *This class will attempt to implement the PSO algorithm from the Methods and Particles
 *classes to solve a quintic equation of the form:
 *f(x,y,z)= x^5 + y^4 + z^3 + x^2*y^2*z^2 + xyz -15x + 7y - z = 0
 *
 *
 * @author Kevin Beale
 */
public class Quintic {
    
    /** Creates a new instance of Quintic */
    public Quintic() {
    }
    
    public static void main(String[] args) {
        Particles P = new Particles(15, 5);
        Methods M = new Methods(P);
        
        M.initialize();
        M.assign_neighbors();
        M.assign_fitness();
        
        //getResults(data);
        for(int i=0; i<100; i++){
            for(int a=0; a<P.getnumParticles(); a++){
                double[][] coordinates = P.getxyz();
                
                //M.calculate_fitness() Epochs, Error, particle number;
                //Epochs =1 delta = ? partic# = a
                //x^5 + y^4 + z^3 + x^2*y^2*z^2 + xyz -15x + 7y - z = 0
                /*
                 *function test(particle) {
                 *x = particle.current[1]
                 *y = particle.current[2]
                 *z = particle.current[3]
                 *w = particle.current[4]
                 *f = 5*(x^2) + 2*(y^3) – (z/w)^2 + 4
                 *if (x*y) = 0 then n = 1 else n = 0
                 *return 0 – abs(f) - n */
                
            }
            
        }
        
        M.calculate_gbest();
        M.calculate_nbest();
        
        M.adjust_velocity();
        M.adjust_position();
    }
    public double getfitness(){
        double fit=0;
        return fit;
    }
    
    
}











