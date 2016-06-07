/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	
	public void run(){
		while(facingEast()){
		toPath();
		pickBeeper();
		fromPath();
		/*this is added to reset Karel to starting position*/
		toPath();				
		putBeeper();
		fromPath();
		}		
	}
	
	/*to guide Karel to move to position to pick newspaper
	 * Pre Condition: Karel in North-West corner of the house
	 * Post Condition : At position to pick up newspaper*/
	private void toPath(){
		while (frontIsClear()){
			move();
		}
		turnRight();
		while(leftIsBlocked()){
			move();
			turnLeft();
		}
		move();}
	
	/*to guide Karel to move back to position where it started*/
	private void fromPath(){
			turnAround();
			move();
			turnRight();
			while(frontIsClear()){
				move();
			}
			turnLeft();
			while(frontIsClear()){
				move();
			}
			turnAround();
			}
	}
