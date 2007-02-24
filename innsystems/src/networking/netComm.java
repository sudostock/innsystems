/*
 * netComm.java
 *
 * Created on February 13, 2007, 11:13 AM
 *
 */

package networking;

import java.io.IOException;
import java.net.*;
import java.util.*;


/**
 * Responsible for communication between server and client. Handles all of the sending and recieving of data
 *
 *
 * @author Alex Filby
 * @version 0.0.1 2/13/06
 */
public class netComm {
    
    private String clientip;
    private String destinationip;
    private InetAddress client_ip;
    private InetAddress destination_ip;
    private final int netlength = 4;
    private int destPort;
    
    
    
    public netComm(String clientip) {
        this.clientip = clientip;
        this.destinationip = null;
        
        try {
            client_ip = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }
    
    public netComm(String clientip, String destinationip, int destPort) {
        this.clientip = clientip;
        this.destinationip = destinationip;
        this.destPort = destPort;
        
        try {
            client_ip = InetAddress.getLocalHost();
            destination_ip = InetAddress.getByAddress(ip_ToByte(destinationip));
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void sendData(byte[] data, int length) {
        
    }
    
    public void findHost() {
        DatagramSocket ds = null;
        DatagramPacket p;
        byte[] data = ip_ToByte(clientip);
        int length = data.length;
        try {
            
            ds.send( new DatagramPacket( data, length, InetAddress.getByAddress(ip_ToByte("10.0.255.255")), 30));
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        p = new DatagramPacket(data, length);
        try {
            ds.receive(p);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        destination_ip = p.getAddress();
    }
    
    public void returnHost() {
        DatagramSocket ds = null;
        DatagramPacket p;
        byte[] data = ip_ToByte(clientip);
        int length = data.length;
        
                
    }
    
    
    
    
    /** Converts and IP address to a byte array */
    private byte[] ip_ToByte(String serverip) {
        StringTokenizer token = new StringTokenizer(serverip, ".");
        int[] ipAddr = new int[netlength];
        for(int i = 0; i < ipAddr.length; i++) {
            ipAddr[i] = Integer.parseInt(token.nextToken());
        }
        byte[] byteip = new byte[]{(byte)ipAddr[0], (byte)ipAddr[1], (byte)ipAddr[2], (byte)ipAddr[3]};
        return byteip;
    }
    
} 
