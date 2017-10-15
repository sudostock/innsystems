/*
 * LocateServer.java
 *
 * Created on April 4, 2007, 11:39 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package networking;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Simple class that at the moment just finds the server by being hard coded or through use of a list
 *
 * @author Alexander.Filby
 */

public class LocateServer {
    private InetAddress server;
    private final String defaultServer = "10.10.31.15";
    private final int port = 7776;
    private boolean address = false;
    private String serverlist;
    
    /** Creates instance of LocateServer, default */
    public LocateServer() {
        findServer();
    }
    
    /** Creates instance of LocateServer
     * @param filename name of the serverlist file, file extension is irrelevant
     */
    public LocateServer(String filename) {
        findServer(filename);
    }
    
    /** findServer tries to connect to the default server */
    private void findServer() {
        try {
            server = InetAddress.getByName(defaultServer);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        
        Socket sock = null;
        try {
            sock = new Socket(server, port);
        } catch (Exception e) {
            System.out.println("Failed to connect to server!");
            e.printStackTrace();
            System.exit(1);
        }
        try {
            address = true;
            sock.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
     /** Cycles through list of included server ips until it find a server
      * @param filename name of file that includes server ips
      */
    private void findServer(String filename) {
        serverlist = filename;
        BufferedReader inFile = null;
        try {
            inFile = new BufferedReader(new InputStreamReader(new FileInputStream(serverlist)));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        String serverT = null;
        while(true) {
            try {
                serverT = inFile.readLine();
                if(serverT == null) break;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            try {
                server = InetAddress.getByName(serverT);
            } catch (UnknownHostException ex) {
                System.out.println("Unknown Host");
                continue;
            }
            
            Socket sock = null;
            try {
                sock = new Socket(server, port);
            } catch (Exception e) {
                System.out.println("Failed to connect to server!");
                continue;
            }
            try {
                address = true;
                sock.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
    }
    
    /** Fetches server ip find by locate method
     * @returns ip address of server
     */
    public InetAddress getServer() {
        if(address)
            return server;
        else
            return null;
    }
    
    
    
}
