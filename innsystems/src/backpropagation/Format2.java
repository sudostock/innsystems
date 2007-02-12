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
 * This is a fully functional class that creates the appropriate test class for the backpropagation network
 */


public class Format2
{
    public static void main(String[] args)
      {
   double d = 0.0;
   int debug = 0;
   int i = 0;
   int j = 0;
   int pointer = 0;
   int counter = 0;
   int skips = 0;
   int beginning_skips = 0;
   int middle_skips = 0;
   int input_length = 0;
   int output_length = 0;
   int number_of_input_vectors = 0;
   double smallest = 0.0, largest = 0.0;
   PrintStream tSetInputs = null;
   PrintStream tSetOutputs = null;
   PrintStream BackPropagationTest = null;
   BufferedReader iFile = null;
   BufferedReader Read_tSetInputs = null;
   BufferedReader Read_tSetOutputs = null;
   String inString = null; // The input string for a line of data
   StringTokenizer token = null; // for reading in a line of text
   double learnRate = .7;
   double momentum = .2;
   double tolerance = .01;
   int patterns = 0;
   int number_of_hidden_layer_nodes = 12;



  try {
      iFile = new BufferedReader(new InputStreamReader(new FileInputStream("t.txt")));
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }

    try {
        BackPropagationTest = new PrintStream(new FileOutputStream("BackPropagationTest.java"));
      }
      catch(IOException ex) {
        System.out.print("Error : " + ex);
        System.exit(1);
      }

   
    try {
        tSetInputs = new PrintStream(new FileOutputStream("tSetInputs.txt"));
      }
      catch(IOException ex) {
        System.out.print("Error : " + ex);
        System.exit(1);
      }

    try {
        tSetOutputs = new PrintStream(new FileOutputStream("tSetOutputs.txt"));
      }
      catch(IOException ex) {
        System.out.print("Error : " + ex);
        System.exit(1);
      }

   System.out.println("Enter beginning_skips middle_skips ... ");
   System.out.println("      input_length output_length number_of_input_vectors");

   beginning_skips = Keyboard.readInt();
   middle_skips = Keyboard.readInt();
   input_length = Keyboard.readInt();
   output_length = Keyboard.readInt(); 
   number_of_input_vectors = Keyboard.readInt();

   int total_length = input_length + output_length;
   int reset = total_length;
   double [] total = new double[total_length];

   patterns = number_of_input_vectors;

   number_of_input_vectors++;

   while(skips<beginning_skips) {

try {
      inString = iFile.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
         skips++;
   }
   skips = 0;

try {
      inString = iFile.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    if(inString == null) System.exit(1);
    if(inString.length() == 0) System.exit(1);
    token = new StringTokenizer(inString," \t\n\r");
    if (!token.hasMoreTokens()) System.exit(1);
    try {
      d = Double.parseDouble(token.nextToken());
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
   
     total[pointer] = d;
     smallest = d;
     largest = d;
     pointer++;
   

   while(skips<middle_skips) {
try {
      inString = iFile.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
         skips++;
   }
   skips = 0;




   while(true) {
try {
      inString = iFile.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    if(inString == null) System.exit(1);
    if(inString.length() == 0) System.exit(1);
    token = new StringTokenizer(inString," \t\n\r");
    if (!token.hasMoreTokens()) System.exit(1);
    try {
      d = Double.parseDouble(token.nextToken());
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
     if(d<smallest) smallest = d;
     if(d>largest) largest = d;
     total[pointer] = d;
     pointer++;
   while(skips<middle_skips) {
try {
      inString = iFile.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
         skips++;
   }
   skips = 0;
     if(pointer==total_length) break;
   }

   pointer = 0;
   tSetInputs.print(total[pointer]);
   counter = 1;
   while (counter < input_length) {
     pointer++;
     pointer %= reset;
     tSetInputs.print(" "+total[pointer]);
     counter++;
   }

   tSetInputs.println("");

     pointer++;
     pointer %= reset;

   tSetOutputs.print(total[pointer]);

   counter = 1;
   while (counter < output_length) {
     pointer++;
     pointer %= reset;
     tSetOutputs.print(" "+total[pointer]);
     counter++;
   }

   tSetOutputs.println("");


   while(true) {

try {
      inString = iFile.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    if(inString == null) System.exit(1);
    if(inString.length() == 0) System.exit(1);
    token = new StringTokenizer(inString," \t\n\r");
    if (!token.hasMoreTokens()) System.exit(1);
    try {
      d = Double.parseDouble(token.nextToken());
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }

     if(d<smallest) smallest = d;
     if(d>largest) largest = d;
     pointer++;
     pointer %= reset;
     total[pointer] = d;
     pointer++;
     pointer %= reset;
     tSetInputs.print(total[pointer]);

   while(skips<middle_skips) {
try {
      inString = iFile.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
         skips++;
   }
   skips = 0;

   counter = 1;
   while (counter < input_length) {
     pointer++;
     pointer %= reset;
     tSetInputs.print(" "+total[pointer]);
     counter++;
   }

   tSetInputs.println("");

     pointer++;
     pointer %= reset;

   tSetOutputs.print(total[pointer]);

   counter = 1;
   while (counter < output_length) {
     pointer++;
     pointer %= reset;
     tSetOutputs.print(" "+total[pointer]);
     counter++;
   }
   tSetOutputs.println("");
   if (debug > (number_of_input_vectors - 3)) break;
   debug++;
   }

// close the files

try {
      iFile.close();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }	

tSetInputs.close();
tSetOutputs.close();


try {
      Read_tSetInputs = new BufferedReader(new InputStreamReader(new FileInputStream("tSetInputs.txt")));
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }

try {
      Read_tSetOutputs = new BufferedReader(new InputStreamReader(new FileInputStream("tSetOutputs.txt")));
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }

    try {
        BackPropagationTest = new PrintStream(new FileOutputStream("BackPropagationTest.java"));
      }
      catch(IOException ex) {
        System.out.print("Error : " + ex);
        System.exit(1);
      }

BackPropagationTest.println("public class BackpropagationTest {");
BackPropagationTest.println("    ");
BackPropagationTest.println("    public BackpropagationTest() {");
BackPropagationTest.println("    }");
BackPropagationTest.println("    ");
BackPropagationTest.println("    public static void main(String[] args) {");
BackPropagationTest.println("\n        Backpropagation net = new Backpropagation(\"Test net\");");
BackPropagationTest.println("        ");
BackPropagationTest.println("        double smallest = "+smallest+"; // the smallest stock price");
BackPropagationTest.println("        double largest = "+largest+";   // the largest stock price");
BackPropagationTest.print  ("\n        int temp[] = {"+input_length+", ");
BackPropagationTest.println(number_of_hidden_layer_nodes+", "+output_length+" };");
BackPropagationTest.println("");
BackPropagationTest.print  ("        net.createNetwork(temp, ");
BackPropagationTest.print  (learnRate+", "+momentum+", "+patterns+", "+tolerance+" );");
BackPropagationTest.println("        ");
BackPropagationTest.println("\n        double tSetInputs[][] = { ");

for (i = 0; i < patterns; i++) {
  BackPropagationTest.print("{");
  try {
      inString = Read_tSetInputs.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    if(inString == null) System.exit(1);
    if(inString.length() == 0) System.exit(1);
    token = new StringTokenizer(inString," \t\n\r");
    while (token.hasMoreTokens()) {
    try {
      d = Double.parseDouble(token.nextToken());
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
// Normalize the data to the range -1 to 1
  d = (2*((d-smallest)/(largest-smallest)))-1;
  BackPropagationTest.print(d+",");
  }
  if (i < patterns-1)
	BackPropagationTest.println("1}, // added a 1 for bias node");
  else {
	BackPropagationTest.println("1} // added a 1 for bias node");
      BackPropagationTest.println("                                };");
  }
}  

BackPropagationTest.println("\n        double tSetOutputs[][] = { ");

for (i = 0; i < patterns; i++) {
  BackPropagationTest.print("{");
  try {
      inString = Read_tSetOutputs.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    if(inString == null) System.exit(1);
    if(inString.length() == 0) System.exit(1);
    token = new StringTokenizer(inString," \t\n\r");
    while (token.hasMoreTokens()) {
    try {
      d = Double.parseDouble(token.nextToken());
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
// Normalize the data to the range -1 to 1
  d = (2*((d-smallest)/(largest-smallest)))-1;
  BackPropagationTest.print(d+",");
  }
  if (i < patterns-1)
	BackPropagationTest.println("1}, // added a 1 for bias node");
  else {
	BackPropagationTest.println("1} // added a 1 for bias node");
      BackPropagationTest.println("                                };");
  }
}  


BackPropagationTest.println("");
BackPropagationTest.println("");
BackPropagationTest.println("        net.train(tSetInputs, tSetOutputs);");
BackPropagationTest.println("        ");
BackPropagationTest.print("        double testData[] = ");

BackPropagationTest.print("{");
  try {
      inString = Read_tSetInputs.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    if(inString == null) System.exit(1);
    if(inString.length() == 0) System.exit(1);
    token = new StringTokenizer(inString," \t\n\r");
    while (token.hasMoreTokens()) {
    try {
      d = Double.parseDouble(token.nextToken());
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
// Normalize the data to the range -1 to 1
  d = (2*((d-smallest)/(largest-smallest)))-1;
  BackPropagationTest.print(d+",");
  }

	BackPropagationTest.println("1}; // added a 1 for bias node");


BackPropagationTest.print("\n        double actual[] = ");

BackPropagationTest.print("{");
  try {
      inString = Read_tSetOutputs.readLine();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
    if(inString == null) System.exit(1);
    if(inString.length() == 0) System.exit(1);
    token = new StringTokenizer(inString," \t\n\r");
    while (token.hasMoreTokens()) {
    try {
      d = Double.parseDouble(token.nextToken());
    }
    catch(NumberFormatException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }
  if(token.hasMoreTokens())
    BackPropagationTest.print(d+",");
  else
    BackPropagationTest.print(d);
  }
	BackPropagationTest.println("};");




BackPropagationTest.println("");
BackPropagationTest.println("        net.getValue(testData,actual,smallest,largest);");
BackPropagationTest.println("                ");
BackPropagationTest.println("    }");
BackPropagationTest.println("    ");
BackPropagationTest.println("}");


try {
      Read_tSetInputs.close();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }	
try {
      Read_tSetOutputs.close();
    }
    catch(IOException e) {
      System.out.print("Error : " + e);
      System.exit(1);
    }	

  BackPropagationTest.close();

  }
} 
