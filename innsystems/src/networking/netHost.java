/*
 * netHost.java
 *
 * Created on February 14, 2007, 9:03 PM
 *
 */

package networking;

import java.io.*;
import java.util.*;
import java.net.*;

/**
 * Responsible for being the host or the "controller" on the distributed network
 *
 * @author Alex Filby
 * @version 0.0.1 2/14/07
 *
 */


public class netHost implements netComm {
    
    private InetAddress clientList[];
    private static InetAddress serverIp;
    private static byte[] serverIP;
    private hostBC bc;
    private hostReply ry;
    private int pointer = 0;
    private static double results[][];
    
    
    public netHost(int clients, int particles) {
        try {
            serverIp = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        serverIP = serverIp.getAddress();
        bc = new hostBC(serverIP);
        clientList = new InetAddress[clients];
        results = new double[particles][2];
        
    }
    
    public boolean connect(byte[] ip_address, int port) {
        return true;
    }
    
    public void sendData(byte[] data, int length) {
    }
    
    public void startRun() {
        bc.stop_Server();
        
    }
    
    public void stop_listenBC() {
        bc.stop_Server();
        ry = new hostReply(clientList.length);
    }
    
    public static void fromBC(Socket s) {
        byte[] buff = null;
        int length = 0;
        DataInputStream inClient = null;
        DataOutputStream outClient = null;
        try{
            inClient = new DataInputStream(s.getInputStream());
            outClient = new DataOutputStream(s.getOutputStream());
            while(true) {
                if(inClient.available() > 0) {
                    length = inClient.readInt();
                    buff = new byte[length];
                    inClient.readFully(buff);
                    break;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        try {
            
            InetAddress client = InetAddress.getByAddress(buff);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        try {
            
            outClient.writeInt(serverIP.length);
            outClient.write(serverIP);
            outClient.flush();
            inClient.close();
            outClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    public static void fromRY(Socket s) {
        int particle = 0;
        double error = 0.0;
        int epochs = 0;
        DataInputStream inClient = null;
        DataOutputStream outClient = null;
        try{
            inClient = new DataInputStream(s.getInputStream());
            outClient = new DataOutputStream(s.getOutputStream());
            while(true) {
                if(inClient.available() > 0) {
                    particle = inClient.readInt();
                    error = inClient.readDouble();
                    epochs = inClient.readInt();
                    break;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        addResult(particle, error, epochs);
        
        try {
            inClient.close();
            outClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void addClient(InetAddress client) {
        clientList[pointer] = client;
        pointer++;
    }
    
    private InetAddress getClient(int clientnum) {
        return clientList[clientnum];
    }
    
    private static void addResult(int particle, double error, int epochs) {
        results[particle][0] = error;
        results[particle][1] = epochs;
    }
    
}
