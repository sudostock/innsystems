/*
 * Methods.java
 *
 * Created on February 24, 2007, 10:07 AM
 *
 *
 */

package pso;

import java.util.Arrays;

/**
 *
 * @author Kevin Beale, Joel Wolfe
 *
 * !!!!NOTE: MADE CHANGES: I changed this for a method class to one that creates objects
 * The constructor is such that it takes a Particles object and that it. I had to change the methods
 * so they no longer needed to input a Particles object since the pointer is saved inside the methods object
 *  -Alex
 * I fixed the assign neighbors class, the setNeighbors method call was missing, also commented out a plus one
 * it was giving an area out of bounds exception
 *
 * I have checked as much as I can understand, you guys will have to perform a more comprehensive check
 *
 */
public class Methods {
    //minimum # of Input and Hidden neurons, x and y values
    private final int I = 2;
    private final int H = 3;
    private final int num_line_particles = 10;
    private final int random_factor = 25;
    private final double inertial = .8;
    private final double independence = .5;
    private final double social = .4;
    private final int num_particles;
    private Particles P;
    
    
    public Methods(Particles particle) {
        P = particle;
        num_particles = P.getnumParticles();
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
    
    public void initialize(){
        int num_particles = P.getnumParticles();
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
    
    /* Why there was a plus one Idk, check the output and make sure it wasnt needed
     * it was giving an array out of bounds exception until I commented it out.
     * ERROR: It is assigning neighbors one int off, for instance for the 7th particle it is
     * listing it as 7 instead of 6. */
    public void assign_neighbors(){
        int neighborhood_size=P.getnumNeighbors();
        for(int i = 0; i < P.getnumParticles()/* + 1*/; i++){
            int[] neighbors = new int[neighborhood_size];
            for (int j = 0; j < neighborhood_size; j++){
                if (i == 0 && j == 0){
                    neighbors[j] = P.getnumParticles();
                } else if (i == P.getnumParticles() && (i+j)> P.getnumParticles()){
                    neighbors[j] = (i+j-P.getnumParticles());
                } else  neighbors[j] = i + j - 1;
                P.setNeighbors(i, neighbors);
            }
        }
    }
    
    /* This method works */
    public void adjust_position(){
        for (int i = 0; i < P.getnumParticles(); i++){
            double[] posit = P.getPosition(i);
            double[] vel = P.getVelocity(i);
            double[] newpos = new double[3];
            newpos[0] = posit[0]+vel[0];
            newpos[1] = posit[1]+vel[1];
            newpos[2] = posit[2]+vel[2];
            for(int j=0; j<3; j++){
                if(newpos[j]<0)
                    newpos[j]=Math.abs(7*Math.random())+3;
            }
            P.setPosition(i,newpos);
        }
    }
    
    public void adjust_velocity(){
        int num_particles = P.getnumParticles();
        for(int a = 0; a<num_particles; a++){
            double velocity[] = P.getVelocity(a);
            double nvelocity[]= new double[3];
            double pos[]= P.getPosition(a);
            double pbest[]= P.getpBest(a);
            double nbest[]= P.getnBest(a);
            double gbest[]= P.getgBest();
            
            for(int b=1; b<4; b++){
                if(b%3==0){
                    //new velocity= (inertial)(velocity)+(social)*random()*(nbest[d]-position[d])+(independence)*random()*(pbest[d]-position[d])
                    double delta = velocity[b-1]+independence*Math.random()*(pbest[b-1]-pos[b-1])+social*Math.random()*(nbest[b-1]-pos[b-1]+gbest[b-1]-pos[b-1])/2;
                    nvelocity[b-1]= inertial*velocity[b-1] + delta;
                }else{
                    //new velocity= (inertial)(velocity)+(social)*random()*(nbest[d]-position[d])+(independence)*random()*(pbest[d]-position[d])
                    double delta = (int)(velocity[b-1]+independence*Math.random()*(pbest[b-1]-pos[b-1])+social*Math.random()*(nbest[b-1]-pos[b-1]+gbest[b-1]-pos[b-1])/2);
                    nvelocity[b-1]= inertial*velocity[b-1] + delta;
                }
            }
            for(int b=1; b<4; b++){
                if(b%3==0){
                    if(nvelocity[b-1]<-5 || nvelocity[b-1]>5){
                        nvelocity[b-1]=5 * Math.random() * Math.pow(-1, (int)10*Math.random());
                    }
                }else{
                    if(nvelocity[b-1]<-5 || nvelocity[b-1]>5){
                        nvelocity[b-1]=(int)(5*Math.random()*Math.pow(-1, (int)10*Math.random()));
                    }
                }
            }
            P.setVelocity(a,nvelocity);
        }
    }
    
    
    /* Tested: Works */
    public void calculate_fitness(int Epochs, double delta, int a){
        double fitness = Epochs * delta;
        P.setFitness(a,fitness);
        if (fitness < P.getFitness(a)) P.setFitness(a, fitness); P.setpBest(a, P.getPosition(a));
    }
    
    public void calculate_gbest(){//global, neighborhood, and personal
        int num_particles = P.getnumParticles();
        double fitness[]=new double[num_particles];
        for(int a=0; a<num_particles; a++){
            fitness[a]=P.getFitness(a);
        }
        Arrays.sort(fitness);
        for(int b =0; b <num_particles; b++){
            if(P.getFitness(b)==fitness[0]){
                double co[]= P.getpBest(b);
                P.setgBest(co);
            }
            break;
        }
    }
    
    /** Debug method that returns the particle array contained inside the particles class */
    public void debug() {
        P.debug();
    }
    
    //needs calculate_nbest()
    
}
