import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
 //CHANGES NEEDED 
public class UserFunctionPage extends JFrame implements ActionListener {
	 JFrame frame;
	 JLabel welcomeLabel = new JLabel("WELCOME!");
	 JButton testButton = new JButton("TAKE A TEST");
	 JButton resultButton = new JButton("CHECK PREVIOUS RESULTS");
	 JButton chatButton = new JButton("ENTER CHAT ROOM");
 
    UserFunctionPage() {
    	createWindow();
        setLocationAndSize();
        addComponents();
        addActionEvent();
 
    }
    
    public void createWindow()
    {
    	 frame=new JFrame();
         frame.setTitle("Functions For Authenticated Users");
         frame.setBounds(40,40,380,600);
         frame.getContentPane().setLayout(null);
         frame.setVisible(true);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setResizable(false);
         frame.getContentPane().setBackground(new Color(208, 236, 253));
    }
 
    public void setLocationAndSize() {
    	welcomeLabel.setBounds(40, 40, 380 ,40);
    	testButton.setBounds(60, 150, 240, 50);
        resultButton.setBounds(60, 300, 240, 50);
        chatButton.setBounds(60, 450, 240, 50);
    }
    
    public void addComponents() {
    	frame.add(welcomeLabel);
        frame.add(testButton);
        frame.add(resultButton);
        frame.add(chatButton);
    }
    
    public void addActionEvent() {
        testButton.addActionListener(this);
        resultButton.addActionListener(this);
        chatButton.addActionListener(this);
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
     
        if (e.getSource() == testButton) {
        	frame.dispose();
        	TestTaking TT = new TestTaking();
        }
    
        if (e.getSource() == resultButton) {
            
        }

        if (e.getSource() == chatButton) {
       //need the MAIN SERVER to link to the chatroom
        	//open Webpage of the chatroom
        	try {
				openWebpage(new URL("http://www.baidu.com"));
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        }
    }
    
    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
 
}