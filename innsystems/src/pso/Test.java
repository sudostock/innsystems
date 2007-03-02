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
        Particles part = new Particles(50, 2);
        Methods pso = new Methods(part);
        pso.initialize();
        pso.debug();
        System.out.println("___________________");
        System.out.println("\n");
        
        /* Errors present in this method */
      //  pso.assign_neighbors();
        //pso.debug();
        
        
        //System.out.println("________________");
        //System.out.println("\n\n");
        
        /* This method works */
       // pso.adjust_position();
        //pso.debug();
        
        //System.out.println("_________");
        //System.out.println("\n\n");
        
        /* This method works */
      //  pso.calculate_fitness(1, .1, 0);
       // pso.calculate_fitness(30, .3, 1);
      //  pso.calculate_fitness(33, .5, 2);
       // pso.debug();
        
     //  pso.calculate_gbest();
      // pso.calculate_nbest();
     //  pso.debug();
      // pso.debug2();
    }
    
}
