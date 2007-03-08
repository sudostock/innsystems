/*
 * SimpleQuadratic.java
 *
 * Created on March 5, 2007, 8:06 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pso;

/**
 *
 * @author Kevin Beale
 *
 *This class will attempt to solve a simple equation of the form
 *sqrt(x) + sqrt(y) + sqrt(z) = 6 with the obvious and desired answer being (4,4,4)
 *
 */
public class SimpleQuadratic {
    
    /** Creates a new instance of SimpleQuadratic */
    public SimpleQuadratic() {
        
    }
    public static void main(String[] args) {
        
        Particles P = new Particles(15, 5);
        Methods M = new Methods(P);
        
        M.initialize();
        M.assign_neighbors();
        M.assign_fitness();
        
        printxyz(P);
        printvelocity(P);
        printfitness(P);
        
        for(int i=0; i<10000; i++){ //iterations
            double[][] coordinates = P.getxyz();
            for(int a=0; a<P.getnumParticles(); a++){ //for each particle...
                double x = coordinates[a][0];
                double y = coordinates[a][1];
                double z = coordinates[a][2];
                double ans = x*x*x*x*x+y*y*y*y+z*z*z;//Math.sqrt(x)+Math.sqrt(y)+Math.sqrt(z);
                double delta; //= (Math.abs(6-ans)/6)*100;
                if(ans>3){
                    delta = (ans-3)/3*100;
                }else{
                    delta = (3-ans)/3*100;
                }
                M.calculate_fitness(1,delta,a);
                
            }
            
            M.calculate_gbest();
            M.calculate_nbest();
            M.adjust_velocity();
            M.adjust_position();
            if(i==500){
                System.out.println("---------------------------------------------------\n");
                printxyz( P);
                printvelocity(P);
                printfitness(P);
                System.out.println("----------------------------------------------------\n\n");
            }
             if(i==501){
                System.out.println("---------------------------------------------------\n");
                printxyz( P);
                printvelocity(P);
                printfitness(P);
                System.out.println("----------------------------------------------------\n\n");
            }
        }
        printxyz( P);
        printvelocity(P);
        printfitness(P);
    }
    public static void printxyz(Particles P){
        double[][] coordinates1 = P.getxyz();
        for(int i=0; i<P.getnumParticles(); i++){
            for(int j=0; j<3; j++){
                System.out.println("coordinates["+i+"]["+j+"] = ]"+coordinates1[i][j]);
            }
            
        }
    }
    public static void printfitness(Particles P){
        for(int i=0; i<P.getnumParticles(); i++){
            System.out.println("fitness["+i+"] = "+P.getFitness(i));
        }
    }
    public static void printvelocity(Particles P){
        for(int i=0; i<P.getnumParticles(); i++){
            double v[] = P.getVelocity(i);
            System.out.println("velocity["+i+"] = "+v[0]+"i "+v[1]+"j "+v[2]+"k ");
        }
    }
}
