package app;
/////////////////////////////////
////////////////////////////////
////Login: admin
////password: dinnerdash
////////////////////////////////
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;

public class LogIn extends JFrame implements ActionListener {
	   JPanel panel;
	   JLabel user_label, password_label, message;
	   JTextField userName_text;
	   JPasswordField password_text;
	   JButton submit, cancel;
	   LogIn() {
	      // User name Label
	      user_label = new JLabel();
	      user_label.setText("User Name :");
	      userName_text = new JTextField();
	      // Password Label
	      password_label = new JLabel();
	      password_label.setText("Password :");
	      password_text = new JPasswordField();
	      // Submit
	      submit = new JButton("SUBMIT");
	      panel = new JPanel(new GridLayout(3, 1));
	      panel.add(user_label);
	      panel.add(userName_text);
	      panel.add(password_label);
	      panel.add(password_text);
	      message = new JLabel();
	      panel.add(message);
	      panel.add(submit);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      // Adding the listeners to components..
	      submit.addActionListener(this);
	      getContentPane().add(panel, BorderLayout.CENTER);
	      setTitle("Welcome to Dinner Dash!");
	      setSize(450,308);
	      setVisible(true);
	   }
	   public static void main(String[] args) {
	      new LogIn();
	      
	   }
	     
	   @Override
	   public void actionPerformed(ActionEvent ae) {
	      String userName = userName_text.getText();
	      String password = password_text.getText();
	      if (userName.trim().equals("admin") && password.trim().equals("dinnerdash")) {
	         message.setText(" Hello " + userName + "");
	         File inputFile = new File("menu");
	         try {
				Driver guiFrame = new Driver(inputFile);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				JOptionPane.showMessageDialog(null,"Error! Menu File not found!", "Please reinput", JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Error! Program terminated", " Error", JOptionPane.ERROR_MESSAGE);
			}
	      } else {
	    	 
	         message.setText(" Invalid user. Try Again or Exit Out.");
	         
	      }
	     
	   }
	}
