/*
 * clientComm.java
 *
 * Created on February 28, 2007, 10:53 PM
 *
 *
 */

package networking;

import java.io.*;
import java.net.*;

/**
 * 
 *
 * @author Alex Filby
 */
public class clientComm {
    private CDQueue testData;
    private CDQueue testResults;
    private final int defaultSize = 10;
    
    
    public clientComm() {
        testData = new CDQueue(defaultSize);
        testResults = new CDQueue(defaultSize);
                
    }
    
    
    public void addTestData(double[] testD) {
        testData.put(testD);
    }
    
    public double[] getTestData() {
        return (double[]) testData.take();
    }
    
    public void addTestResults(double[] testR) {
        testResults.put(testR);
    }
    
    public double[] getTestResults() {
        return (double[]) testResults.take();
    }
    
    
    
    
}
