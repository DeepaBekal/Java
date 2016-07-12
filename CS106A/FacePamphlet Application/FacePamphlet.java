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
		if(NameInput.getText().equals("")){
			Canvas.showMessage("Please enter Name.");			
		}
		else{
			if (cmd.equals("Add")){
				if(DB.containsProfile(NameInput.getText())){
					Canvas.showMessage("A profile with name "+NameInput.getText()+" already exists.");
				}
				else{
					Name = new FacePamphletProfile(NameInput.getText());
					DB.addProfile(Name);
					Canvas.displayProfile(Name);
					if(DB.containsProfile(NameInput.getText())){
						Canvas.showMessage("New profile created.");
						println("--> Current profile: "+Name);
					}
				}
			}
			else if (cmd.equals("Delete")){
				if(!DB.containsProfile(NameInput.getText())){
					Canvas.showMessage("Profile with name "+ NameInput.getText()+" does not exist.");
				}
				else{
					DB.deleteProfile(NameInput.getText());
					if(!DB.containsProfile(NameInput.getText())){
						Canvas.showMessage("Profile of "+NameInput.getText()+" deleted.");
						Name=null;
					}				
				}
			}
			else if (cmd.equals("Lookup")){
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
			
			
			
			if (cmd.equals("Change Status")) {
				if(Name!=null) ChangeStatus(Name);
				else Canvas.showMessage("Please select a profile to change status.");
				//println("Change Status : "+StatusText.getText());
			}
			else if (cmd.equals("Change Picture")){
				if(Name!=null)ChangePicture(Name);
				else Canvas.showMessage("Please select a profile to change picture.");
				//println("Change Picture : "+PictureText.getText());
			}
			else if (cmd.equals("Add Friend")){
				if(Name!=null){
					String FriendName = FriendText.getText();
					if(FriendName.equals(Name.getName())) Canvas.showMessage("Enter friend name other than own name.");
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
				
				//println("Add Friend : "+FriendText.getText());	
			}
		}		
	}
    
    public void keyPressed(KeyEvent e){
    	if(e.getKeyCode()==KeyEvent.VK_ENTER && e.getSource()==StatusText){
    		if(Name!=null) ChangeStatus(Name);
			else Canvas.showMessage("Please select a profile to change status.");
    		//println("Change Status : "+StatusText.getText());
    	}
    	else if(e.getKeyCode()==KeyEvent.VK_ENTER && e.getSource()==PictureText){
    		if(Name!=null)ChangePicture(Name);
			else Canvas.showMessage("Please select a profile to change picture.");
    		//println("Change Picture : "+PictureText.getText());
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
    		//println("Add Friend : "+FriendText.getText());
    	}
    }
    
    private void ChangeStatus(FacePamphletProfile FPP){
    		FPP.setStatus(StatusText.getText());
    		Canvas.displayProfile(FPP);
    		Canvas.showMessage("Status updated to "+FPP.getStatus());
        	println("--> Current profile: "+FPP);
    }
    
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
