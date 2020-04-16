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

    /**
     * Creates array for storing information for specified number of particles.
     * @param inParticles the desired number of particles
     * @param neighbors the desired number of neighbors
     */
    public Particles(int inParticles, int neighbors) {
        hive = new double[inParticles][16 + neighbors];
        numNeighbors = neighbors;
        numParticles = inParticles;
        depth = 16 + neighbors;

    }

    /**
     * Creates array for storing information for specified number of particles
     * and allow one particles information to be explicity set.
     * @param inParticles the desired number of particles
     * @param particle
     * @param neighbors the desired number of neighbors
     * @param position
     * @param velocity
     * @param fitness
     * @param tFitness
     * @param pBest
     */
    public Particles(int inParticles, int particle, int neighbors, double[] position, double[] velocity, double fitness, double tFitness, double[] pBest) {
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

    /**
     * Sets a new position of specified particle. Saves the previous position
     * then overwrites current position with new one.
     * @param particle index of particle
     * @param position array with new position information in the form [0] = x,
     * [1] = y, and [2] = z.
     */
    public void setPosition(int particle, double[] position) {
        hive[particle][3] = hive[particle][0];
        hive[particle][4] = hive[particle][1];
        hive[particle][5] = hive[particle][2];

        hive[particle][0] = position[0];
        hive[particle][1] = position[1];
        hive[particle][2] = position[2];
    }

    /**
     * Gets specified particle's current position.
     * @param particle index of particle
     * @return array with position information in the form [0] = x,
     * [1] = y, and [2] = z.
     */
    public double[] getPosition(int particle) {
        double[] coord = new double[3];
        coord[0] = hive[particle][0];
        coord[1] = hive[particle][1];
        coord[2] = hive[particle][2];

        return coord;
    }

    /**
     * Gets previous position of specified particle.
     * @param particle index of particle
     * @return array with previous position information in the form [0] = x,
     * [1] = y, and [2] = z.
     */
    public double[] getPrevPosition(int particle) {
        double[] coord = new double[3];
        coord[0] = hive[particle][3];
        coord[1] = hive[particle][4];
        coord[2] = hive[particle][5];
        return coord;
    }

    /**
     * Sets the velocity of specified particle.
     * @param particle index of particle
     * @param velocity array with velocity information in the form [0] = x,
     * [1] = y, and [2] = z.
     */
    public void setVelocity(int particle, double[] velocity) {
        hive[particle][6] = velocity[0];
        hive[particle][7] = velocity[1];
        hive[particle][8] = velocity[2];
    }

    /**
     * Gets the velocity information of specified particle.
     * @param particle index of particle
     * @return array with velocity information in the form [0] = x,
     * [1] = y, and [2] = z.
     */
    public double[] getVelocity(int particle) {
        double[] coord = new double[3];
        coord[0] = hive[particle][6];
        coord[1] = hive[particle][7];
        coord[2] = hive[particle][8];
        return coord;
    }

    /**
     * Sets the fitness of specified particle.
     * @param particle index of particle
     * @param fitness particle's fitness value
     */
    public void setFitness(int particle, double fitness) {
        hive[particle][9] = fitness;
    }

    /**
     * Gets fitness value of specified particle
     * @param particle index of particle
     * @return fitness value of particle
     */
    public double getFitness(int particle) {
        return hive[particle][9];
    }

    /**
     * Sets personal best position for specified particle.
     * @param particle index of particle
     * @param pBest array with personal best position information in the form
     * [0] = x, [1] = y, and [2] = z.
     */
    public void setpBest(int particle, double[] pBest) {
        hive[particle][10] = pBest[0];
        hive[particle][11] = pBest[1];
        hive[particle][12] = pBest[2];
    }

    /**
     * Gets the personal best position information for specified particle
     * @param particle index of particle
     * @return array with personal best position information in the form
     * [0] = x, [1] = y, and [2] = z.
     */
    public double[] getpBest(int particle) {
        double[] p = new double[3];
        p[0] = hive[particle][10];
        p[1] = hive[particle][11];
        p[2] = hive[particle][12];
        return p;
    }

    //sets all the initial data for one particle.
    /**
     *
     * @param particle
     * @param position
     * @param velocity
     * @param fitness
     * @param tFitness
     * @param pBest
     * @param neighbors
     */
    public void setAll(int particle, double[] position, double[] velocity, double fitness, double tFitness, double[] pBest, int[] neighbors) {
        setPosition(particle, position);
        setVelocity(particle, velocity);
        hive[particle][9] = fitness;
        hive[particle][10] = pBest[0];
        hive[particle][11] = pBest[1];
        hive[particle][12] = pBest[2];
        setNeighbors(particle, neighbors);
    }

    /**
     * Sets neighbors for specified particle. Takes neighbor information given
     * and copies it to the main array.
     * @param particle index of particle
     * @param neighbors array containing all neighbors of the particle
     */
    public void setNeighbors(int particle, int[] neighbors) {
        for (int i = 16; i < numNeighbors + 16; i++) {
            hive[particle][i] = neighbors[i - 16];
        }
    }

    /**
     * Gets all the neighbors of the specified particle.
     * @param particle index of particle
     * @return array of all particles that are neighbors of the specified
     * particle.
     */
    public int[] getNeighbors(int particle) {
        int[] neighbors = new int[numNeighbors];
        for (int i = 16; i < numNeighbors + 16; i++) {
            neighbors[i - 16] = (int) hive[particle][i];
        }
        return neighbors;
    }

    /**
     * Sets the neighborhood best for specified particle.
     * @param particle index of particle
     * @param nBest array with neighborhood best position information in the
     * form [0] = x, [1] = y, and [2] = z.
     */
    public void setnBest(int particle, double[] nBest) {
        hive[particle][13] = nBest[0];
        hive[particle][14] = nBest[1];
        hive[particle][15] = nBest[2];
    }

    /**
     * Gets the neighborhood best for specified particle.
     * @param particle index of particle
     * @return array with neighborhood best position information in the form
     * [0] = x, [1] = y, and [2] = z.
     */
    public double[] getnBest(int particle) {
        double[] nb = new double[3];
        nb[0] = hive[particle][13];
        nb[1] = hive[particle][14];
        nb[2] = hive[particle][15];
        return nb;
    }

    /**
     * Sets the global best position information, for all particles.
     * @param gBest array with best position information in the form [0] = x,
     * [1] = y, and [2] = z.
     */
    public void setgBest(double[] gBest) {
        this.gBest = gBest;
    }

    /**
     * Gets global best particle in swarm.
     * @return array with global best position information in the form [0] = x,
     * [1] = y, and [2] = z.
     */
    public double[] getgBest() {
        return gBest;
    }

    /**
     * Gets number of particles.
     * @return <code>int</code> of number of particles in the swarm
     */
    public int getnumParticles() {
        return numParticles;
    }

    /**
     * Gets number of neighbors.
     * @return <code>int</code> of number of neighbors for all particles
     */
    public int getnumNeighbors() {
        return numNeighbors;
    }

    /**
     * Prints out entire particles matrix.
     */
    public void debug() {
        for (int i = 0; i < numParticles; i++) {
            for (int j = 0; j < depth; j++) {
                System.out.print("hive[" + i + "][" + j + "] = " + hive[i][j] + ",\t");
            }
            System.out.println();
        }
    }

    /**
     * Prints out global best.
     */
    public void debuggBest() {
        System.out.println(gBest[0] + " " + gBest[1] + " " + gBest[2]);
    }

    /**
     * Gets positional information for all particles.
     * @return array with all particles and their position information in the
     * form [0] = x, [1] = y, and [2] = z.
     */
    public double[][] getxyz() {
        double[][] coordinates = new double[numParticles][3];
        for (int i = 0; i < numParticles; i++) {
            coordinates[i][0] = hive[i][0];
            coordinates[i][1] = hive[i][1];
            coordinates[i][2] = hive[i][2];
        }
        return coordinates;
    }

    /**
     * Sets the global fitness value.
     * @param gf value of global fitness
     */
    public void setgFitness(double gf) {
        gFit = gf;
    }

    /**
     * Gets the global fitness value.
     * @return global fitness value
     */
    public double getgFitness() {
        return gFit;
    }

    /* Not understanding the use of method, looks pointless */
    /**
     * Sets neighborhood fitness. Impractical as in each swarm there are going
     * to be multiple neighborhoods if number of neighbors is less than number
     * of particles.
     * @param nf neighborhood fitness value
     */
    public void setnFitness(double nf) {
        nFit = nf;
    }

    /* Not understanding the use of method, looks pointless */
    /**
     * Gets neighborhood fitness value.
     * @return neighborhood fitness value
     */
    public double getnFitness() {
        return nFit;
    }

    /**
     * 
     * @param neuvopos
     * @param p
     */
    public void modifyX(int neuvopos, int p) {
        hive[p][0] = neuvopos;
    }
}
