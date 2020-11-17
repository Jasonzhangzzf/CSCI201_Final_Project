import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**

 */
public class PyClassificationThread implements Runnable{

    protected Socket s = null;
    protected String serverText   = null;
    private CopyOnWriteArrayList<String> user_Data = new CopyOnWriteArrayList<String>();
    private CopyOnWriteArrayList<String> result = new CopyOnWriteArrayList<String>();
    private Lock lock = new ReentrantLock();
	private Condition sleepingCondition = lock.newCondition();
	private GraphicalResultThread gt;
    
    public PyClassificationThread(Socket s, CopyOnWriteArrayList user_Data, CopyOnWriteArrayList<String> result, GraphicalResultThread gt) {
    	this.s = s;
    	this.user_Data = user_Data;
    	this.result = result;
    	this.gt = gt;
    }

    public void run() {
        try {
			System.out.println("Classification Server Started");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			while(true) {
				while(!user_Data.isEmpty()) {
					//String line = br.readLine();
					//System.out.println("Received from Python Classification: " + line);
		
					String str = user_Data.remove(0);
					System.out.println("Sending to Python Classification: " + str);
					pw.println(str);
					
					if(str.charAt(0) == '0') {
						String response = br.readLine();
						result.add(response);
						StringBuilder sb = new StringBuilder();
						sb.append(str.substring(9,13) + str.charAt(14) + str.charAt(16) + response.charAt(8));
						String packet = sb.toString();
						gt.wakeUpGrahpicalResult(packet);
						
					}
				}
				try {
					lock.lock();
					System.out.println("Classification complete, waiting for next process");
					sleepingCondition.await();
					System.out.println("GUI woke me up!");
					lock.unlock();
					
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
    
    public void wakeUpPy() {
		/*
		 * Your code here
		 */
		try {
			this.lock.lock();
			sleepingCondition.signal();
		}
		finally {
			this.lock.unlock();
		}
	}
    
}