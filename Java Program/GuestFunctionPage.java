import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestFunctionPage extends JFrame implements ActionListener {
	 JFrame frame;
	 JLabel welcomeLabel = new JLabel("REGISTER AND LOGIN IN TO UNLOCK ALL FUNCTIONS.");
	 JButton testButton = new JButton("TAKE A TEST");
	 JButton resultButton = new JButton("CHECK PREVIOUS RESULTS");
	 JButton chatButton = new JButton("ENTER CHAT ROOM");
	 JButton registerButton = new JButton("REGISTER NOW");
	 
    GuestFunctionPage() {
    	createWindow();
        setLocationAndSize();
        addComponents();
        addActionEvent();
    }
    
    public void createWindow()
    {
    	 frame=new JFrame();
         frame.setTitle("Functions For Guests");
         frame.setBounds(40,40,380,600);
         frame.getContentPane().setLayout(null);
         frame.setVisible(true);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.getContentPane().setBackground(new Color(208, 236, 253));
         frame.setResizable(false);
    }
 
    public void setLocationAndSize() {
    	welcomeLabel.setBounds(20, 20, 380 ,40);
    	testButton.setBounds(60, 100, 240, 50);
        resultButton.setBounds(60, 200, 240, 50);
        resultButton.setEnabled(false);
        chatButton.setBounds(60, 300, 240, 50);
        chatButton.setEnabled(false);
        registerButton.setBounds(60, 400, 240, 50);
    }
    
    public void addComponents() {
    	frame.add(welcomeLabel);
        frame.add(testButton);
        frame.add(resultButton);
        frame.add(chatButton);
        frame.add(registerButton);
    }
    
    public void addActionEvent() {
        testButton.addActionListener(this);
        resultButton.addActionListener(this);
        chatButton.addActionListener(this);
        registerButton.addActionListener(this);
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
        }
        if(e.getSource() == registerButton) {
        	frame.dispose();
        	Register R = new Register();
        }
    }
 
}