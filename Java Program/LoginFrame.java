import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 //CHANGES NEEDED
public class LoginFrame extends JFrame implements ActionListener {
	JFrame frame;
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
 
 
    LoginFrame() {
    	createWindow();
        setLocationAndSize();
        addComponents();
        addActionEvent();
 
    }
    
    public void createWindow()
    {
    	 frame=new JFrame();
         frame.setTitle("Login");
         frame.setBounds(40,40,380,600);
         frame.getContentPane().setLayout(null);
         frame.setVisible(true);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setResizable(false);
         frame.getContentPane().setBackground(new Color(208, 236, 253));
         showPassword.setBackground(new Color(208, 236, 253));
    }
 
    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
    }
 
    public void addComponents() {
        frame.add(userLabel);
        frame.add(passwordLabel);
        frame.add(userTextField);
        frame.add(passwordField);
        frame.add(showPassword);
        frame.add(loginButton);
        frame.add(resetButton);
    }
 
    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            //CODE NEEDED HERE
            //CHECK WHETHER THERE IS SUCH USER, AND STORE THE USER INFO IN THE USER CLASS
            if (userText.equalsIgnoreCase("pupu") && pwdText.equalsIgnoreCase("12345")) {
                JOptionPane.showMessageDialog(frame, "Login Successful");
                frame.dispose();
                UserFunctionPage UFP = new UserFunctionPage();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
            }
 
        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
 
 
        }
    }
 
}