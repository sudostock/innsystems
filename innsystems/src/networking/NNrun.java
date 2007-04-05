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
    private int generation;
    private int particle;
    private int inputL;
    private int hiddenL;
    private double learnRate;
    private double momentum = .2;
    private final int patterns = 10;
    private boolean stop;
    
    private int epochs;
    private double error;
    
    private Format dataP;
    
    
    /** Creates a new instance of NNrun */
    public NNrun(clientComm client, String filename) {
        this.client = client;
        dataP = new Format(filename, outputL);
        nn = new Backpropagation("Test");
        stop = false;
        Thread t = new Thread(this);
        t.start();
        
    }
    
    public void run() {
        
        while(!stop) {
            testData = client.getTestData();
            generation = (int) testData[0];
            particle = (int) testData[1];
            inputL = (int) testData[2];
            hiddenL = (int) testData[3];
            learnRate = testData[4];
            learnRate = (1/learnRate);
            
            int[] tLayer = new int[3];
            tLayer[0] = inputL;
            tLayer[1] = hiddenL;
            tLayer[2] = outputL;
            
            nn.createNetwork(tLayer, learnRate, momentum, patterns);
            dataP.createDataSet(inputL, patterns);
            nn.train(dataP.getInputs(), dataP.getOutputs());
            epochs = nn.getEpochs();
            error = nn.getError();
            
            System.out.println("Epochs :" + epochs + "Error" + error);
            double[] testResults = new double[4];
            testResults[0] = generation;
            testResults[1] = particle;
            testResults[2] = epochs;
            testResults[3] = error;
            
            client.addTestResults(testResults);
        }
    }
    
    public void stop() {
        stop = true;
    }
    
}
