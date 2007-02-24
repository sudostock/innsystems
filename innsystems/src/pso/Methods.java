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
    private final double inertial = .8;
    private final double independence = .45;
    private final double social = .5;
    /** Creates a new instance of Methods */
    public Methods() {
    }
    
    private double setPosition(int n, int dim){
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
    
    public void initialize(Particles P, int num_particles){
        //x=input neurons, y=hidden neurons, z=learn rate,
        double w=0, x=0, y=0, z=0;
        int r=0;
        
        double coordinates[] = new double[3];
        //initialize coordinates[][]
        for (int j=0; j<3; j++)coordinates[j]=0;
        
        
        //initialize particle positions
        for(int a=0; a<num_particles; a++){
            //*dimension adjusting functionality to be included after initial project completion
            for(int b=0; b<3; b++){
                coordinates[b]= setPosition(a,b);
            }
            P.setPosition(a,coordinates);
        }
       
       //initialize particle velocities
       double velocity[]; 
       for(int k=0; k<num_particles; k++){
          velocity = new double[3];
           for(int l=0; l<3; l++){
               velocity[l] = Math.random();//sets i,j,and k velocity components to random numbers
           }
          P.setVelocity(k, velocity);
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
    
    public void adjust_position(Particles p){
        for (int i = 0; i < p.getnumParticles(); i++){
            double[] posit = p.getPosition(i);
            double[] vel = p.getVelocity(i);
            double[] newpos = new double[3];
            newpos[0] = posit[0]+vel[0];
            newpos[1] = posit[1]+vel[1];
            newpos[2] = posit[2]+vel[2];
            p.setPosition(i, newpos);
        }
    }
    
    public void adjust_velocity(int num_particles, Particles P){
        
        for(int a = 0; a<num_particles; a++){
            double velocity[] = P.getVelocity(a);
            double nvelocity[]= new double[3]; 
            double pos[]= P.getPosition(a);
            double pbest[]= P.getpBest(a);
            double nbest[]= P.getnBest();
            double gbest[]= P.getgBest();
            
            for(int b=0; b<3; b++){
                if(b%2==0){
                //new velocity= (inertial)(velocity)+(social)*random()*(nbest[d]-position[d])+(independence)*random()*(pbest[d]-position[d])
                double delta = velocity[b]+independence*Math.random()*(pbest[b]-pos[b])+social*Math.random()*(nbest[b]-pos[b]+gbest[b]-pos[b])/2;
                nvelocity[b]= inertial*velocity[b] + delta;
                }else{
                      //new velocity= (inertial)(velocity)+(social)*random()*(nbest[d]-position[d])+(independence)*random()*(pbest[d]-position[d])
                double delta = (int)(velocity[b]+independence*Math.random()*(pbest[b]-pos[b])+social*Math.random()*(nbest[b]-pos[b]+gbest[b]-pos[b])/2);
                nvelocity[b]= inertial*velocity[b] + delta;
                }
              
                //(inertial*velocity[b]+independence*Math.random())
            }
            P.setVelocity(a,nvelocity);
        } 
    }
    
    public void calculate_fitness(){
        
    }
    
    public void constrict_xy(){
        //"constricts" velocity and fitness values to within a certain range >>"delta"
        
    }
    
    public void constrict_z(){
        
    }
    
    public void calculate_best(){//global, neighborhood, and personal
        
    }
    
    
    
    
}
