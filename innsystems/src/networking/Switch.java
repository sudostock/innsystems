/*
 * Switch.java
 *
 * Created on May 20, 2007, 2:21 PM
 *
 * Simple class meant to synchornize two threads
 */

package networking;

/**
 *
 * @author Alex Filby
 */
public class Switch {
    private boolean finished;
    
    
    public Switch() {
        finished = false;
    }
    
    public synchronized void next() {
        if(!finished) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public synchronized void finished() {
        finished = true;
        notify();
    }
    
    public void reset() {
        finished = false;
    }
    
}
