import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**

 */
public class MainServer{
	protected static int classification_port_number = 6789;
	protected static int gui_port_number = 5678;
	protected static int graphical_port_number = 4567;
	public static void main(String [] args) throws IOException{
		CopyOnWriteArrayList<String> user_Data = new CopyOnWriteArrayList<String>();
		CopyOnWriteArrayList<String> resultList = new CopyOnWriteArrayList<String>();
		ExecutorService es = Executors.newCachedThreadPool();
		Socket guiSocket = new ServerSocket(gui_port_number).accept();
		GUIThread guiThread = new GUIThread(guiSocket, user_Data);
		es.execute(guiThread);	
		
		// to be deleted
		user_Data.add("1011111111100110011111");
		//
		while(true) {
			if(!user_Data.isEmpty()) {
				Socket classificationSocket = new ServerSocket(classification_port_number).accept();
				PyClassificationThread classificationThread = new PyClassificationThread(classificationSocket, user_Data, resultList);
				System.out.println("Starting connection with Python Classification");
				es.execute(classificationThread);
				
				//wait for result to be returned to list
				while(resultList.isEmpty()) {
				}
				
				String result = resultList.remove(0);
				Socket graphicalSocket = new ServerSocket(graphical_port_number).accept();
				GraphicalResultThread graphicalThread = new GraphicalResultThread(graphicalSocket, result);
				es.execute(graphicalThread);
			}
		}
	}
}