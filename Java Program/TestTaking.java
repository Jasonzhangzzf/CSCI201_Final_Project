import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
 //CHANGES NEEDED
public class TestTaking implements ActionListener {
    JFrame frame;
    JLabel instruLabel = new JLabel("Select YES if you have this symptom, NO otherwise:");
    String[] yesOrNo={"Yes","No"};
    String[] contact={"Yes","No","I don't know"};
    ArrayList<String> _yesOrNo = new ArrayList<String>(Arrays.asList("No","Yes"));
    JLabel feverLabel=new JLabel("Fever");
    JLabel tiredLabel=new JLabel("Tiredness");
    JLabel coughLabel=new JLabel("Dry-Cough");
    JLabel breathLabel=new JLabel("Difficulty-in-Breathing");
    JLabel soretLabel=new JLabel("Sore-Throat");
    JLabel painLabel=new JLabel("Pains");
    JLabel nasalLabel=new JLabel("Nasal-Congestion");
    JLabel noseLabel=new JLabel("Runny-Nose");
    JLabel diarrheaLabel=new JLabel("Diarrhea");
    JLabel contactLabel=new JLabel("Contacts with Covid19 patients?");
    JComboBox comboBox1=new JComboBox(yesOrNo);
    JComboBox comboBox2=new JComboBox(yesOrNo);
    JComboBox comboBox3=new JComboBox(yesOrNo);
    JComboBox comboBox4=new JComboBox(yesOrNo);
    JComboBox comboBox5=new JComboBox(yesOrNo);
    JComboBox comboBox6=new JComboBox(yesOrNo);
    JComboBox comboBox7=new JComboBox(yesOrNo);
    JComboBox comboBox8=new JComboBox(yesOrNo);
    JComboBox comboBox9=new JComboBox(yesOrNo);
    JComboBox comboBoxContact=new JComboBox(contact);
    JButton registerButton=new JButton("SUBMIT");
    
    TestTaking()
    {
        createWindow();
        setLocationAndSize();
        addComponentsToFrame();
        actionEvent();
    }
    public void createWindow()
    {
        frame=new JFrame();
        frame.setTitle("Tester");
        frame.setBounds(40,40,380,600);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(208, 236, 253));
    }
    public void setLocationAndSize()
    {
    	instruLabel.setBounds(0, 0, 380, 20);
        feverLabel.setBounds(20,20,100,70);
        tiredLabel.setBounds(20,70,80,70);
        coughLabel.setBounds(20,120,100,70);
        breathLabel.setBounds(20,170,200,70);
        soretLabel.setBounds(20,220,100,70);
        painLabel.setBounds(20,270,140,70);
        nasalLabel.setBounds(20,320,200,70);
        noseLabel.setBounds(20,370,200,70);
        diarrheaLabel.setBounds(20,420,200,70);
        contactLabel.setBounds(20,470,200,70);
        comboBox1.setBounds(210,43,75,23);
        comboBox2.setBounds(210,93,75,23);
        comboBox3.setBounds(210,143,75,23);
        comboBox4.setBounds(210,193,75,23);
        comboBox5.setBounds(210,243,75,23);
        comboBox6.setBounds(210,293,75,23);
        comboBox7.setBounds(210,343,75,23);
        comboBox8.setBounds(210,393,75,23);
        comboBox9.setBounds(210,443,75,23);
        comboBoxContact.setBounds(210,493,75,23);
        registerButton.setBounds(130,530,100,35);
    }
    public void addComponentsToFrame()
    {
    	frame.add(instruLabel);
        frame.add(feverLabel);
        frame.add(tiredLabel);
        frame.add(coughLabel);
        frame.add(breathLabel);
        frame.add(soretLabel);
        frame.add(painLabel);
        frame.add(nasalLabel);
        frame.add(noseLabel);
        frame.add(diarrheaLabel);
        frame.add(contactLabel);
        frame.add(comboBox1);
        frame.add(comboBox2);
        frame.add(comboBox3);
        frame.add(comboBox4);
        frame.add(comboBox5);
        frame.add(comboBox6);
        frame.add(comboBox7);
        frame.add(comboBox8);
        frame.add(comboBox9);
        frame.add(comboBoxContact);
        frame.add(registerButton);
    }
    public void actionEvent()
    {
       //Adding Action Listener to buttons
        registerButton.addActionListener(this);
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==registerButton)
        {
    		// Server part
    		StringBuffer str = new StringBuffer("");
    		str.append("0");
    		str.append("00000000");
    		str.append(_yesOrNo.indexOf(comboBox1.getSelectedItem().toString()));
    		str.append(_yesOrNo.indexOf(comboBox2.getSelectedItem().toString()));
    		str.append(_yesOrNo.indexOf(comboBox3.getSelectedItem().toString()));
    		str.append(_yesOrNo.indexOf(comboBox4.getSelectedItem().toString()));
    		str.append(_yesOrNo.indexOf(comboBox5.getSelectedItem().toString()));
    		str.append(_yesOrNo.indexOf(comboBox6.getSelectedItem().toString()));
    		str.append(_yesOrNo.indexOf(comboBox7.getSelectedItem().toString()));
    		str.append(_yesOrNo.indexOf(comboBox8.getSelectedItem().toString()));
    		str.append(_yesOrNo.indexOf(comboBox9.getSelectedItem().toString()));
    		if(comboBox9.getSelectedItem().toString() == "Yes") {
    			str.append("001");
    		}else if(comboBox9.getSelectedItem().toString() == "No") {
    			str.append("010");
    		}else {
    			str.append("100");
    		}
    		
    		try {
				PrintWriter pw = new PrintWriter(Login.guiSocket.getOutputStream(), true);
				pw.println(str);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    		
    		
    		/*
            try {
            	//TEST IT ON UR PC!!!!!!!!!!!!!!!!!!
            	//THE SAME IDEA AS REGISTER CLASS -- CHANGE THE VARIABLES
            	//IM NOT SURE IT WORKS OR NOT!!!!!!!!!!!!!!!!!!!!
                //Creating Connection Object
                Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/myDatabase","root","root");
                //Preapared Statement
                PreparedStatement Pstatement=connection.prepareStatement("insert into test values(?,?,?,?,?,?,?)");
                //Specifying the values of it's parameter
                /*
                Pstatement.setString(1,nameTextField.getText());
                Pstatement.setString(2,genderComboBox.getSelectedItem().toString());
                Pstatement.setString(3,firstnameTextField.getText());
                Pstatement.setString(4,lastnameTextField.getText());
                Pstatement.setString(5,passwordField.getText());
                Pstatement.setString(6,confirmPasswordField.getText());
                Pstatement.setString(7,ageTextField.getText());
                Pstatement.setString(8,isProComboBox.getSelectedItem().toString());
               
                //FINISHING SAVING IN DATABASE
                JOptionPane.showMessageDialog(frame,"Test Submitted Successfully.");
                JOptionPane.showMessageDialog(frame,"Click and Forward to the Test Result.");  
                frame.dispose();
                //link to the test result display
                //work required HERE
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            */
        }
    }
}