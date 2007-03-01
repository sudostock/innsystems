/*
 * clientComm.java
 *
 * Created on February 28, 2007, 9:42 PM
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
public class clientComm implements Runnable {
    
    /** Creates a new instance of clientComm */
    public clientComm() {
        Thread t = new Thread(this);
        t.start();
    }
    
    public void run() {
        DatagramSocket sock = null;
        try {
            sock = new DatagramSocket(7777);
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        byte[] buff = new byte[256];
        int length = buff.length;
        DatagramPacket pack = new DatagramPacket(buff, length);
        
        while(true)
            try{
                sock.setSoTimeout(5);
                try {
                    sock.receive(pack);
                    System.out.println(pack.getAddress());
                    break;
                } catch (SocketTimeoutException ex) {
                    
                }}catch (IOException e) {
                    
                }
        
        
    }
    
    
    
}
