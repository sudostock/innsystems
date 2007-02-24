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
 *
 */
public class Methods {
    //minimum # of Input and Hidden neurons, x and y values
    private final int I = 2;
    private final int H = 3;
    private final int num_line_particles = 10;
    private final int random_factor = 25;
    /** Creates a new instance of Methods */
    public Methods() {
    }
    
    public double setPosition(int n, int dim){
        int x = (n+1)*(n+1)*(dim+1);
        int j = (n+1)*5;
        int pos=0;
        
        if(n < num_line_particles) {
            pos = j;
            if(x%3==0)pos = (n+1);
            
        }else{
            pos = (int)(Math.random()*random_factor);
            if(x%3==0)pos = (int)(Math.random()*random_factor/5);
        }
        return pos;
    }
    
    public void initialize(int num_particles){
        //x=input neurons, y=hidden neurons, z=learn rate,
        double w=0, x=0, y=0, z=0;
        int r=0;
        
        double coordinates[][] = new double[num_particles][3];
        //initialize coordinates[][]
        for(int i=0; i<num_particles; i++){
            for (int j=0; j<3; j++)coordinates[i][j]=0;
        }
        
        //initialize particle positions
        for(int a=0; a<num_particles; a++){
            //*dimension adjusting functionality to be included after initial project completion
            for(int b=0; b<3; b++){
                coordinates[a][b]= setPosition(a,b);
            }
        }
        
       //initialize particle velocities
       double velocity[][] = new double[num_particles][3];
       for(int k=0; k<num_particles; k++){
           for(int l=0; l<3; l++){
               velocity[k][l] = Math.random();//sets i,j,and k velocity components to random numbers
           }
       }
        
    }
    
    public void assign_neighbors(Particles part){
        int neighborhood_size=part.getnumNeighbors();
        for(int i = 0; i < part.getnumParticles() + 1; i++){
            int[] neighbors = new int[neighborhood_size];
            for (int j = 0; j < neighborhood_size; j++){
                if (i == 0 && j == 0){
                    neighbors[j] = part.getnumParticles();
                } else if (i == part.getnumParticles() && (i+j)> part.getnumParticles()){
                    neighbors[j] = (i+j-part.getnumParticles());
                } else  neighbors[j] = i + j - 1;
                
            }
        }
    }
    
    public void adjust_position(){
        
    }
    
    public void adjust_velocity(int num_particles){
        double velocity[][]=new double[num_particles][3];//dimensions=3
        
    }
    
    public void calculate_fitness(){
        
    }
    
    public void constrict(){
        //"constricts" velocity and fitness values to within a certain range
        
    }
    
    public void calculate_best(){
        
    }
    
    
    
    
}
