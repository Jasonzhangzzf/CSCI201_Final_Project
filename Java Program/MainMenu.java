import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
 //NO CHANGES TO THIS FILE
public class MainMenu extends JFrame implements ActionListener {
    Container container = getContentPane();
    ImageIcon icon = new ImageIcon("title.png");
    JButton loginButton = new JButton("LOGIN");
    JButton guestButton = new JButton("ENTER AS GUEST");
    JButton registerButton = new JButton("REGISTER");
    JPictureBox PB = new JPictureBox();
    
    MainMenu() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
  
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
        loginButton.setBounds(105, 300, 150, 30);
        guestButton.setBounds(105, 380, 150, 30);
        registerButton.setBounds(105, 460, 150, 30);
    }
 
    public void addComponentsToContainer() {
    	PB.setIcon(icon);
    	PB.setBounds(27, 50, 300, 170);
    	container.add(PB);
        container.add(guestButton);
        container.add(loginButton);
        container.add(registerButton);
    }
 
    public void addActionEvent() {
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        guestButton.addActionListener(this);
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
        	dispose();
            LoginFrame LF = new LoginFrame();
 
        }
        if (e.getSource() == registerButton) {
        	dispose();
        	Register R = new Register();
        }
        if (e.getSource() == guestButton) {
        	dispose();
            GuestFunctionPage GFP = new GuestFunctionPage();
        }
    }
 
}