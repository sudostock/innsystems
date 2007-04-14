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
 */
public class Methods {
    //minimum # of Input and Hidden neurons, x and y values
    private final int I = 2;
    private final int H = 3;
    private final int num_line_particles = 7;
    private final int random_factor = 25;
    private final double inertial = .8;//.8
    private final double independence = .5;//.4
    private final double social = .5;//.7
    private final int num_particles;
    private final int num_neighbors;
    private final int lowInodes=5;
    private final int highInodes=30;
    private Particles P;
    
    
    public Methods(Particles particle) {
        P = particle;
        num_particles = P.getnumParticles();
        num_neighbors = P.getnumNeighbors();
    }
    
    private double setPosition(int n, int dim){
        int x = (n+1)*(n+1)*(dim+1);
        int j = (n+1)*5;
        double pos=0;
        
        if(n < num_line_particles) {
            pos = (int)j;
            //**********************
            if((dim+1)==3)pos = (n+1);
            
        }else{
            pos = (int)(Math.random()*random_factor);
            //**********************
            if(x==3)pos = (Math.random()*random_factor/5);
            System.out.println(pos);
        }
        return pos;
    }
    
    public void initialize(){
        //int num_particles = P.getnumParticles();
        //x=input neurons, y=hidden neurons, z=learn rate,
        double w=0, x=0, y=0, z=0;
        int r=0;
        
        double coordinates[] = new double[3];
        //initialize coordinates[][]
        for (int j=0; j<3; j++)coordinates[j]=0;
        
        //initialize particle positions
        for(int a=0; a< num_particles; a++){
            //*dimension adjusting functionality to be included after initial project completion
            for(int b=0; b<3; b++){
                coordinates[b]= setPosition(a,b);
            }
            P.setPosition(a,coordinates);
            constrictInodes();
        }
        
        //initialize particle velocities
        double velocity[];
        for(int k=0; k<num_particles; k++){
            velocity = new double[3];
            for(int l=0; l<3; l++){
                //*****************
                velocity[l] = (int)(3*Math.random())+1;//sets i,j,and k velocity components to random numbers
                double coord[]=P.getPosition(k);
                if(coord[l]>10)velocity[l]=velocity[l]*Math.pow(-1,(int)Math.random()*10);
            }
            P.setVelocity(k, velocity);
        }
        this.assign_neighbors();
        this.assign_fitness();
        P.setgFitness(100);
        double g[]={20,20,20};
        P.setgBest(g);
    }
    
    /* This method works as designed */
    public void assign_neighbors(){
      //  int neighborhood_size=P.getnumNeighbors();
        //int particles_num = P.getnumParticles();
        for(int i = 0; i < num_particles; i++){
            int[] neighbors = new int[num_neighbors];
            for (int j = 0; j < num_neighbors; j++){
                if (i == 0 && j == 0){
                    neighbors[j] = num_particles -1;
                }else if ((i+j)> num_particles ){
                    neighbors[j] = (i+j - 1 - num_particles);
                } else  neighbors[j] = i + j - 1;
                P.setNeighbors(i, neighbors);
            }
        }
    }
    
    /* This method works */
    public void adjust_position(){
      //  int particles_num = P.getnumParticles();
        for (int i = 0; i < num_particles; i++){
            double[] posit = P.getPosition(i);
            double[] vel = P.getVelocity(i);
            double[] newpos = new double[3];
            newpos[0] = posit[0]+vel[0];
            newpos[1] = posit[1]+vel[1];
            newpos[2] = posit[2]+vel[2];
            for(int j=0; j<3; j++){
                if(newpos[j]<0){
                    newpos[j]=(int)(Math.abs(10*Math.random()-5));
                }
            }
            P.setPosition(i,newpos);
            constrictInodes();
        }
    }
    
    public void adjust_velocity(){
      //  int num_particles = P.getnumParticles();
        
        for(int a = 0; a< num_particles; a++){
            double velocity[] = P.getVelocity(a);
            double nvelocity[]= new double[3];
            double pos[]= P.getPosition(a);
            double pbest[]= P.getpBest(a);
            double nbest[]= P.getnBest(a);
            double gbest[]= P.getgBest();
            double ddelta = 0;
            double delta = 0;
            
            for(int b=1; b<4; b++){
                if(b==3){
                    
                    //new velocity= (inertial)(velocity)+(social)*random()*(nbest[d]-position[d])+(independence)*random()*(pbest[d]-position[d])
                    //nbest[b-1]-pos[b-1]+gbest[b-1]-pos[b-1])/2
                    ddelta = (independence*Math.random()*(pbest[b-1]-pos[b-1])+social*Math.random()*(nbest[b-1]-pos[b-1]+gbest[b-1]-pos[b-1])/2);
                    nvelocity[b-1]= inertial*velocity[b-1] + ddelta;
                }else{
                    //new velocity= (inertial)(velocity)+(social)*random()*(nbest[d]-position[d])+(independence)*random()*(pbest[d]-position[d])
                    delta = (independence*Math.random()*(pbest[b-1]-pos[b-1])+social*Math.random()*(nbest[b-1]-pos[b-1]+gbest[b-1]-pos[b-1])/2);
                    nvelocity[b-1]= (int)(inertial*velocity[b-1] + delta);
                }
            }
            
            for(int c=1; c<4; c++){
                if(c==3){
                    if(nvelocity[c-1]<-10 || nvelocity[c-1]>10){
                        nvelocity[c-1]=((Math.random() * 20)-10);
                        //((Math.random() * 10)-5)
                        //(int)(5*Math.random()*Math.pow(-1, (int)10*Math.random()))
                    }
                }else{
                    if(nvelocity[c-1]<-10 || nvelocity[c-1]>10){
                        nvelocity[c-1]=(int)((Math.random() * 20)-10);
                        //(int)(5*Math.random()*Math.pow(-1, (int)10*Math.random()))
                    }
                }
            }
            P.setVelocity(a,nvelocity);
        }
        lockgBest();
    }
    
    public void adjust_velocity2(){
      //  int num_particles = P.getnumParticles();
        
        /*
         for every particle
            for i and j velocities of each particle
                adjust velocity according to equation
            for k velocity of each particle
                adjust velocity according to equation
         store new velocity component in 2dim array
         constrict velocity
         lock the global best
         
         */
    }
    
    
    /* Tested: Works */
   /* public void calculate_fitness(int Epochs, double delta, int a){
        double fitness = Epochs * delta;
        P.setFitness(a,fitness);
        if (fitness < P.getFitness(a)){
            P.setFitness(a, fitness);
            P.setpBest(a, P.getPosition(a));
        }
    }*/
    
    public void calculate_fitness(int Epochs, double delta, int a){
        double fitness = (Epochs * delta); //calculates particle's finess value for particular location
        if(fitness < P.getFitness(a)){
            P.setpBest(a, P.getPosition(a));
            // P.setFitness(a, fitness);
        }
        P.setFitness(a, fitness);
    }
    
    
    public void assign_fitness(){
      //  int particles_num = P.getnumParticles();
        for(int a = 0; a < num_particles; a++){
            P.setFitness(a , 200);
        }
    }
    
    public void calculate_gbest(){  //global, neighborhood, and personal
       // int num_particles = P.getnumParticles();
        double fitness[] = new double[num_particles];
        for(int a=0; a< num_particles; a++){
            fitness[a]=P.getFitness(a);//for each particle store its fitness value in fitness[]
        }
        Arrays.sort(fitness);//sort fitness values from lowest to highest
        
        for(int b =0; b < num_particles; b++){
            if(P.getFitness(b)==fitness[0]){//for each particle see if its value corresponds to the lowest value
                double co[] = P.getpBest(b);//gets coordinates of particle with lowest fitness value
                //P.setgBest(co);
                //if(P.getgBest()>P.getpBest(b))P.setgBest(co);
                if(P.getgFitness()>P.getFitness(b))P.setgBest(co);
                if(P.getgFitness()>P.getFitness(b))P.setgFitness(fitness[0]);
                break;
            }
        }
    }
    public void lockgBest(){
       // int particles_num = P.getnumParticles();
        double gbest = P.getgFitness();
        for(int i=0; i< num_particles; i++){
            if(P.getFitness(i)==gbest){
                double[] vel = { 0, 0, 0 };
                P.setVelocity(i, vel);
            }
        }
        
    }
    
    public void calculate_nbest(){// neighborhood
       // int num_particles = P.getnumParticles();
        double fitness[]=new double[num_particles];
        for(int a=0; a< num_particles; a++){
          //  int num_neighbors = P.getnumNeighbors();
            for (int b = 0; b< num_neighbors; b++){
                fitness[b]=P.getFitness(b);
                Arrays.sort(fitness);
                //The for loop below was using 'b' also, which could be what caused the confusion
                for(int c = 0; c< num_particles; c++){
                    if(P.getFitness(c)==fitness[0]){
                        double co[]= P.getpBest(c);
                        //P.setnBest(a, co);
                        if(P.getnFitness()>P.getFitness(b))P.setnBest(a, co);
                        if(P.getnFitness()>P.getFitness(b))P.setnFitness(fitness[0]);
                        break;
                    }
                    
                }
            }
        }
    }
    
    /** Debug method that returns the particle array contained inside the particles class */
    public void debug() {
        P.debug();
    }
    
    public double[] getgBest(){
        double c[]= P.getgBest();
        return c;
    }
    
    public void debug2(){
        P.debuggBest();
    }
    
    public void printParticles() {
        String view[][] = new String[100][100];
        
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                view[i][j] = " ";
            }
        }
        
        int particles = P.getnumParticles();
        
        for(int i = 0; i < particles; i++) {
            double pos[] = new double[3];
            pos = P.getPosition(i);
            
            view[(int)pos[1]][(int)pos[0]] = "X";
        }
        System.out.println("-------------");
        System.out.println(" X and Y graph");
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                System.out.print(view[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("----------");
        view = new String[100][100];
        
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                view[i][j] = " ";
            }
        }
        
        
        
        for(int i = 0; i < particles; i++) {
            double pos[] = new double[3];
            pos = P.getPosition(i);
            
            view[(int)pos[2]][(int)pos[0]] = "X";
        }
        System.out.println("-------------");
        System.out.println(" X and Z graph");
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                System.out.print(view[i][j] + "\t");
            }
            System.out.println("");
        }
        
    }
    
    public void constrictInodes(){
        double[] ipos;
        double[] temp;
        for(int i =0; i<P.getnumParticles(); i++){
           temp = P.getPosition(i); 
           if(temp[0]>30 || temp[0]<5){
               temp[0] = 30 - 25*Math.random();
           }
           P.modifyX((int)temp[0], i);
        }       
    }    
    
    
}
