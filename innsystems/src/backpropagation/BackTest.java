/*
 * BackTest.java
 *
 * Created on April 4, 2007, 8:34 AM
 */

package backpropagation;

/**
 *
 * @author Alexander.Filby
 */
public class BackTest {
    
    public BackTest() {
    }
    
    public static void main(String[] args) {
        
        Format data = new Format("AppleTestData.db", 6);
        Backpropagation backprop = new Backpropagation("TEST");
        
        int tLayer[] = new int[3];
        
        tLayer[0] = 7;
        tLayer[1] = 600;
        tLayer[2] = 6;
        
        data.createDataSet(7, 10);
        
        backprop.createNetwork(tLayer, .9, .1, 10);
        backprop.train(data.getInputs(), data.getOutputs());
    }
    
}
