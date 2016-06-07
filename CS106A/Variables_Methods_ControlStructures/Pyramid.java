/*
 * File: Pyramid.java
 * Name: Deepa
 * Program that draws a pyramid consisting of bricks arranged in horizontal
 * rows, so that the number of bricks in each row decreases by one as you move up the pyramid
 */

import acm.graphics.*;
import acm.program.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		buildPyramid();				
	}
	
	private void buildPyramid(){
	
		/*the outer 'for' loop is for controlling the number of rows which is equal to number of BRICKS_IN_BASE*/
		for(int RowNo=0; RowNo < BRICKS_IN_BASE; RowNo++){
	
			/*Bricks in a row = one brick less than bricks in the base*/
			int BricksInRow = BRICKS_IN_BASE - RowNo;
	
			/*starting X co ordinate for each row is obtained by deducting the space taken by no. of 
			 * bricks in  * one half of canvas by the width of the half of the canvas*/
			double StartX= (getWidth()/2)-((BRICK_WIDTH*BricksInRow)/2);
	
			/*starting Y co ordinate for each row is obtained by deducting the brick height times row no.*/
			double StartY= getHeight()-BRICK_HEIGHT*(RowNo+1); 
			
			/*to fill brick in every row & no. of bricks is equal to bricks in row minus the row no.*/
			for(int count=0;count < BricksInRow; count++){
				
				/*add brick at x,y for WxH*/
				GRect brick = new GRect(StartX, StartY, BRICK_WIDTH,BRICK_HEIGHT);
				add(brick);
				
				/*the X co ordinate increases by 1 brick width for next brick*/
				StartX = StartX+BRICK_WIDTH;
			}			
		}
	}	
}

