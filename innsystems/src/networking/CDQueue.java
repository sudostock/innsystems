/*
 * CDQueue.java
 *
 *
 */

package networking;

import java.util.concurrent.*;

/**
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
