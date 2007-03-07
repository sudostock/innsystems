/*
 * RecieveComm.java
 *
 * Created on March 5, 2007, 8:11 PM
 *
 *
 */

package networking;

import java.net.*;
import java.io.*;


/**
 * Class takes incoming data from the network and passes it to the netController class
 *
 * @author Alex Filby
 */
public class RecieveComm implements Runnable{
    private netController controller;
    private final int listenport = 7779;
    private ServerSocket sock;
    private boolean stop;
    private DataInputStream in;
    
    
    public RecieveComm(netController controller) {
        this.controller = controller;
        Thread t = new Thread(this);
        stop = false;
        t.start();
    }
    
    public void run() {
        try {
            
            sock = new ServerSocket(listenport);
            sock.setSoTimeout(5);
        } catch(SocketException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        while(!stop) {
            
            try{
                Socket s = sock.accept();
                msg(s);
                s.close();
            }catch(SocketTimeoutException e) {
                
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        
        
    }
    
    
    private void msg( Socket s ) {
        int particle = -1;
        int epochs = -1;
        double error = -1;
        
        
        try {
            in = new DataInputStream(s.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        try{
            particle = in.readInt();
            epochs = in.readInt();
            error = in.readDouble();
        }catch(IOException e) {
            e.printStackTrace();
        }
        
        controller.storeResults(particle, epochs, error);
        try {
            
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
    
}
