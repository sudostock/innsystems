/*
 * RecieveComm.java
 *
 * Created on March 5, 2007, 8:11 PM
 *
 *
 */

package networking;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.IOException;


/**
 * Class takes incoming data from the network and passes it to the netController class
 *
 * @author Alex Filby
 */
public class RecieveComm implements Runnable{
    private netController controller;
    private int generation;
    private final int listenport = 7780;
    private ServerSocket sock;
    private boolean stop;
    private DataInputStream in;
    private Thread t;
    
    
    public RecieveComm(netController controller) {
        this.controller = controller;
        t = new Thread(this);
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
                generation = controller.getGeneration();
                msg(s);
                s.close();
            }catch(SocketTimeoutException e) {
                
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        
        
    }
    
    
    private void msg( Socket s ) {
        int gen = -1;
        int particle = -1;
        int epochs = -1;
        double error = -1;
        
        
        try {
            in = new DataInputStream(s.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        try{
            gen = in.readInt();
            if(generation != gen){
                System.out.println("NOPE!");
                controller.addQClient(s.getInetAddress());
                return;
            }
            particle = in.readInt();
            epochs = in.readInt();
            error = in.readDouble();
        }catch(IOException e) {
            e.printStackTrace();
        }
        
        controller.storeResults(particle, epochs, error);
        //System.out.println("Storing results");
        controller.addQClient(s.getInetAddress());
        try {
            
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void stopTT() {
        stop = true;
    }
    
  
}
