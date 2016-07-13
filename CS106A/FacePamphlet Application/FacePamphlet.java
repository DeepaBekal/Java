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

public class FacePamphlet extends Program implements FacePamphletConstants {

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
		Canvas = new FacePamphletCanvas();
		add(Canvas);
	}
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		/*Checks for blank input to name field*/
		if(NameInput.getText().equals("")){
			Canvas.showMessage("Please enter Name.");			
		}		
		
		/*Conditions for Add, Delete, Lookup buttons pressed*/
		else{
			
			/*actions to be taken when Add button is pressed*/
			if (cmd.equals("Add")){
				
				/*checks for if already a profile with same name exists*/
				if(DB.containsProfile(NameInput.getText())){
					Canvas.showMessage("A profile with name "+NameInput.getText()+" already exists.");
				}
				
				/*if the name entered is not in database already then new profile is created
				 * Canvas displays the profile & application message. "Name" is the current profile */
				else{
					Name = new FacePamphletProfile(NameInput.getText());
					DB.addProfile(Name);
					Canvas.displayProfile(Name);
					if(DB.containsProfile(NameInput.getText())){
						Canvas.showMessage("New profile created.");
						//println("--> Current profile: "+Name); added for testing purpose
					}
				}
			}
			
			/*actions to be taken when Delete button is pressed*/
			else if (cmd.equals("Delete")){
				
				/*checks is the entered name to be deleted exists in profile*/
				if(!DB.containsProfile(NameInput.getText())){
					Canvas.showMessage("Profile with name "+ NameInput.getText()+" does not exist.");
				}
				
				/*Deletes the profile is exists in database & displays application message accordingly
				 * Current profile is "Name" which is null after the deletion*/
				else{
					DB.deleteProfile(NameInput.getText());
					if(!DB.containsProfile(NameInput.getText())){
						Canvas.showMessage("Profile of "+NameInput.getText()+" deleted.");
						Name=null;
					}				
				}
			}
			
			/*actions to be taken when Lookup button is pressed*/
			else if (cmd.equals("Lookup")){
				
				/*displays the profile if it exists in database & displays application message
				 * the looked up profile becomes the current profile "Name"*/
				if(DB.containsProfile(NameInput.getText())){
					Name = DB.getProfile(NameInput.getText());
					Canvas.displayProfile(Name);
					Canvas.showMessage("Displaying "+Name.getName());
				}
				else {
					Canvas.showMessage("Profile with name "+NameInput.getText()+" does not exist.");
					Name=null;
				}
			}
			
			
			/*checks if Change Status button pressed & carries out the operation on the current profile
			 * here the current profile is Name*/
			if (cmd.equals("Change Status")) {
				
				/*before changing status to make sure that current profile exists to which 
				 * status change has to be done for*/
				if(Name!=null) ChangeStatus(Name);
				else Canvas.showMessage("Please select a profile to change status.");
			}
			
			/*checks if Change Picture button pressed & carries out the operation on the current profile
			 * here the current profile is Name*/
			else if (cmd.equals("Change Picture")){
				
				/*before changing picture to make sure that current profile exists to which 
				 * status change has to be done for*/
				if(Name!=null)ChangePicture(Name);
				else Canvas.showMessage("Please select a profile to change picture.");
			}
			
			/*checks if Add Friend button pressed & carries out the operation on the current profile
			 * here the current profile is "Name", "Mutual" is added to Name's profile 
			 * & "Mutual" is the name of the friend to whose profile "Name" should be added */
			else if (cmd.equals("Add Friend")){
				if(Name!=null){
					String FriendName = FriendText.getText();
					if(FriendName.equals(Name.getName())) Canvas.showMessage("Enter friend name other than own name.");
					
					/*Only is the name of the friend exists in database will it be added to friend list*/
					else if(DB.containsProfile(FriendName)){
						if(Name.addFriend(FriendName)){
							Mutual = DB.getProfile(FriendName);
							Mutual.addFriend(Name.getName());
							Canvas.displayProfile(Name);
							Canvas.showMessage(FriendText.getText()+" added as a friend");
							println("--> Current profile: "+Name);
						}
						else Canvas.showMessage(Name.getName()+" already has "+FriendName+" as a friend.");
					}
					else Canvas.showMessage(FriendName+" does not exist.");					
				}
				else Canvas.showMessage("Please select a profile to add friend to");
			}
		}		
	}
    
    
    /*all the same actions to be performed as above upon pressing enter in Change status, Change picture
     * Add Friend fields*/
    public void keyPressed(KeyEvent e){
    	if(e.getKeyCode()==KeyEvent.VK_ENTER && e.getSource()==StatusText){
    		if(Name!=null) ChangeStatus(Name);
			else Canvas.showMessage("Please select a profile to change status.");
    	}
    	else if(e.getKeyCode()==KeyEvent.VK_ENTER && e.getSource()==PictureText){
    		if(Name!=null)ChangePicture(Name);
			else Canvas.showMessage("Please select a profile to change picture.");
    	}
    	else if(e.getKeyCode()==KeyEvent.VK_ENTER && e.getSource()==FriendText){
    		if(Name!=null){
				String FriendName = FriendText.getText();
				if(FriendName.equals(Name.getName())) Canvas.showMessage("Enter friend name other than own name.");
				else if(DB.containsProfile(FriendName)){
					if(Name.addFriend(FriendName)){
						Mutual = DB.getProfile(FriendName);
						Mutual.addFriend(Name.getName());
						Canvas.showMessage(FriendText.getText()+" added as a friend");
						println("--> Current profile: "+Name);
					}
					else Canvas.showMessage(Name.getName()+" already has "+FriendName+" as a friend.");
				}
				else Canvas.showMessage(FriendName+" profile does not exist.");					
			}
			else Canvas.showMessage("Please select a profile to add friend to");
    	}
    }
    
   
    /**
     * Changes the status for the current profile, Displays the profile & application message
     * @param FPP is current profile to which status change has to be made
     */    
    private void ChangeStatus(FacePamphletProfile FPP){
    		FPP.setStatus(StatusText.getText());
    		Canvas.displayProfile(FPP);
    		Canvas.showMessage("Status updated to "+FPP.getStatus());
        	println("--> Current profile: "+FPP);
    }
    
    
    /**
     * Changes the picture for the current profile & displays the profile & application message
     * @param FPP is current profile to which picture change has to be made
     */
    private void ChangePicture(FacePamphletProfile FPP){
    		GImage image = null;
    		String filename = PictureText.getText();
    		try{
    			  image = new GImage(filename); 	
    			  FPP.setImage(image);
    			  Canvas.displayProfile(FPP);
    			  Canvas.showMessage("Picture updated.");
    			  println("--> Current Profile: "+FPP);
    		} catch (ErrorException e){
    			Canvas.showMessage("Image does not exist.");
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
    private FacePamphletCanvas Canvas;

}
