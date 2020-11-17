import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GraphicalResultThread implements Runnable {
	protected Socket s = null;
	protected String result = null;
	private Lock lock = new ReentrantLock();
	private Condition sleepingCondition = lock.newCondition();
	
	public GraphicalResultThread(Socket s) {
    	this.s = s;
    }

	@Override
	public void run() {
		try {
			System.out.println("Graphical Result Server Started");		
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			System.out.println("Sending to Graphical Result: " + this.result);
			while(true) {
				lock.lock();
				System.out.println("waiting for classification result");
				sleepingCondition.await();
				System.out.println("classification woke me up!");
				lock.unlock();
				pw.println(this.result);
			}

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void wakeUpGrahpicalResult(String result) {
		/*
		 * Your code here
		 */
		try {
			this.result = result;
			this.lock.lock();
			sleepingCondition.signal();
		}
		finally {
			this.lock.unlock();
		}
	}
	

}

