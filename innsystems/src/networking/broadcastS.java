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
    private ServerSocket sock;
    private final int listenPort = 7776;
    private boolean stop;
    private InetAddress  group;
    private final String shake = "HELLO";
    private netController net;
    
    public broadcastS(netController net) {
        this.net = net;
        try {
            serverIp = InetAddress.getLocalHost();
            System.out.println(serverIp);
            //  group  = InetAddress.getByName("228.5.7.7");
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        
        Thread t = new Thread(this);
        t.start();
        
    }
    
    public void run() {
        stop = false;
        try {
            System.out.println("About to listen for clients");
            sock = new ServerSocket(listenPort);
            //    sock.joinGroup(group);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            
            sock.setSoTimeout(5);
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        while(!stop) {
            try{
                try {
                    Socket s = sock.accept();
                    msg(s);
                    
                } catch (SocketTimeoutException ex) {
                    
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        try {
            //    sock.leaveGroup(group);
            sock.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    private void msg(Socket s) {
        System.out.println(s.getInetAddress());
        net.addQClient(s.getInetAddress());
        //   returnIP(packet.getAddress());
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
