/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */


import java.awt.Color;
import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		GLine ScaffoldHeight = new GLine(getWidth()/4,28, getWidth()/4,28+SCAFFOLD_HEIGHT );
		add(ScaffoldHeight);
		GLine BeamLength = new GLine(getWidth()/4,28,getWidth()/4+BEAM_LENGTH,28);
		add(BeamLength);
		GLine RopeLength = new GLine(getWidth()/4+BEAM_LENGTH,28, getWidth()/4+BEAM_LENGTH,28+ROPE_LENGTH);
		add(RopeLength);
	}	


/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		GLabel gWord = new GLabel(word,(getWidth()/4)-10, 420 );
		gWord.setColor(Color.BLUE);
		gWord.setFont("Calibri-24");
		if(getElementAt((getWidth()/4)-10, 420)!=null){
			remove(getElementAt((getWidth()/4)-10, 420));
		}
		add(gWord);		
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(String letter, int guesscounter) {
		GLabel IncorrectGuess = new GLabel (letter,(getWidth()/4)-10, 450 );
		IncorrectGuess.setColor(Color.RED);
		IncorrectGuess.setFont("Calibri-20");
		add(IncorrectGuess);
		switch(guesscounter){
		case 0 : RightFoot();
		case 1 : LeftFoot();
		case 2 : RightLeg();
		case 3 : LeftLeg();
		case 4 : RightArm();
		case 5 : LeftArm();
		case 6 : Body();
		case 7 : Head();		
		}		
	}
	
	
	private void Head(){
		GOval head = new GOval (getWidth()/4+BEAM_LENGTH-HEAD_RADIUS,28+ROPE_LENGTH, HEAD_RADIUS*2, HEAD_RADIUS*2);
		add(head);
	}
	
	private void Body(){
		GLine body = new GLine(getWidth()/4+BEAM_LENGTH,(28+ROPE_LENGTH)+(HEAD_RADIUS*2),getWidth()/4+BEAM_LENGTH, (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+BODY_LENGTH);
		add(body);	
	}
	
	private void LeftArm(){
		GLine UpperArmL = new GLine(getWidth()/4+BEAM_LENGTH-UPPER_ARM_LENGTH, (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD,getWidth()/4+BEAM_LENGTH, (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD);
		add(UpperArmL);
		GLine LowerArmL = new GLine(getWidth()/4+BEAM_LENGTH-UPPER_ARM_LENGTH,(28+ROPE_LENGTH)+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD, getWidth()/4+BEAM_LENGTH-UPPER_ARM_LENGTH, (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
		add(LowerArmL);
	}
	
	private void RightArm(){
		GLine UpperArmR = new GLine(getWidth()/4+BEAM_LENGTH, (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD,getWidth()/4+BEAM_LENGTH+UPPER_ARM_LENGTH, (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD);
		add(UpperArmR);
		GLine LowerArmR = new GLine(getWidth()/4+BEAM_LENGTH+UPPER_ARM_LENGTH,(28+ROPE_LENGTH)+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD, getWidth()/4+BEAM_LENGTH+UPPER_ARM_LENGTH, (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
		add(LowerArmR);		
	}
	
	private void LeftLeg(){
		GLine HipHalfL = new GLine(getWidth()/4+BEAM_LENGTH,(28+ROPE_LENGTH)+(HEAD_RADIUS*2)+BODY_LENGTH, getWidth()/4+BEAM_LENGTH-(HIP_WIDTH), (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+BODY_LENGTH);
		add(HipHalfL);
		GLine LeftLegL= new GLine (getWidth()/4+BEAM_LENGTH-(HIP_WIDTH), (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+BODY_LENGTH,getWidth()/4+BEAM_LENGTH-(HIP_WIDTH), (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+BODY_LENGTH+LEG_LENGTH);
		add(LeftLegL);
	}
	
	private void RightLeg(){
		GLine HipHalfR = new GLine(getWidth()/4+BEAM_LENGTH,(28+ROPE_LENGTH)+(HEAD_RADIUS*2)+BODY_LENGTH, getWidth()/4+BEAM_LENGTH+(HIP_WIDTH), (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+BODY_LENGTH);
		add(HipHalfR);
		GLine LeftLegR= new GLine (getWidth()/4+BEAM_LENGTH+(HIP_WIDTH), (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+BODY_LENGTH,getWidth()/4+BEAM_LENGTH+(HIP_WIDTH), (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+BODY_LENGTH+LEG_LENGTH);
		add(LeftLegR);
	}
	
	private void LeftFoot(){
		GLine LeftFootL = new GLine(getWidth()/4+BEAM_LENGTH-(HIP_WIDTH), (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+BODY_LENGTH+LEG_LENGTH,getWidth()/4+BEAM_LENGTH-(HIP_WIDTH)-FOOT_LENGTH, (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+BODY_LENGTH+LEG_LENGTH);
		add(LeftFootL);
	}
	
	private void RightFoot(){
		GLine RightFootL = new GLine(getWidth()/4+BEAM_LENGTH+(HIP_WIDTH), (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+BODY_LENGTH+LEG_LENGTH,getWidth()/4+BEAM_LENGTH+(HIP_WIDTH)+FOOT_LENGTH, (28+ROPE_LENGTH)+(HEAD_RADIUS*2)+BODY_LENGTH+LEG_LENGTH);
		add(RightFootL);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
