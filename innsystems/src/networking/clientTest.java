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
 * @author Alex
 */
public class clientTest {
    
    /** Creates a new instance of clientTest */
    public clientTest() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException {
        byte[] buff;
        buff = "HELLO".getBytes();
        int length = buff.length;
        InetAddress server = InetAddress.getByName("228.5.7.7");
        DatagramPacket packet = new DatagramPacket(buff, length, server, 7776);
        DatagramSocket sock;
        clientComm commie = new clientComm();
        try {
            sock = new DatagramSocket();
            sock.send(packet);
            sock.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
}
