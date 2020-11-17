import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class GraphicalResultThread implements Runnable {
	protected Socket s = null;
	protected String result;
	public GraphicalResultThread(Socket s, String result) {
    	this.s = s;
    	this.result = result;
    }

	@Override
	public void run() {
		try {
			System.out.println("Graphical Result Server Started");		
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			System.out.println("Sending to Client: " + this.result);
			pw.println(this.result);

        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
		
	}
	

}
