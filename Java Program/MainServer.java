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
		System.out.println("Establish connection with GUI");
		Socket classificationSocket = new ServerSocket(classification_port_number).accept();
		System.out.println("Establish connection with Python Classification");
		Socket graphicalSocket = new ServerSocket(graphical_port_number).accept();
		System.out.println("Establish connection with Graphical Webpage");
		GraphicalResultThread graphicalThread = new GraphicalResultThread(graphicalSocket);
		PyClassificationThread classificationThread = new PyClassificationThread(classificationSocket, user_Data, resultList, graphicalThread);
		GUIThread guiThread = new GUIThread(guiSocket, user_Data, classificationThread);
		es.execute(guiThread);	
		es.execute(classificationThread);
		es.execute(graphicalThread);
			
		
	}
}