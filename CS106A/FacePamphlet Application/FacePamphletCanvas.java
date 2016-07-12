/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		GLabel displayMessage = new GLabel(msg);
		double x = getWidth()/3;
		double y = getHeight()-BOTTOM_MESSAGE_MARGIN;
		displayMessage.setFont(MESSAGE_FONT);
		if(getElementAt(x,y)!=null) remove(getElementAt(x,y));
		add(displayMessage,x,y);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		DisplayName(profile);
		DisplayImage(profile);
		DisplayStatus(profile);
		DisplayFriendList(profile);
	}
	
	private void DisplayName(FacePamphletProfile profile){
		GLabel name = new GLabel(profile.getName(),LEFT_MARGIN,TOP_MARGIN*2);
		name.setFont(PROFILE_NAME_FONT);
		name.setColor(Color.BLUE);
		add(name);		
	}
	
	private void DisplayImage(FacePamphletProfile profile){
		GRect ImageRect = new GRect(LEFT_MARGIN,TOP_MARGIN*3,IMAGE_WIDTH, IMAGE_HEIGHT);
		GLabel text = new GLabel("No Image",LEFT_MARGIN*3.5,(TOP_MARGIN*3)+(IMAGE_HEIGHT/2));
		text.setFont(PROFILE_IMAGE_FONT);
		if(profile.getImage()==null){
			add(ImageRect);		
			add(text);
		}
		else{
			GImage image = profile.getImage();
			image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image,LEFT_MARGIN,TOP_MARGIN*3);
		}
	}
	
	private void DisplayStatus(FacePamphletProfile profile){
		String status = profile.getStatus(); 		
		if(!status.isEmpty()){			
			status = profile.getName() + " is " + profile.getStatus();			
		}
		else {
			status = "No current status";
		}
		GLabel Gstatus = new GLabel(status,LEFT_MARGIN,(TOP_MARGIN*5)+IMAGE_HEIGHT);
		Gstatus.setFont(PROFILE_STATUS_FONT);
		add(Gstatus);		
	}

	private void DisplayFriendList(FacePamphletProfile profile){
		double x, y;
		x= getWidth()/2;
		y=TOP_MARGIN*3;
		GLabel title = new GLabel ("Friends: ",x,y);
		title.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(title);
		Iterator <String> iter= profile.getFriends();
		for(int i=1;iter.hasNext();i++){
			GLabel FriendList = new GLabel(iter.next());
			FriendList.setFont(PROFILE_FRIEND_FONT);
			double index = y+title.getHeight()*i;
			add(FriendList,x,index);
		}		
	}	
}
