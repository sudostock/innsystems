/*
 * TestingMethods.java
 *
 * Created on February 28, 2007, 10:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pso;

/**
 *
 * @author Kevin Beale
 */
public class TestingMethods {

    
    /** Creates a new instance of TestingMethods */
    public static void main(String[] args) {
        Particles P = new Particles(3, 2);
        Methods PSO = new Methods(P);
        
        PSO.initialize();
        
        PSO.calculate_gbest();
        
        double c[]=PSO.getgBest();
        System.out.println("Initial: ");
        
        for(int i=0; i<c.length; i++){
            System.out.println(" "+c[i]);
        }
        
        PSO.calculate_gbest();
        PSO.getgBest();
        c=PSO.getgBest();
        System.out.println("Final: ");
        
            for(int i=0; i<c.length; i++){
              System.out.println(" "+c[i]);
         }
    }
}
   
