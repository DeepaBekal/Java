/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	
	public void run(){
		while(facingEast()){
			fillColumnUpward();
			changeAvenue();
			fillColumnDownward();
			changeAvenue();
		}		
	}	
	
	private void fillColumnUpward(){
		turnLeft();
		while (frontIsClear()){			
			if(noBeepersPresent()){
				putBeeper();
			}
			move();			
		}
	}
	
	private void fillColumnDownward(){
		turnRight();
		while (frontIsClear()){			
			if(noBeepersPresent()){
				putBeeper();
			}
			move();			
		}
	}
	
	private void changeAvenue(){
	if (facingNorth())	{
		turnRight();
		if (frontIsClear()){
			move();
			move();
			move();
			move();}
		else{
			turnAround();	
		}
	}
	else{
		turnLeft();
		if (frontIsClear()){
			move();
			move();
			move();
			move();}
		else{
			turnAround();
		}
	}
	}
}
