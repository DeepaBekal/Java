/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	
	public void run(){
		while(facingEast()){
			LtoR();
			while(facingNorth()&&frontIsClear()){
				if(beepersPresent()){						
					move();
					turnLeft();
					RtoLOdd();
					turnRight();
					move();
					turnRight();
				}
				else{
					move();
					turnLeft();
					RtoLEven();
					if(frontIsClear()){
					move();
					turnRight();
					}
				}	
			}
		}
	}
	
	private void LtoR(){
		while(facingEast()){
			putBeeper();
			if(frontIsClear()){
				move();
				if(frontIsClear()){
					move();
				}
				else{
					turnLeft();
				}
			}
			else{
				turnLeft();
				if(rightIsBlocked()&&leftIsBlocked()){
					while(facingNorth()&&frontIsClear()){
						move();
						if(frontIsClear()){
							move();
						}
						else{
							turnRight();
						}
						if(frontIsClear()){
							putBeeper();
						}
						else{
							turnRight();
						}
					}
				}
			}
		}
	}
	
	private void RtoLOdd(){
		while(facingWest()&&frontIsClear()){
				move();
				if(frontIsClear()){
					putBeeper();
					if(frontIsClear()){
						move();
					}
					else{
						turnRight();
					}
				}
				else{
				turnRight();								
				}
		}	
	}
	private void RtoLEven(){
		while(facingWest()&&frontIsClear()){
				putBeeper();
				if(frontIsClear()){
					move();
					if(frontIsClear()){
						move();
					}
					else{
						turnRight();
					}
				}
				else{
					turnRight();
				}				
		}
	}
}
