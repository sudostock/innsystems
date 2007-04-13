package backpropagation;

/*
 * Backpropagation.java
 *
 * Created on October 6, 2006, 2:11 PM
 *
 */


import java.util.Arrays;
import java.io.Serializable;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;


/**
 * This class is an implementation of a foward feed neural net with the backpropagation algorithem used to determine
 * the change in connection weights
 *
 *
 * @author Dr. Lilly, Alex Filby
 * @version 1.0.0 2/12/07
 * This is fully functional
 */
public class Backpropagation implements Serializable {
    private String name;
    
    private int numUnits;
    private int numWeights;
    private int dataIndex;
    private int teachIndex;
    private int tLayer[];
    private int indexTeach;
    private double error;
    private int epochs;
    private final double targetError = .01;
    
    private int mode;
    private double learnRate;
    private double momentum;
    private double tolerance;
    private double trainingNum;
    private double sumSE;
    private double aveSSE;
    
    private double weights1[][];
    private double weights2[][];
    private double weightsDelta1[];
    private double weightsDelta2[];
    private double tSetInputs[][];
    private double tSetOutputs[][];
    private double activations1[];
    private double activations2[];
    private double erSig[];
    
//*** added some variables
    private BufferedReader is2 = null;
    private String inString = null;
    private StringTokenizer token = null;
    private double rnd = 0.0;
    private int patterns;
    
    
    /** Creates a new instance of a Backpropagation neural net
     *
     *  @param name the name of the Network */
    public Backpropagation(String name) {
        this.name = name;
        
    }
    
    /** Sigmoid function, returns a number from 0 to 1
     *
     *  @param sum the sum of all the weights and activations for a particular neuron
     *
     *  @return a double of the output of the sigmoid function */
    private double sigmoid(double sum) {
        return ( 2.0 / (1 + Math.exp(-1.0 * sum)) - 1.0 );
    }
    
    /** Gets name of neural net
     *
     *  @returns String name of neural net */
    public String getName() {
        return name;
    }
    
    
    
    
    /** Resets all of the matrices in the net
     * Needs to be rewritten to randomly assign weights */
    private void resetNet() {
        tSetInputs = new double[patterns][tLayer[0]+1];
        tSetOutputs = new double[patterns][tLayer[2]];
//*** set dimensions for matrices
        weights1  = new double[tLayer[1]+1][tLayer[0]+1];
        weights2 = new double[tLayer[2]][tLayer[1]+1];
        weightsDelta1 = new double[tLayer[1]+1];
        weightsDelta2 = new double[tLayer[2]];
        activations1 = new double[tLayer[1]+1];
        activations2 = new double[tLayer[2]];
        
//*** Use the same set of random numbers each time --- the input file is rnd.out
        try {
            is2 = new BufferedReader(new InputStreamReader(new FileInputStream("rnd.out")));
        } catch(IOException e) {
            System.out.print("Error : " + e);
            System.exit(1);
        }
        
        //***      for(int i = 0; i < weights.length; i++) {
        for(int i = 0; i < tLayer[1]+1; i++)
            for(int j = 0; j < tLayer[0]+1; j++) {
            
            // read in the next line
            try {
                inString = is2.readLine();
            } catch(IOException e) {
                System.out.print("Error : " + e);
                System.exit(1);
            }
            // parse the next line
            token = new StringTokenizer(inString," \t\n\r");
            try {
                rnd = Double.parseDouble(token.nextToken());
//***            weights1[i] = ((Math.random() * .6) - .3);
                weights1[i][j] = 2.0 * rnd - 1.0;
            } catch(NumberFormatException e) {
                System.out.print("Error : " + e);
                System.exit(1);
            }
            }
        for(int i = 0; i < tLayer[2]; i++)
            for(int j = 0; j < tLayer[1]+1; j++) {
// read in the next line
            try {
                inString = is2.readLine();
            } catch(IOException e) {
                System.out.print("Error : " + e);
                System.exit(1);
            }
            // parse the next line
            token = new StringTokenizer(inString," \t\n\r");
            try {
                rnd = Double.parseDouble(token.nextToken());
//***            weights[i] = ((Math.random() * .6) - .3);
                weights2[i][j] = 2.0 * rnd - 1.0;
            } catch(NumberFormatException e) {
                System.out.print("Error : " + e);
                System.exit(1);
            }
            }
        
        
    }
    
    /** Resets NN wihtout inputing the set random numbers from a file. It just randomizes each
     *  weight for every connection */
    public void resetNet_new() {
        tSetInputs = new double[patterns][tLayer[0]+1];
        tSetOutputs = new double[patterns][tLayer[2]];
//*** set dimensions for matrices
        weights1  = new double[tLayer[1]+1][tLayer[0]+1];
        weights2 = new double[tLayer[2]][tLayer[1]+1];
        weightsDelta1 = new double[tLayer[1]+1];
        weightsDelta2 = new double[tLayer[2]];
        activations1 = new double[tLayer[1]+1];
        activations2 = new double[tLayer[2]];
        
        for(int i = 0; i < tLayer[1]+1; i++)
            for(int j = 0; j < tLayer[0]+1; j++) {
            weights1[i][j] = ((Math.random() * .6) - .3);
            }
        
        for(int i = 0; i < tLayer[2]; i++)
            for(int j = 0; j < tLayer[1]+1; j++) {
            weights2[i][j] = ((Math.random() * .6) - .3);
            }
        
        
    }
    
    /** Creates a Neural Net Object */
    public void createNetwork(int[] tLayer, double learnRate, double momentum, int patterns) {
        this.tLayer = tLayer;
        this.learnRate = learnRate;
        this.momentum = momentum;
        this.patterns = patterns;
        int tLayersize = tLayer.length;
        mode = 0;
        tolerance = .1;
        sumSE = 0;
        
        numWeights = 0;
        for(int i = 0; i < tLayersize -1; i++) {
            numWeights += tLayer[i] * tLayer[i+1];
        }
        
        numUnits = 0;
        for(int i = 0; i < tLayersize; i++) {
            numUnits += tLayer[i];
        }
        resetNet_new();
    }
    
    public void train(double[][] tSetInputs, double[][] tSetOutputs) {
        this.tSetInputs = tSetInputs;
        this.tSetOutputs = tSetOutputs;
        mode = 1;
        epochs = 0;
        
        
        
        
        do {
            epochs = epochs + 1;
            error = 0.0;
            for (int i = 0; i < patterns; i++) {
                for (int j = 0; j < tLayer[1]; j++) {
                    sumSE = 0;
                    for (int k = 0; k < tLayer[0]+1; k++) {
                        sumSE = sumSE + weights1[j][k]*tSetInputs[i][k];
                    }
                    activations1[j] = sigmoid(sumSE);
                }
                activations1[tLayer[1]] = 1.0;
                for (int j = 0; j < tLayer[2]; j++) {
                    sumSE = 0.0;
                    for (int k = 0; k < tLayer[1]+1; k++)
                        sumSE = sumSE + weights2[j][k]*activations1[k];
                    activations2[j] = sigmoid(sumSE);
                    error = error + Math.pow(activations2[j]-tSetOutputs[i][j],2.0)/2.0;
                }
                for (int j = 0; j < tLayer[2]; j++) {
                    weightsDelta2[j] = (tSetOutputs[i][j]-activations2[j])*(1.0-Math.pow(activations2[j],2.0))/2.0;
                }
                for (int j = 0; j < tLayer[1]+1; j++) {
                    sumSE = 0;
                    for (int k = 0; k < tLayer[2]; k++)
                        sumSE = sumSE + weightsDelta2[k] * weights2[k][j];
                    weightsDelta1[j] = (1.0 - Math.pow(activations1[j],2.0))*sumSE/2.0;
                }
                for (int j = 0; j < tLayer[2]; j++)
                    for (int k = 0; k < tLayer[1]+1; k++)
                        weights2[j][k] = weights2[j][k]+
                                learnRate*weightsDelta2[j]*activations1[k];
                for (int j = 0; j < tLayer[1]+1; j++)
                    for (int k = 0; k < tLayer[0]+1; k++)
                        weights1[j][k] = weights1[j][k]+
                                learnRate*weightsDelta1[j]*tSetInputs[i][k];
            }
           // System.out.println("error = "+ error+" epochs = "+epochs);
            if (epochs>400) break;
        }
        while (error > targetError);
    }
    
    public void getValue(double[] testvec) {
        double sumSE;
        
//*** Did not normalize data so no need to convert back
//***        data = normalizeData(data);
        System.out.println("Test");
        
        System.out.println("The test inputs are "+ (int)testvec[0]+ " and "+ (int)testvec[1]);
        
        for (int j = 0; j < tLayer[1]; j++) {
            sumSE = 0;
            for (int k = 0; k < tLayer[0]+1; k++)
                sumSE = sumSE + weights1[j][k]*testvec[k];
            activations1[j] = sigmoid(sumSE);
        }
        activations1[tLayer[1]] = 1.0;
        for (int j = 0; j < tLayer[2]; j++) {
            sumSE = 0;
            for (int k = 0; k < tLayer[1]+1; k++)
                sumSE = sumSE + weights2[j][k]*activations1[k];
            activations2[j] = sigmoid(sumSE);
        }
        
        for (int i = 0; i < tLayer[2]; i++) {
//*** take out reverse of normalization
//                 activations2[i] = (activations2[i]+1.0)/2.0;
//                 activations2[i] = activations2[i] * (largest - smallest) + smallest;
            System.out.print("        predicted = " + activations2[i] + " ");
        }
        System.out.println();
        
        
    }
    
    public int getEpochs() {
        return epochs;
    }
    
    public double getError() {
        return error;
    }
}
