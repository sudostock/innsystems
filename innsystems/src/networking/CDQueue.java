/*
 * CDQueue.java
 *
 *
 */

package networking;

import java.util.concurrent.*;

/**
 * This class uses the ArrayBlockingQueue and a wait and notify scheme, so that all of the threads in
 * the main program can talk to each other and use wait and notify and only call those threads which use
 * that particular instance of an object from this class
 *
 * @author Alex Filby
 */
public class CDQueue {
    private ArrayBlockingQueue q;
    private int size = 50;
    private boolean data;
    
    /** Creates a new instance of CDQueue */
    public CDQueue() {
        q = new ArrayBlockingQueue(size);
        data = false;
    }
    
    public CDQueue(int size) {
        q = new ArrayBlockingQueue(size);
        data = false;
    }
    
    public synchronized void put(Object obj) {
        try {
            q.put(obj);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        data = true;
        notify();
    }
    
    public synchronized Object take() {
        Object obj = null;
        if(!data) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            obj = q.take();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        if(q.isEmpty())
            data = false;
        return obj;
    }
    
    public boolean isEmpty() {
        return q.isEmpty();
    }
    
}
