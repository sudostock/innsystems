/*
 * Format.java
 *
 * Created on March 18, 2007, 1:30 AM
 *
 */

package backpropagation;

import java.util.*;
import java.io.*;


/**
 * This class is a rewrite of the Format2 class written by Dr Lilly. It is modified to work with the program
 * it will now be an object instead of a main class and it will no longer output a file, instead it will
 * have methods to allow retrieval of those "formatted" and normilized data. Also this new class will have
 * the ability to store the input data in an array, so that when the number of input nodes changes the program
 * will quickly be able to create a new dataset.
 *
 * Theoretically this class will have to be rewritten for each and every type of data one would like to run
 * this allows the Neural Net to be more generalized and let the user decide how they want to normilize their
 * data.
 *
 * This class needs to be looked at for error checking, this is an important class with intensive read from files
 * and manipulation of data.
 *
 * @author Alex Filby
 * @version 0.0.1 3/18/07
 */
public class Format {
    private String filename;
    private double max;
    private double min;
    private double[] dataSet;
    private int inputNodes;
    private final int outputNodes;
    private int patterns;
    private double[][] t_inputs;
    private double[][] t_outputs;
    
    /** Creates a new instance of Format */
    public Format(String filename, int outputNodes) {
        this.filename = filename;
        this.outputNodes = outputNodes;
        initialize();
    }
    
    /* Needs to be rewritten to make it more error resistant and utlizing the Scanner class, need to know format of
     * input data first, unless it can read in a line and determine what kind of data it is */
    private void initialize(){
        BufferedReader inFile = null;
        ArrayList <Double> data = new ArrayList();
        try {
            inFile = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        
        String d = null;
        double da = 0;
        try {
            d = inFile.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        da = Double.parseDouble(d);
        data.add(da);
        max = da;
        min = da;
        
        while(true) {
            try {
                d = inFile.readLine();
                if(d == null) break;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            da = Double.parseDouble(d);
            data.add(da);
            
            if(da > max)
                max = da;
            if(da < min)
                min = da;
            
        }
        
        // Converts array list to an array
        dataSet = new double[data.size()];
        int lengthData = data.size();
         for(int i = 0; i < lengthData; i++) {
            dataSet[i] = data.get(i);
         }
        data = null;
        
        /* Normalizing data */
        int length = dataSet.length;
        for(int i = 0; i < length; i++) {
            dataSet[i] = ((2*((dataSet[i]-min)/(max-min)))-1);
        }
        
        
    }
    
    public void createDataSet(int inputNodes, int patterns) {
        if(this.inputNodes == inputNodes && this.patterns == patterns)
            return;
        this.inputNodes = inputNodes;
        this.patterns = patterns;
        if(this.inputNodes == inputNodes) return;
        
        t_inputs = new double[patterns][inputNodes +1]; // + 1 for bias nerode
        t_outputs = new double[patterns][outputNodes];
        
        int skips = ((5*60)/inputNodes);
        int outSkips = 50;
        int pointerI = 0;
        int pointer = 0;
        
        for(int i = 0; i < patterns; i++) {
            for(int j = 0; j < inputNodes; j++) {
                t_inputs[i][j] = dataSet[pointer];
                pointer+= skips;
            }
            t_inputs[i][inputNodes -1] = 1;
            
            for(int j = 0; j < outputNodes; j++) {
                t_outputs[i][j] = dataSet[pointer];
                pointer+= outSkips;
            }
            pointerI += skips;
            pointer = pointerI;
        }
    }
    
    public double[][] getInputs() {
        return t_inputs;
    }
    
    public double[][] getOutputs() {
        return t_outputs;
    }
    
}
