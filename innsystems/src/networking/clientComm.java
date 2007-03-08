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
 * Adjusted for known server
 *
 * @author Alex Filby
 */
public class clientComm implements Runnable {
    private InetAddress server;
    private ServerSocket recieve;
    private boolean stop;
    private DataInputStream in;
    
    
    public clientComm() {
        Thread t = new Thread(this);
        t.start();
        
    }
    
    public void run() {
        //Following code gets serverIp address
        byte[] buff;
        buff = "HELLO".getBytes();
        int length = buff.length;
        InetAddress serverM = null;
        try {
            serverM = InetAddress.getByName("10.10.31.15"/*"228.5.7.7"*/);
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
                    server = pack.getAddress();
                    stop = true;
                } catch (SocketTimeoutException ex) {
                    
                }}catch (IOException e) {
                    
                }
        
        /* Rest of Code */
        try {
            recieve = new ServerSocket(7778);
            stop = false;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            
            recieve.setSoTimeout(5);
            while(!stop) {
                try{
                    try {
                        Socket s = recieve.accept();
                        msg(s);
                        s.close();
                    } catch (SocketTimeoutException ex) {
                        
                    }
                }catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        
        
        
    }
    
    
    private void msg(Socket s) {
        double particle;
        double numInput;
        double numHidden;
        double learnrate;
        System.out.println("About to get data!");
        try {
            in = new DataInputStream(s.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        try{
            particle = in.readDouble();
            numInput = in.readDouble();
            numHidden = in.readDouble();
            learnrate = in.readDouble();
            System.out.println("done, recieved test data!");
        }catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    
    private void runNet() {
        
    }
    
    
    
    
}
