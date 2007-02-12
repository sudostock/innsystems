package backpropagation;

import java.io.*; // import the Java input/output classes
import java.util.*; // import the Java utilities classes


/**
 * This class is an implementation of a foward feed neural net with the backpropagation algorithem used to determine
 * the change in connection weights
 *
 *
 * @author Dr. Lilly
 * @version 1.0.0 2/12/07
 * This is fully functional method to test the backpropagation class using the xor function
 */


public class XOR
{
    public static void main(String[] args)
      {
  BufferedReader is1 = null; // information input data stream         
  BufferedReader is2 = null; // random number input data stream         
  String inString = null; // The input string for a line of data
  StringTokenizer token = null; // for reading in a line of text
  double rnd = 0.0;
  
  final int max_vectors       = 100; //   {max # training vectors in set}
  final int max_vec_len       = 25;  //   {max length of training or test vectors}
  final int max_neurons       = 25;  //   {max # classifying neurons in simulation}

      double []   testvec     = new double[max_vec_len];
      double [][] outputvecs  = new double[max_vectors][max_vec_len];
      double [][]  inputvecs  = new double[max_vectors][max_vec_len];
      double [][] weightvecs  = new double[max_vec_len][max_vec_len];
      double [][] weight2vecs = new double[max_vec_len][max_vec_len];

      int limit_of_inputs = 0; // {limit on # of input vectors}

      int numinvecs = 0; //    {# vectors in training set}
      int leninvecs = 0; //    {length of training vectors}
      int numclasses = 0; //   {number of classes to train (numoutvecs)}
      int iter = 0; //         {step into training - per invec or cycle}

      double average_result = 0.0; //  {average of the result(s)}
      double prior_to_actual = 0.0; // {target prediction value}
      double actual = 0.0; //    {target prediction value}
      double smallest = 0.0; //  {smallest number read}
      double largest = 0.0; //   {largest number read}
      
      double my_rnd = 0.0; //    {for storing random numbers}
      double alpha = 0.0; //     {learning constant for various techniques}
      double error = 0.0; //     {curr iteration error for various techs}
      double term_error = 0.0; // : real;      {termination error or test val for term}
      boolean finished = false; // {end of an operation, usually for training}
      boolean abort = false; //    {abort current operation}
      boolean quittrain = false; //{force end of training but allow testing}
      boolean funckey = false; //   : boolean;   {last key scanned in a fn key?}
      char technique = ' '; //    {user choice for learing technique}
      char inputkey  = ' '; //     : char;      {last key scanned from keyboard}


      int i = 0;
      int j = 0;
 
for (i = 0; i < max_vectors; i++) 
      for (j = 0; j < max_vec_len; j++)
         {
         inputvecs[i][j] = 0;
         outputvecs[i][j] = 0;
         }



  try {
      is1 = new BufferedReader (new InputStreamReader(new FileInputStream("xor.txt")));
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    // read in the first line
    try {
      inString = is1.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    // parse the first line 
    token = new StringTokenizer(inString," \t\n\r");
    try {
      limit_of_inputs = Integer.parseInt(token.nextToken());
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }


  while(numinvecs < limit_of_inputs) {
try {
      inString = is1.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    // parse the next line 
    token = new StringTokenizer(inString," \t\n\r");
    i = 0;
    while ( token.hasMoreTokens()) {
    try {
      inputvecs[numinvecs][i] = Double.parseDouble(token.nextToken());
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    i++;
    }

   leninvecs = i;

try {
      inString = is1.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    // parse the next line 
    token = new StringTokenizer(inString," \t\n\r");
    i = 0;
    while ( token.hasMoreTokens()) {
    try {
      outputvecs[numinvecs][i] = Double.parseDouble(token.nextToken());
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    i++;
    }
    numclasses = i;

numinvecs++; 
}

System.out.println("Number of input and output vectors  = "+ numinvecs);
System.out.println("Length of input vectors  = "+ leninvecs);
System.out.println("Length of ouput vectors = "+ numclasses);

    i = 0;
    try {
      inString = is1.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    // parse the next line 
    token = new StringTokenizer(inString," \t\n\r");
    while ( token.hasMoreTokens()) {
    try {
      testvec[i] = Double.parseDouble(token.nextToken());
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    i++;
    }

    prior_to_actual = testvec[leninvecs-1];

    try {
      inString = is1.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    // parse the next line 
    token = new StringTokenizer(inString," \t\n\r");
    try {
      actual = Double.parseDouble(token.nextToken());
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }

    try {
      inString = is1.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    // parse the next line 
    token = new StringTokenizer(inString," \t\n\r");
    try {
      smallest = Double.parseDouble(token.nextToken());
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }

    try {
      inString = is1.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    // parse the next line 
    token = new StringTokenizer(inString," \t\n\r");
    try {
      largest = Double.parseDouble(token.nextToken());
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }


// System.out.println("prior_to_actual = "+ prior_to_actual);
// System.out.println("         actual = "+ actual);
// System.out.println("       smallest = "+ smallest);
// System.out.println("        largest = "+ largest);

System.out.println();

// close the input data file
    try {
      is1.close();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }	    

//*** Normalization
//      for (i = 0; i < leninvecs; i++) { 
//            testvec[i] = (testvec[i]-smallest)/(largest-smallest);
//      }
//      for (i = 0; i < numinvecs; i++) 
//          for (j = 0; j < leninvecs; j++)
//            {
//            inputvecs[i][j] = (inputvecs[i][j]-smallest)/(largest-smallest);
//            }
//      for (i = 0; i < numinvecs; i++)
//          for (j = 0; j < numclasses; j++)
//            {
//             outputvecs[i][j] = (2*((outputvecs[i][j]-smallest)/(largest-smallest)))-1;
//            }

// do back propagation


    double sum = 0.0;

    double []   o1          = new double[max_vec_len];
    double []   o2          = new double[max_vec_len];
    double []   delta_j     = new double[max_vec_len];
    double []   delta_k     = new double[max_vec_len];

    int k = 0;
    int numhiddennodes = 0;

  try {
      is2 = new BufferedReader (new InputStreamReader(new FileInputStream("rnd.out")));
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }


      numhiddennodes = 12;
      alpha = .7;
      term_error = .01;
      iter = 0;

      for (i = 0; i < numinvecs; i++) 
         inputvecs[i][leninvecs] = 1.0;

//	System.out.println("numinvecs = " + numinvecs + " numhiddennodes = " + numhiddennodes);

      for (i = 0; i < numhiddennodes + 1; i++)
         for (j = 0; j < leninvecs + 1; j++) {
    // read in the next line
    try {
      inString = is2.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    // parse the next line 
    token = new StringTokenizer(inString," \t\n\r");
    try {
      rnd = Double.parseDouble(token.nextToken());
      weightvecs[i][j] = 2.0 * rnd - 1.0;
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
}
    for (i = 0; i < numclasses; i++)
         for (j = 0; j < numhiddennodes + 1; j++) {
  
  // read in the next line
    try {
      inString = is2.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    // parse the next line 
    token = new StringTokenizer(inString," \t\n\r");
    try {
      rnd = Double.parseDouble(token.nextToken());
      weight2vecs[i][j] = 2.0 * rnd - 1.0;
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    }
    // close the random number input data file
    try {
      is2.close();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }


do {
         iter = iter + 1;
         error = 0.0;
         for (i = 0; i < numinvecs; i++)
            {
            for (j = 0; j < numhiddennodes; j++)
               {
               sum = 0;
               for (k = 0; k < leninvecs+1; k++) {
                  sum = sum + weightvecs[j][k]*inputvecs[i][k];
                  }
               o1[j] = fnet(sum);
               }
            o1[numhiddennodes] = 1.0;
            for (j = 0; j < numclasses; j++)
               {
               sum = 0.0;
               for (k = 0; k < numhiddennodes+1; k++)
                  sum = sum + weight2vecs[j][k]*o1[k];
               o2[j] = fnet(sum);
               error = error + Math.pow(o2[j]-outputvecs[i][j],2.0)/2.0;
               }
            for (j = 0; j < numclasses; j++) {
               delta_k[j] = (outputvecs[i][j]-o2[j])*(1.0-Math.pow(o2[j],2.0))/2.0;
               }
            for (j = 0; j < numhiddennodes+1; j++)
               {
               sum = 0;
               for (k = 0; k < numclasses; k++)
                  sum = sum + delta_k[k] * weight2vecs[k][j];
               delta_j[j] = (1.0 - Math.pow(o1[j],2.0))*sum/2.0;
               }
            for (j = 0; j < numclasses; j++)
               for (k = 0; k < numhiddennodes+1; k++)
                  weight2vecs[j][k] = weight2vecs[j][k]+
                                           alpha*delta_k[j]*o1[k];
            for (j = 0; j < numhiddennodes+1; j++)
               for (k = 0; k < leninvecs+1; k++)
                  weightvecs[j][k] = weightvecs[j][k]+
                                         alpha*delta_j[j]*inputvecs[i][k];
            }
 System.out.println("error = "+ error+" epochs = "+iter);
 if(iter > 400) break;
}
while (error > term_error);

// finished with training
// time now to try prediction

            testvec[leninvecs] = 1.0;

            System.out.println("The test inputs are "+ (int)testvec[0]+ " and "+ (int)testvec[1]);

            for (j = 0; j < numhiddennodes; j++)
               {
               sum = 0;
               for (k = 0; k < leninvecs+1; k++)
                  sum = sum + weightvecs[j][k]*testvec[k];
               o1[j] = fnet(sum);
               }
            o1[numhiddennodes] = 1.0;
            for (j = 0; j < numclasses; j++)
               {
               sum = 0;
               for (k = 0; k < numhiddennodes+1; k++)
                  sum = sum + weight2vecs[j][k]*o1[k];
               o2[j] = fnet(sum);
               }

            average_result = 0;


//            System.out.println("prior_to_actual = " + prior_to_actual);
//            System.out.println("         actual = " + actual);
            System.out.print  ("      predicted = ");
            for (i = 0; i < numclasses; i++)
               {
// did not normalize so no need to reverse normalizations  
//                 o2[i] = (o2[i]+1.0)/2.0;
//                 o2[i] = o2[i] * (largest - smallest) + smallest;
                 System.out.print (o2[i] + " ");
                 average_result = average_result + o2[i];
               }
               System.out.println(" ");
//               System.out.println(" average result = " + (average_result/numclasses));

            testvec[0] = 0.0;

            System.out.println("The test inputs are "+ (int)testvec[0]+ " and "+ (int)testvec[1]);

            for (j = 0; j < numhiddennodes; j++)
               {
               sum = 0;
               for (k = 0; k < leninvecs+1; k++)
                  sum = sum + weightvecs[j][k]*testvec[k];
               o1[j] = fnet(sum);
               }
            o1[numhiddennodes] = 1.0;
            for (j = 0; j < numclasses; j++)
               {
               sum = 0;
               for (k = 0; k < numhiddennodes+1; k++)
                  sum = sum + weight2vecs[j][k]*o1[k];
               o2[j] = fnet(sum);
               }

            average_result = 0;


//            System.out.println("prior_to_actual = " + prior_to_actual);
//            System.out.println("         actual = " + actual);
            System.out.print  ("      predicted = ");
            for (i = 0; i < numclasses; i++)
               {
// did not normalize so no need to reverse normalizations  
//                 o2[i] = (o2[i]+1.0)/2.0;
//                 o2[i] = o2[i] * (largest - smallest) + smallest;
                 System.out.print (o2[i] + " ");
                 average_result = average_result + o2[i];
               }
               System.out.println(" ");
//               System.out.println(" average result = " + (average_result/numclasses));

	}

public static double fnet(double net) // {cts, bipolar activation fn}
{
   return (2.0/(1.0 + Math.exp(-net)) - 1.0);
}

}
