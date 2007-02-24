/*
 * netHost.java
 *
 * Created on February 14, 2007, 9:03 PM
 *
 */

package networking;

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
    private InetAddress serverIp;
    
    
    public netHost() {
    
    }

    public boolean connect(byte[] ip_address, int port) {
        return true;
    }

    public void sendData(byte[] data, int length) {
    }
    
    public void listenBC() {
        
    }
    
    public void listenReply() {
        
    }
    
}
