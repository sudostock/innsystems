/*
 * test.java
 *
 * Created on February 24, 2007, 3:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package networking;

import java.net.*;


/**
 *
 * @author alex
 */
public class test {
    
    /** Creates a new instance of test */
    public test() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException {
        byte[] serverIP = new byte[4];
        hostBC bc = new hostBC(serverIP);
        System.out.println("Started thread");
        bc.stop();
        System.out.println("stopped the fucker");
        
        
    }
    
}
