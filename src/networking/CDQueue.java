/*
 * CDQueue.java
 *
 *
 */

package networking;

import java.util.concurrent.ArrayBlockingQueue;

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
    
    /** Creates an instance of CDQueue */
    public CDQueue() {
        q = new ArrayBlockingQueue(size);
        data = false;
    }
    
    /** Creates an instance of CDQueue 
     * @param size set an explicit size of the Queue
     */
    public CDQueue(int size) {
        q = new ArrayBlockingQueue(size);
        data = false;
    }
    
    /** Places an object into the Queue
     * @param obj Any object the user wants to store
     */
    public synchronized void put(Object obj) {
        try {
            q.put(obj);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        data = true;
        notify();
    }
    
    /** Removes the first item in the Queue
     * @returns Object
     */
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
    
    /** Checks for data in queue
     * @returns boolean
     */
    public boolean isEmpty() {
        return q.isEmpty();
    }
    
    public void changeSize(int size) {
        q = new ArrayBlockingQueue(size);
    }
    
}
