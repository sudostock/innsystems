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
 *
 *
 */
public class broadcastS implements Runnable {
    private InetAddress serverIp;
    private MulticastSocket sock;
    private final int listenPort = 7776;
    private boolean stop;
    private InetAddress  group;
    private final String shake = "HELLO";
    private netController net;
    
    public broadcastS(netController net) {
        this.net = net;
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
        net.addQClient(packet.getAddress());
        returnIP(packet.getAddress());
        if(new String(buf).equalsIgnoreCase(shake)) {
            System.out.println("Got the fucking packet!");
            
            returnIP(packet.getAddress());
        } else
            return;
    }
    
    private void returnIP(InetAddress client) {
        DatagramSocket sockOut = null;
        try {
            sockOut = new DatagramSocket();
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        
        byte[] buff = "random".getBytes();
        int length = buff.length;
        DatagramPacket packet = new DatagramPacket(buff, length, client, 7777);
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
        
    }
    
    public void stop() {
        System.out.println("STOPPED!");
        stop = true;
    }
    
}
