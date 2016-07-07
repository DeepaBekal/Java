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
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
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
		DB = new FacePamphletDatabase();
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
			if (cmd.equals("Add")){
				if(DB.containsProfile(NameInput.getText())){
					println("Add: Profile for "+NameInput.getText()+" already exists.");
				}
				else{
					Name = new FacePamphletProfile(NameInput.getText());
					DB.addProfile(Name);
					//CurrentProfile = Name;
					if(DB.containsProfile(NameInput.getText())){
						println("Add: New Profile: "+Name+".");
						println("--> Current profile: "+Name);
					}
				}
			}
			else if (cmd.equals("Delete")){
				if(!DB.containsProfile(NameInput.getText())){
					println("Delete: profile with name "+ NameInput.getText()+" does not exist.");
				}
				else{
					DB.deleteProfile(NameInput.getText());
					if(!DB.containsProfile(NameInput.getText())){
						println("Delete: profile of "+NameInput.getText()+" deleted.");
						Name=null;
					}				
				}
			}
			else if (cmd.equals("Lookup")){
				if(DB.containsProfile(NameInput.getText())){
					Name = DB.getProfile(NameInput.getText());
					println("Lookup: "+Name);
				}
				else {
					println("Lookup: Profile with name "+NameInput.getText()+" does not exist");
					Name=null;
				}
			}
			
			
			
			if (cmd.equals("Change Status")) {
				if(Name!=null) ChangeStatus(Name);
				else println("Please select a profile to change status.");
				//println("Change Status : "+StatusText.getText());
			}
			else if (cmd.equals("Change Picture")){
				if(Name!=null)ChangePicture(Name);
				else println("Please select a profile to change picture.");
				//println("Change Picture : "+PictureText.getText());
			}
			else if (cmd.equals("Add Friend")){
				if(Name!=null){
					String FriendName = FriendText.getText();
					if(FriendName.equals(Name.getName())) println("Enter friend name other than own name.");
					else if(DB.containsProfile(FriendName)){
						if(Name.addFriend(FriendName)){
							Mutual = DB.getProfile(FriendName);
							Mutual.addFriend(Name.getName());
							println(FriendText.getText()+" added as a friend");
							println("--> Current profile: "+Name);
						}
						else println(FriendText.getText()+" is already in friends list");
					}
					else println(FriendName+" profile does not exist.");					
				}
				else println("Please select a profile to add friend to");
				
				//println("Add Friend : "+FriendText.getText());	
			}
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
    
    private void ChangeStatus(FacePamphletProfile FPP){
    		FPP.setStatus(StatusText.getText());
        	println("Status updated to "+FPP.getStatus());
        	println("--> Current profile: "+FPP);
    }
    
    private void ChangePicture(FacePamphletProfile FPP){
    		GImage image = null;
    		String filename = PictureText.getText();
    		try{
    			  image = new GImage(filename); 	
    			  FPP.setImage(image);
    			  println("Picture updated.");
    			  println("--> Current Profile: "+FPP);
    		} catch (ErrorException e){
    			image=null;
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
    private FacePamphletDatabase DB;
    private FacePamphletProfile Name;
    private FacePamphletProfile Mutual;

}
