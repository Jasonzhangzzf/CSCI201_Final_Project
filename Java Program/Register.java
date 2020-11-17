import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
 //CHANGES NEEDED
public class Register implements ActionListener {
    JFrame frame;
    String[] gender={"Male","Female"};
    String[] yesOrNo={"Yes","No"};
    JLabel nameLabel=new JLabel("USERNAME");
    JLabel genderLabel=new JLabel("GENDER");
    JLabel firstNameLabel=new JLabel("FIRSTNAME");
    JLabel lastNameLabel=new JLabel("LASTNAME");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JLabel confirmPasswordLabel=new JLabel("CONFIRM PASSWORD");
    JLabel ageLabel=new JLabel("AGE");
    JLabel isProLabel=new JLabel("MEDICAL PROFESSIONAL?");
    JTextField nameTextField=new JTextField();
    JComboBox genderComboBox=new JComboBox(gender);
    JTextField firstnameTextField=new JTextField();
    JTextField lastnameTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JPasswordField confirmPasswordField=new JPasswordField();
    JTextField ageTextField=new JTextField();
    JComboBox isProComboBox=new JComboBox(yesOrNo);
    JButton registerButton=new JButton("REGISTER");
    JButton resetButton=new JButton("RESET");
 
 
    Register()
    {
        createWindow();
        setLocationAndSize();
        addComponentsToFrame();
        actionEvent();
    }
    public void createWindow()
    {
        frame=new JFrame();
        frame.setTitle("Registration Form");
        frame.setBounds(40,40,380,600);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(208, 236, 253));
    }
    public void setLocationAndSize()
    {
        nameLabel.setBounds(20,20,100,70);
        genderLabel.setBounds(20,70,80,70);
        firstNameLabel.setBounds(20,120,100,70);
        lastNameLabel.setBounds(20,170,100,70);
        passwordLabel.setBounds(20,220,100,70);
        confirmPasswordLabel.setBounds(20,270,140,70);
        ageLabel.setBounds(20,320,100,70);
        isProLabel.setBounds(20,370,200,70);
        
        nameTextField.setBounds(180,43,165,23);
        genderComboBox.setBounds(180,93,165,23);
        firstnameTextField.setBounds(180,143,165,23);
        lastnameTextField.setBounds(180,193,165,23);
        passwordField.setBounds(180,243,165,23);
        confirmPasswordField.setBounds(180,293,165,23);
        ageTextField.setBounds(180,343,165,23);
        isProComboBox.setBounds(180,393,165,23);
        registerButton.setBounds(70,450,100,35);
        resetButton.setBounds(220,450,100,35);
    }
    public void addComponentsToFrame()
    {
        frame.add(nameLabel);
        frame.add(genderLabel);
        frame.add(firstNameLabel);
        frame.add(lastNameLabel);
        frame.add(passwordLabel);
        frame.add(confirmPasswordLabel);
        frame.add(ageLabel);
        frame.add(isProLabel);
        frame.add(nameTextField);
        frame.add(genderComboBox);
        frame.add(firstnameTextField);
        frame.add(lastnameTextField);
        frame.add(passwordField);
        frame.add(confirmPasswordField);
        frame.add(ageTextField);
        frame.add(isProComboBox);
        frame.add(registerButton);
        frame.add(resetButton);
    }
    public void actionEvent()
    {
       //Adding Action Listener to buttons
        registerButton.addActionListener(this);
        resetButton.addActionListener(this);
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==registerButton)
        {
            try {
            	//TEST IT ON UR PC!!!!!!!!!!!!!!!!!!
            	//IM NOT SURE IT WORKS OR NOT!!!!!!!!!!!!!!!!!!!!
                //Creating Connection Object
                Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/myDatabase","root","root");
                //Preapared Statement
                PreparedStatement Pstatement=connection.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
                //Specifying the values of it's parameter
                Pstatement.setString(1,nameTextField.getText());
                Pstatement.setString(2,genderComboBox.getSelectedItem().toString());
                Pstatement.setString(3,firstnameTextField.getText());
                Pstatement.setString(4,lastnameTextField.getText());
                Pstatement.setString(5,passwordField.getText());
                Pstatement.setString(6,confirmPasswordField.getText());
                Pstatement.setString(7,ageTextField.getText());
                Pstatement.setString(8,isProComboBox.getSelectedItem().toString());
                //checking for duplicate username
                 //NEED TO BE DONE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                //if(...){
                //JOptionPane.showMessageDialog(null,"Username " + nameTextField.getText() +" already exists.");
                //}
                
                //Checking for the Password match
                if(passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText()))
                {
                    //Executing query
                    Pstatement.executeUpdate();
                    JOptionPane.showMessageDialog(frame,"Data Registered Successfully.");
                    frame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(frame,"Password did not match!");
                }
 
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if(e.getSource()==resetButton)
        {
            //Clearing Fields
            nameTextField.setText("");
            genderComboBox.setSelectedItem("Male");
            firstnameTextField.setText("");
            lastnameTextField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
            ageTextField.setText("");
            isProComboBox.setSelectedItem("Yes");
        }
    }
}