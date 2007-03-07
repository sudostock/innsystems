/*
 * controllertest.java
 *
 * Created on March 6, 2007, 7:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package networking;

import java.io.*;
import java.net.*;


/**
 *
 * @author Alexander.Filby
 */
public class controllertest {
    
    /** Creates a new instance of controllertest */
    public controllertest() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        netController net = new netController(1);
        // broadcastS broad = new broadcastS(net);
        DatagramPacket packet = null;
        DatagramSocket sockOut = null;
        try {
            sockOut = new DatagramSocket();
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        InetAddress client = null;
        try {
            client = InetAddress.getByName("10.10.31.14");
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        byte[] buff = "random".getBytes();
        int length = buff.length;
        packet = new DatagramPacket(buff, length, client, 7777);
        System.out.println(client);
        try{
            for(int i = 0; i < 4; i++) {
                sockOut.send(packet);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            sockOut.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        try {
            Thread.sleep(15000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        net.addQClient(client);
        double temp[][] = new double[1][3];
        temp[0][0] = 5;
        temp[0][1] = 5;
        temp[0][2] = .7;
        
        net.storeTestData(temp);
        System.out.println("HELLO");
        //  broad.stop();
        RecieveComm r = new RecieveComm(net);
        System.out.println("YO");
        SendData s = new SendData(net);
        System.out.println("Ummm");
    }
    
}
