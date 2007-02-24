/*
 * Methods.java
 *
 * Created on February 24, 2007, 10:07 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pso;

/**
 * 
 * @author Kevin Beale
 */
public class Methods {
    //minimum # of Input and Hidden neurons, x and y values
    private final int I = 2;
    private final int H = 3;
    /** Creates a new instance of Methods */
    public Methods() {
    }
   
    public double setPosition(int pnum, int dim, int n){
        int x=((pnum+1)*(dim+1)*(n+1));
        int q=0;
        return q;
    }
    
   public void initilialize(int num_particles, int radius){
       //x=input neurons, y=hidden neurons, z=learn rate
       double w=0, x=0, y=0, z=0;
       int r=0;
       
       double coordinates[][] = new double[num_particles][3];
       //initialize coordinates[][]
       for(int i=0; i<num_particles; i++){
           for (int j=0; j<4; j++)coordinates[i][j]=0;
       }
       //initialize particle positions
       for(int a=0; a<num_particles; a++){
           //*dimension adjusting functionality to be included after initial project completion
           for(int b=0; b<4; b++){
              coordinates[a][b]= setPosition(a,b,r);
            }
           r++;
       }
    }
   
   public void assign_neighbors(){
       
   }
   
   public void adjust_position(){
       
   }
   
   public void adjust_velocity(){
       
   }
   
   public void calcutate_fitness(){
       
   }
   
   public void constrict(){
       
   }
   
   public void calculate_best(){
       
   }
   
   
   
   
}
