/*
 * DataFile.java
 *
 * Created on April 3, 2007, 7:45 PM
 *
 */

package backpropagation;

import java.util.*;
import java.io.*;

/**
 *
 * @author Alexander.Filby
 */
public class DataFile {
    private static final String filename = "data.db";
    
    
    /** Creates a new instance of DataFile */
    public DataFile() {
    }
    
    public static void main(String[] args) {
        BufferedReader inFile = null;
        BufferedWriter outFile = null;
        try {
            inFile = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("outData.db")));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        String data = null;
        while(true) {
            try {
                
                data = inFile.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if( data == null ) break;
            data = data.substring(0, 5);
            try {
                outFile.write(data + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
        
        
        try {
            inFile.close();
            outFile.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
