/*
 * clientTest.java
 *
 * Created on February 28, 2007, 8:37 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package networking;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author Alex Filbius
 */
public class clientTest {
    
    /** Creates a new instance of clientTest */
    public clientTest() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException {
        clientComm test = new clientComm();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
}
