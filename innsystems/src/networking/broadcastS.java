/*
 * broadcastS.java
 *
 * Created on February 28, 2007, 7:58 PM
 *
 *
 */

package networking;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Alex Filby
 */
public class broadcastS implements Runnable {
    private InetAddress serverIp;
    private MulticastSocket sock;
    private final int listenPort = 7776;
    private boolean stop;
    private InetAddress  group;
    private final String shake = "HELLO";
    private PipedOutputStream pout;
    
    public broadcastS() {
        DataOutputStream dout = new DataOutputStream(pout);
        try {
            serverIp = InetAddress.getLocalHost();
            group  = InetAddress.getByName("228.5.7.7");
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        
        Thread t = new Thread(this);
        t.start();
        
    }
    
    public void run() {
        stop = false;
        try {
            
            sock = new MulticastSocket(listenPort);
            sock.joinGroup(group);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            
            sock.setSoTimeout(5);
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        while(!stop) {
            byte buff[] = new byte[256];
            int length = buff.length;
            DatagramPacket packet = new DatagramPacket(buff, length);
            try{
                try {
                    sock.receive(packet);
                    System.out.println("WTF");
                    msg(packet);
                    
                } catch (SocketTimeoutException ex) {
                   
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        try {
            sock.leaveGroup(group);
            sock.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    private void msg(DatagramPacket packet) {
        System.out.println("Down here");
        byte buf[] = new byte[packet.getLength()];
        buf = packet.getData();
        System.out.println(buf.toString());
        System.out.println(packet.getAddress());
        if(buf.toString().equalsIgnoreCase(shake)) {
            System.out.println("Got the fucking packet!");
        } else
            return;
    }
    
}
