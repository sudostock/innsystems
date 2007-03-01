/*
 * clientComm.java
 *
 * Created on February 28, 2007, 10:53 PM
 *
 *
 */

package networking;

import java.io.*;
import java.net.*;

/**
 *
 * @author Alex
 */
public class clientComm implements Runnable {
    private InetAddress server;
    
    
    public clientComm() {
        Thread t = new Thread(this);
        t.start();
        
    }
    
    public void run() {
        byte[] buff;
        buff = "HELLO".getBytes();
        int length = buff.length;
        InetAddress serverM = null;
        try {
            serverM = InetAddress.getByName("228.5.7.7");
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        DatagramPacket packet = new DatagramPacket(buff, length, serverM, 7776);
        DatagramSocket socket;
        try {
            socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
        System.out.println("YEP");
        DatagramSocket sock = null;
        boolean stop = false;
        try {
            sock = new DatagramSocket(7777);
        } catch (SocketException ex) {
            System.out.println("WTF");
            ex.printStackTrace();
        }
        buff = new byte[256];
        length = buff.length;
        DatagramPacket pack = new DatagramPacket(buff, length);
        
        while(!stop)
            try{
                sock.setSoTimeout(5);
                try {
                    sock.receive(pack);
                    System.out.println(pack.getAddress());
                    System.out.println("BYAHH");
                    server = pack.getAddress();
                    stop = true;
                } catch (SocketTimeoutException ex) {
        
                }}catch (IOException e) {
        
                } 
        
    }
    
    private void getServerIP() {
        
    }
    
    private void findServer() {
        
    }
    
    
    
    
}
