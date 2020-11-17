import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**

 */
public class PyClassificationThread implements Runnable{

    protected Socket s = null;
    protected String serverText   = null;
    private CopyOnWriteArrayList<String> user_Data = new CopyOnWriteArrayList<String>();
    private CopyOnWriteArrayList<String> result = new CopyOnWriteArrayList<String>();
    
    public PyClassificationThread(Socket s, CopyOnWriteArrayList user_Data, CopyOnWriteArrayList<String> result) {
    	this.s = s;
    	this.user_Data = user_Data;
    	this.result = result; 
    }

    public void run() {
        try {
			System.out.println("Classification Server Started");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			
			String line = br.readLine();
			System.out.println("Received from Client: " + line);

			String str = user_Data.remove(0);
			System.out.println("Sending to Client: " + str);
			pw.println(str);
			
			if(str.charAt(0) == '0') {
				String response = br.readLine(); 
				result.add(response);
			}

        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}