/*
 * File: Artistry.java
 * Name: Deepa
 * You fill this in.  Remember that you must have
 * 1. At most twenty GObjects,
 * 2. At least one filled object,
 * 3. At least two different colors of objects, and
 * 4. At least three different types of objects (not
 * counting the GLabel you use to sign your name). 
 * Also, be sure to sign your name in the bottom-right
 * corner of the canvas.
 */
import acm.program.*;

import java.awt.Color;

import acm.graphics.*;


public class Artistry extends GraphicsProgram {
	public void run() {
		
	/*draw a square (inner) of 100x100 at 250,160(x,y)*/
	GRect sq = new GRect(250, 160, 100, 100);
	add(sq);
	
	/*draw a hexagon (outer) like the shape of Karel starting at (225, 125) coordinates*/
	GPolygon pgn = new GPolygon();
	pgn.addVertex(225,125);
	pgn.addVertex(350,125);
	pgn.addVertex(385,160);
	pgn.addVertex(385,320);
	pgn.addVertex(250, 320);
	pgn.addVertex(225, 285);
	pgn.addVertex(225,125);
	add(pgn);
	
	/*draw a line representing mouth*/
	GLine ln = new GLine(310, 285, 360, 285);
	add(ln);
	
	/*draw right leg vertical part & color pink*/
	GRect RLeg1 = new GRect(290, 320, 10, 40);
	RLeg1.setColor(Color.PINK);
	RLeg1.setFilled(true);
	add(RLeg1);
	
	/*draw right leg horizontal part & color pink*/
	GRect RLeg2 = new GRect(290, 350, 40, 10);
	RLeg2.setColor(Color.PINK);
	RLeg2.setFilled(true);
	add(RLeg2);
	
	/*draw left leg horizontal part & color pink*/
	GRect LLeg1 = new GRect(190, 250, 35, 10);
	LLeg1.setColor(Color.PINK);
	LLeg1.setFilled(true);
	add(LLeg1);
	
	/*draw left leg vertical part & color pink*/
	GRect LLeg2 = new GRect(190, 250, 10, 40);
	LLeg2.setColor(Color.PINK);
	LLeg2.setFilled(true);
	add(LLeg2);
	
	/*draw eye line .i.e., horizontal line*/
	GRect eyeLine = new GRect(190, 210,185, 3);
	add(eyeLine);
	eyeLine.setFilled(true);
	eyeLine.setColor(Color.BLACK);
	
	/*draw solid arc with black which is the right eye*/
	GArc REye = new GArc(325, 195, 50, 30, 180, 180);
	add(REye);
	REye.setFilled(true);
	REye.setFillColor(Color.BLACK);
	GArc LEye = new GArc(275, 195, 50, 30, 180, 180);
	add(LEye);
	
	/*draw solid arc with black which is the right eye*/
	LEye.setFilled(true);
	LEye.setFillColor(Color.BLACK);
	
	/*fill in signature at bottom right corner*/
	GLabel sign = new GLabel("Art by Deepa Bekal", 645, 470);
	add(sign);
	
	/*some fun message*/
	GLabel timepass = new GLabel("Finally Karel Arrived ", 400, 300);
	timepass.setFont("SansSerif-20");
	timepass.setColor(Color.MAGENTA);
	add(timepass);

	}
}
