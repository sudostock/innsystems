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
        netHost host = new netHost();
        for(int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        
        Thread.sleep(5000);
        System.out.println("about to exit");
        host.stop_listenBC();
        System.out.println("wtf");
    }
    
}
