package pso;

/**
 *
 * @author alex
 */

import pso.Methods;

public class Test {
    
    /** Creates a new instance of Test */
    public Test() {
    }
    
 
 /**************************************************************************/
 /***************** Information *********************************************/
 /* Keep log of test data here, any major errors list on our issue tracker
  * I made changes to the methods class look them over. I tried creating a Particles
  * object and then a methods object from 5 to 10000 particles. It creates and preforms
  * the calculations in a timely manner... I dont know if its correct so it needs to be 
  * checked. */
 
    public static void main(String[] args) {
       
        // First test using 5 Particles and 3 Neighbors
        Particles part = new Particles(5, 3);
        Methods pso = new Methods(part);
        pso.initialize();
        pso.debug();
        System.out.println("___________________");
        System.out.println("\n");
        
        Particles part1 = new Particles(100000, 3);
        Methods pso1 = new Methods(part1);
        pso1.initialize();
       // pso1.debug();
    }
    
}
