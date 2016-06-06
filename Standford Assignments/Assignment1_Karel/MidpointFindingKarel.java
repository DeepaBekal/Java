/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
	public void run(){
		Integer i=0, j=0;
		while(frontIsClear()){
			move();
			i++;
		}		
		
		if (i%2==0){
			turnAround();
			i=i/2;
			i=i-1;
			for(j=0;j<=i;j++){
				move();
			}
			putBeeper();
			turnAround();
		}
		else{
			turnAround();
			i=i+1;
			i=i/2;
			i=i-1;
			for(j=0;j<=i;j++){
				move();
			}
			putBeeper();
			turnAround();
		}	
	}
}
