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
        
        Particles P = new Particles(300, 10);
        Methods M = new Methods(P);
        
        M.initialize();
        M.assign_neighbors();
        M.assign_fitness();
        
        printxyz(P);
        printvelocity(P);
        printfitness(P);
        
        for(int i=0; i<600; i++){ //iterations
            double[][] coordinates = P.getxyz();//get coordinates
            for(int a=0; a<P.getnumParticles(); a++){ //for each particle...
                double x = coordinates[a][0];
                double y = coordinates[a][1];
                double z = coordinates[a][2];
                double ans = x+y+z/*x*5*y+y*y*2+z*z*z*z*x*/;//Math.sqrt(x)+Math.sqrt(y)+Math.sqrt(z);
                if(i==9999 || i==500){
                    System.out.println("ANSWER============="+ans);
                }
                double delta = (Math.abs((6-ans)/6))*100;
                /*if(ans>6){
                    delta = ((ans-6)/6)*100;
                }else{
                    delta = ((6-ans)/6)*100;
                }*/
                M.calculate_fitness(1,delta,a);
                
            }
            
            M.calculate_gbest();
            M.calculate_nbest();
            M.adjust_velocity();
            
            if(i==0){
                System.out.println("0---------------------------------------------------\n");
            /*    printxyz(P);
                printvelocity(P);
                printfitness(P);*/
                printParticles(M);
                System.out.println("----------------------------------------------------\n\n");
            }
            
            if(i==10){
                System.out.println("10---------------------------------------------------\n");
            /*    printxyz(P);
                printvelocity(P);
                printfitness(P);*/
                printParticles(M);
                System.out.println("----------------------------------------------------\n\n");
            }
            if(i==150){
                System.out.println("150---------------------------------------------------\n");
               /* printxyz(P);
                printvelocity(P);
                printfitness(P);*/
                printParticles(M);
                System.out.println("----------------------------------------------------\n\n");
            }
            if(i==500){
                System.out.println("500---------------------------------------------------\n");
               /* printxyz(P);
                printvelocity(P);
                printfitness(P);*/
                printParticles(M);
                System.out.println("----------------------------------------------------\n\n");
            }
            M.adjust_position();
        }
        
        printxyz(P);
        printvelocity(P);
        printfitness(P);
        
        System.out.println("Finished!");
        printParticles(M);
    }
    public static void printxyz(Particles P){
        double[][] coordinates = P.getxyz();
        for(int i=0; i<P.getnumParticles(); i++){
            for(int j=0; j<3; j++){
                System.out.println("coordinates["+i+"]["+j+"] = ]"+coordinates[i][j]);
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
    
    public static void printParticles(Methods M) {
        M.printParticles();
    }
    
    
}
