/*
 * controllertest.java
 *
 * Created on March 6, 2007, 7:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package networking;

/**
 *
 * @author Alexander.Filby
 */
public class controllertest {
    
    /** Creates a new instance of controllertest */
    public controllertest() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        netController net = new netController(1);
        broadcastS broad = new broadcastS(net);
        
        try {
            Thread.sleep(15000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        double temp[][] = new double[1][3];
        temp[0][0] = 5;
        temp[0][1] = 5;
        temp[0][2] = .7;
        
        net.storeTestData(temp);
        broad.stop();
        RecieveComm r = new RecieveComm(net);
        SendData s = new SendData(net);
        
    }
    
}
