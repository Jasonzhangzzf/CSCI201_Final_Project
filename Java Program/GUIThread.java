import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class GUIThread implements Runnable {
	protected Socket s = null;
	protected CopyOnWriteArrayList<String> user_Data;
	private PyClassificationThread ct;
	public GUIThread(Socket s, CopyOnWriteArrayList<String> user_Data, PyClassificationThread ct) {
    	this.s = s;
    	this.user_Data = user_Data;
    	this.ct = ct;
    }

	@Override
	public void run() {
		try {
			System.out.println("GUI Server Started");		
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			while(true) {
				String line = br.readLine();
				System.out.println("Received from GUI: " + line);
				this.user_Data.add(line);
				this.ct.wakeUpPy();
			}
			

        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
		
	}

}
