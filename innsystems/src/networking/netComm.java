/*
 * netComm.java
 *
 * Created on February 13, 2007, 11:13 AM
 *
 */

package networking;




/**
 * Interface for communications between server and client
 *
 *
 * @author Alex Filby
 * @version 0.0.1 2/24/06
 */
public interface netComm {
    
    final int ip_length = 4; // length of ip address
    final int hostListen = 7776;//host listens for cleints trying to find server
    final int clientListen = 7777; //client listens for host to send data to it
    final int hostConnListen = 7778; //host listens for client to send results
    
    boolean connect(byte[] ip_address, int port);
    
    void sendData(byte[] data, int length);
    
    
    
    
}
