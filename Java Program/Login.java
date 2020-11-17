import java.awt.Color;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class Login {
	// Server part 
	public static Socket guiSocket;
	//
    public static void main(String[] a) {
    	try {
    		guiSocket = new Socket("localhost",5678);
    		System.out.println("Connection with GUI Server established!");
    	}catch(Exception e) {
    		
    	}
    	MainMenu MM = new MainMenu();
        MM.setTitle("Covid-19 Intelligent Tester");
        MM.setVisible(true);
        MM.setBounds(40, 40, 370, 600);
        MM.getContentPane().setBackground(new Color(173,216,230));
        MM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MM.setResizable(false);
        MM.repaint();
    }
}