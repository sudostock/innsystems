/*
 * LocateServer.java
 *
 * Created on April 4, 2007, 11:39 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package networking;

import java.io.IOException;
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
    
    public LocateServer() {
        findServer();
    }
    
    public LocateServer(String filename) {
        findServer(filename);
    }
    
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
    
    private void findServer(String filename) {
        
    }
    
    public InetAddress getServer() {
        if(address)
            return server;
        else
            return null;
    }
    
    
    
}
