/*
 * NNrun.java
 *
 * Created on March 18, 2007, 12:39 AM
 *
 */

package networking;

import backpropagation.*;

/**
 * This class is designed to wait for input from the conroller and then run the given NN test data and
 * store the results to be sent back to the controller
 *
 * @author Alex Filby
 */
public class NNrun implements Runnable {
    private clientComm client;
    private Backpropagation nn;
    private final int outputL = 6;
    private double[] testData;
    private int particle;
    private int inputL;
    private int hiddenL;
    private double learnRate;
    private double momentum = .2;
    private final int patterns = 4;
    private boolean stop;
    
    private int epochs;
    private double error;
    
    private Format dataP;
    
    
    /** Creates a new instance of NNrun */
    public NNrun(clientComm client, String filename) {
        this.client = client;
        dataP = new Format(filename);
        stop = false;
        Thread t = new Thread(this);
        t.start();
        
    }
    
    public void run() {
        
        while(!stop) {
            testData = client.getTestData();
            particle = (int) testData[0];
            inputL = (int) testData[1];
            hiddenL = (int) testData[2];
            learnRate = testData[3];
            
            int[] tLayer = new int[3];
            tLayer[0] = inputL;
            tLayer[1] = hiddenL;
            tLayer[2] = outputL;
            
            nn.createNetwork(tLayer, learnRate, momentum, patterns);
            dataP.createDataSet(inputL);
            nn.train(dataP.getInputs(), dataP.getOutputs());
            epochs = nn.getEpochs();
            error = nn.getError();
            
            double[] testResults = new double[3];
            testResults[0] = particle;
            testResults[1] = epochs;
            testResults[2] = error;
            
            client.addTestResults(testResults);
        }
    }
    
    public void stop() {
        stop = true;
    }
    
}
