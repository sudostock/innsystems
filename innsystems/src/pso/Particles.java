package pso;

/**
 *
 * @author Joel Wolfe
 */
public class Particles {
    private int numParticles;
    private int numNeighbors;
    private int depth;
    private double gFit;
    private double nFit;
    private double[] gBest;
    
    private double[][] hive; //needs renaming
    
    
    
    public Particles() {
        
    }
    //creates the array for storing n particles
    public Particles(int inParticles, int neighbors){
        hive = new double[inParticles][16+neighbors];
        numNeighbors = neighbors;
        numParticles = inParticles;
        depth = 16+neighbors;
        
    }
    //creates the array for storing n particles and sets one particle
    public Particles(int inParticles, int particle, int neighbors, double[] position, double[] velocity, double fitness, double tFitness, double[] pBest){
        hive = new double[inParticles][16 + neighbors];
        hive[particle][0] = position[0];
        hive[particle][1] = position[1];
        hive[particle][2] = position[2];
        setVelocity(particle, velocity);
        hive[particle][9] = fitness;
        hive[particle][10] = pBest[0];
        hive[particle][11] = pBest[1];
        hive[particle][12] = pBest[2];
        numNeighbors = neighbors;
    }
    //sets the current position, if there is already a current position, sets it as previous position
    public void setPosition(int particle, double[] position){
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
        coord[0] = hive[particle][0];
        coord[1] = hive[particle][1];
        coord[2] = hive[particle][2];
        
        return coord;
    }
    
    public double[] getPrevPosition(int particle){
        double[] coord = new double[3];
        coord[0] = hive[particle][3];
        coord[1] = hive[particle][4];
        coord[2] = hive[particle][5];
        return coord;
    }
    //sets the velocity
    public void setVelocity(int particle, double[] velocity){
        hive[particle][6] = velocity[0];
        hive[particle][7] = velocity[1];
        hive[particle][8] = velocity[2];
    }
    //returns the velocity
    public double[] getVelocity(int particle){
        double[] coord = new double[3];
        coord[0] = hive[particle][6];
        coord[1] = hive[particle][7];
        coord[2] = hive[particle][8];
        return coord;
    }
    //sets the fitness in terms of error
    public void setFitness(int particle, double fitness){
        hive[particle][9] = fitness;
    }
    //gets the fitness in terms of error
    public double getFitness(int particle){
        return hive[particle][9];
    }
    
    //sets the personal best for this particle
    public void setpBest(int particle, double[] pBest){
        hive[particle][10] = pBest[0];
        hive[particle][11] = pBest[1];
        hive[particle][12] = pBest[2];
    }
    
    //gets the personal best for this particle
    public double[] getpBest(int particle){
        double[] p = new double[3];
        p[0] = hive[particle][10];
        p[1] = hive[particle][11];
        p[2] = hive[particle][12];
        return p;
    }
    
    //sets all the initial data for one particle.
    public void setAll(int particle, double[] position, double[] velocity, double fitness, double tFitness, double[] pBest, int[] neighbors){
        setPosition(particle, position);
        setVelocity(particle, velocity);
        hive[particle][9] = fitness;
        hive[particle][10] = pBest[0];
        hive[particle][11] = pBest[1];
        hive[particle][12] = pBest[2];
        setNeighbors(particle, neighbors);
    }
    
    //set neighbors
    public void setNeighbors(int particle, int[] neighbors){
        for (int i = 16; i < numNeighbors+16; i++){
            hive[particle][i] = neighbors[i-16];
        }
    }
    
    //get neighbors
    public int[] getNeighbors(int particle){
        int[] neighbors = new int[numNeighbors];
        for (int i = 16; i < numNeighbors+16; i++){
            neighbors[i-16] = (int) hive[particle][i];
        }
        return neighbors;
    }
    
    
    public void setnBest(int particle, double[] nBest){
        hive[particle][13] = nBest[0];
        hive[particle][14] = nBest[1];
        hive[particle][15] = nBest[2];
    }
    public double[] getnBest(int particle){
        double[] nb = new double[3];
        nb[0] = hive[particle][13];
        nb[1] = hive[particle][14];
        nb[2] = hive[particle][15];
        return nb;
    }
    
    public void setgBest(double[] gBest){
        this.gBest = gBest;
    }
    public double[] getgBest(){
        return gBest;
    }
    public int getnumParticles(){
        return numParticles;
    }
    public int getnumNeighbors(){
        return numNeighbors;
    }
    //Print out the entire matrix
    public void debug(){
        for (int i = 0; i < numParticles; i++){
            for (int j = 0; j < depth; j++){
                System.out.print("hive["+i+"]["+j+"] = "+hive[i][j] + ",\t");
            }
            System.out.println();
        }
    }
    public void debuggBest(){
        System.out.println(gBest[0] + " " + gBest[1] + " " + gBest[2]);
    }
    public double[][] getxyz(){
        double[][] coordinates = new double[numParticles][3];
        for(int i=0; i< numParticles; i++){
            coordinates[i][0] = hive[i][0];
            coordinates[i][1] = hive[i][1];
            coordinates[i][2] = hive[i][2];
        }
        return coordinates;
    }
    public void setgFitness(double gf){
        gFit = gf;
    }
    public double getgFitness(){
        return gFit;
    }
    /* Not understanding the use of method, looks pointless */
    public void setnFitness(double nf){
        nFit = nf;
    }
    /* Not understanding the use of method, looks pointless */
    public double getnFitness(){
        return nFit;
    }
    
    public void modifyX(int neuvopos, int p){
        hive[p][0]= neuvopos;
    }
}
