package pso;


public class NewClass {
    public static void main(String[] args){
    Particles p = new Particles(1, 3);
    double[] vel = {.12, .23, .32};
    double[] n = {.9,.9,.9};
    
    p.addPosition(0, vel);
    double[] get = p.getPosition(0);
    System.out.println(get[0] + " " + get[1] + " " + get[2]);
    p.debug();

    }
}
