package pso;

/**
 *
 * @author Joel Wolfe
 */
public class Particles {
    private int numParticles;
    private double[][] hive; //needs renaming
    private double nBest;
    private double gBest;
    private double numNeighbors;
    
    
    public Particles() {
        
    }
    //creates the array for storing n particles
    public Particles(int inParticles, int neighbors){
        hive = new double[inParticles][12+neighbors];
        numNeighbors = neighbors;
    }
    //creates the array for storing n particles and sets one particle
    public Particles(int inParticles, int neighbors, int particle, double[] position, double[] velocity, double fitness, double tFitness, double pBest){
        hive = new double[inParticles][12 + neighbors];
        hive[particle][0] = position[0];
        hive[particle][1] = position[1];
        hive[particle][2] = position[2];
        hive[particle][6] = velocity[0];
        hive[particle][7] = velocity[1];
        hive[particle][8] = velocity[2];
        hive[particle][9] = fitness;
        hive[particle][10] = tFitness;
        hive[particle][11] = pBest;
        numNeighbors = neighbors;
    }
    //sets the current position, if there is already a current position, sets it as previous position
    public void addPosition(int particle, double[] position){
        hive[particle][3] = hive[particle][0];
        hive[particle][4] = hive[particle][1];
        hive[particle][5] = hive[particle][2];
        
        
        hive[particle][0] = position[0];
        hive[particle][1] = position[1];
        hive[particle][2] = position[2];
    }
    //returns the current position
    public double[] getPosition(int particle){
        double[] coord = new double[3];
        for (int i = 0; i < 3; i++){
            coord[i] = hive[particle][i];
        }
        return coord;
    }
    
    public double[] getPrevPosition(int particle){
        double[] coord = new double[3];
        for (int i = 3; i < 6; i++){
            coord[i] = hive[particle][i];
        }
        return coord;
    }
    //sets the velocity
    public void addVelocity(int particle, double[] velocity){
        hive[particle][6] = velocity[0];
        hive[particle][7] = velocity[1];
        hive[particle][8] = velocity[2];
    }
    //returns the velocity
    public double[] getVelocity(int particle){
        double[] coord = new double[3];
        for (int i = 6; i < 9; i++){
            coord[i] = hive[particle][i];
        }
        return coord;
    }
    //sets the fitness in terms of error
    public void addFitness(int particle, double fitness){
        hive[particle][9] = fitness;
    }
    //gets the fitness in terms of error
    public double getFitness(int particle){
        return hive[particle][9];
    }
    //sets the fitness in terms of epochs
    public void addtFitness(int particle, double tFitness){
        hive[particle][10] = tFitness;
    }
    //gets the fitness in terms of epochs
    public double gettFitness(int particle){
        return hive[particle][10];
    }
    //sets the personal best for this particle
    public void addpBest(int particle, double pBest){
        hive[particle][11] = pBest;
    }
    //gets the personal best for this particle
    public double getpBest(int particle){
        return hive[particle][11];
    }
    //sets all the initial data for one particle.
    public void addAll(int inParticles, int particle, double[] position, double[] velocity, double fitness, double tFitness, double pBest){
        hive[particle][0] = position[0];
        hive[particle][1] = position[1];
        hive[particle][2] = position[2];
        hive[particle][6] = velocity[0];
        hive[particle][7] = velocity[1];
        hive[particle][8] = velocity[2];
        hive[particle][9] = fitness;
        hive[particle][10] = tFitness;
        hive[particle][11] = pBest;
    }
    //set neighbors
    public void addNeighbors(int particle, double[] neighbors){
        for (int i = 12; i < numNeighbors+13; i++){
            hive[particle][i] = neighbors[i-12];
        }
    }
    //get neighbors
    public double[] getNeighbors(int particle){
        double[] neighbors = new double[(int)numNeighbors];
        for (int i = 12; i < numNeighbors+13; i++){
            neighbors[i-12] = hive[particle][i];
        }
        return neighbors;
    }
}
