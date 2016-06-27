/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends ConsoleProgram 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		StatusText = new JTextField(TEXT_FIELD_SIZE);
		StatusButton = new JButton("Change Status");
		PictureText = new JTextField(TEXT_FIELD_SIZE);
		PictureButton = new JButton("Change Picture");
		FriendText = new JTextField(TEXT_FIELD_SIZE);
		FriendButton = new JButton("Add Friend");
		NameLabel = new JLabel("Name");
		NameInput = new JTextField(TEXT_FIELD_SIZE);
		AddProfile = new JButton("Add");
		DeleteProfile = new JButton("Delete");
		LookUpProfile = new JButton("Lookup");
		add(StatusText,WEST);
		StatusText.addKeyListener(this);
		add(StatusButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(PictureText,WEST);
		PictureText.addKeyListener(this);
		add(PictureButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(FriendText,WEST);
		FriendText.addKeyListener(this);
		add(FriendButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(NameLabel,NORTH);
		add(NameInput, NORTH);
		add(AddProfile, NORTH);
		add(DeleteProfile, NORTH);
		add(LookUpProfile, NORTH);
		addActionListeners();		
	}
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(NameInput.getText().equals(""))println("Please enter Name.");
		else{
			if (cmd.equals("Add")) println("Add : "+NameInput.getText());
			else if (cmd.equals("Delete")) println("Delete : "+NameInput.getText());
			else if (cmd.equals("Lookup")) println("Lookup : "+NameInput.getText());
			if (cmd.equals("Change Status")) println("Change Status : "+StatusText.getText());
			else if (cmd.equals("Change Picture")) println("Change Picture : "+PictureText.getText());
			else if (cmd.equals("Add Friend")) println("Add Friend : "+FriendText.getText());	
			
		}		
	}
    
    public void keyPressed(KeyEvent e){
    	if(e.getKeyCode()==KeyEvent.VK_ENTER && e.getSource()==StatusText){
    		println("Change Status : "+StatusText.getText());
    	}
    	else if(e.getKeyCode()==KeyEvent.VK_ENTER && e.getSource()==PictureText){
    		println("Change Picture : "+PictureText.getText());
    	}
    	else if(e.getKeyCode()==KeyEvent.VK_ENTER && e.getSource()==FriendText){
    		println("Add Friend : "+FriendText.getText());
    	}
    }
    
    /*Private instance variables*/
    private JTextField StatusText;
    private JButton StatusButton;
    private JTextField PictureText;
    private JButton PictureButton;
    private JTextField FriendText;
    private JButton FriendButton;
    private JLabel NameLabel;
    private JTextField NameInput;
    private JButton AddProfile;
    private JButton DeleteProfile;
    private JButton LookUpProfile;

}
