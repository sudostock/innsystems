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
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        byte[] server = new byte[5];
        // hostBC bc = new hostBC(server);
        hostBC bc = new hostBC(server);
        System.out.println("Hello");
        for(int i = 0; i < 10; i++) {
            System.out.println("Hello" +i);
            Thread.sleep(1000);
        }
        
        bc.stop_Server();
        System.out.println("stopping");
        Thread.sleep(10000);
    }
    
}
