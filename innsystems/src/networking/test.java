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
        InetAddress net = InetAddress.getLocalHost();
        System.out.println(net);
        System.out.println(net.getAddress());
        System.out.println(net.toString());
        byte[] test = new byte[net.getAddress().length];
        test = net.getAddress();
        System.out.println(test.toString());
        
        InetAddress net2 = InetAddress.getByAddress(test);
        System.out.println(net2);
        System.out.println(net2.getAddress());
        
        
    }
    
}
