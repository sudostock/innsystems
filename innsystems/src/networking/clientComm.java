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
        InetAddress serverM = null;
        try {
            serverM = InetAddress.getByName("10.10.255.255"/*"228.5.7.7"*/);
            System.out.println(serverM);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        Socket socket;
        try {
            socket = new Socket(serverM, 7776);
            server = socket.getInetAddress();
            System.out.println(server);
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
     /*   System.out.println("YEP");
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
      
                }*/
        
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
            System.out.println(particle);
            numInput = in.readDouble();
            System.out.println(numInput);
            numHidden = in.readDouble();
            System.out.println(numHidden);
            learnrate = in.readDouble();
            System.out.println(learnrate);
            System.out.println("done, recieved test data!");
        }catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    
    private void runNet() {
        
    }
    
    
    
    
}
