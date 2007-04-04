/*
 * FormatTest.java
 *
 * Created on April 4, 2007, 8:15 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package backpropagation;

/**
 *
 * @author Alexander.Filby
 */
public class FormatTest {
    
    /** Creates a new instance of FormatTest */
    public FormatTest() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Format data = new Format("tdata.db", 6);
        
        data.createDataSet(3, 2);
        data.showInputs();
        double[][] input = data.getInputs();
        
        data.createDataSet(3, 2);
        System.out.println("\n\n");
        data.showInputs();
        
        data.createDataSet(3, 3);
        System.out.println("\n");
        data.showInputs();
        
        data.createDataSet(6, 10);
        System.out.println("\n");
        data.showInputs();
        
        data.createDataSet(29, 10);
        System.out.println("\n");
        data.showInputs();
        
    }
}
