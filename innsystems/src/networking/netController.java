/*
 * HostComm.java
 *
 * 
 */

package networking;

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Alex Filby
 *
 */
public class netController {
    private int particles;
    private ArrayBlockingQueue qClients;
    private List lClients;
    
    public netController(int particles) {
        this.particles = particles;
        qClients = new ArrayBlockingQueue(particles);
          
        broadcastS clientget = new broadcastS();
        
    }
    
    public synchronized void addQClient(InetAddress address) {
        
    }
    
    public synchronized InetAddress pullQClient() {
        
    }
    
}
