/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	private static final int DELAY = 20;
	
	public void run() {
		/* Set up game & play game for 3(N_TURNS) chances unless in any of the chances all bricks
		 * are cleared by the player*/
	for(int i=0;i<NTURNS;i++){
		SetUpGame();					//resets the game board 
		PlayGame();
		if(BrickCounter==0) {
			Ball.setVisible(false);
			printWinner();				//player wins game if in any chance all bricks are cleared
			break;
		}
		if(BrickCounter>0) removeAll();	// prepares game board for reset for next chance
		}
	if(BrickCounter>0) printGameOver();	//after finishing 3 chances if all bricks are not cleared then game is over & player looses
		
	}
	
	/*sets up 10 rows of bricks of different colors. Creates the paddle which moves along x-axis
	 * while y-axis is constant. Creates ball which bounces off paddle upon player's action & 
	 * bounces of walls & upon hitting brick clears brick */
	private void SetUpGame(){
		SetUpBrick();
		BrickCounter = NBRICKS_PER_ROW * NBRICK_ROWS;
		CreatePaddle();
		CreateBall();
	}

	/*sets up 10 rows of bricks of different colors.*/
	private GRect Brick;
	private void SetUpBrick(){		
		for (int i = 0; i<NBRICK_ROWS;i++ ){			//to loop through rows to put brick
			int StartY = BRICK_Y_OFFSET + i*BRICK_HEIGHT+i*BRICK_SEP;
			for(int j=0; j<NBRICKS_PER_ROW;j++){		//to loop through columns of a row to put brick
				int StartX = j*BRICK_SEP+j*BRICK_WIDTH;
				Brick = new GRect (StartX, StartY,BRICK_WIDTH, BRICK_HEIGHT );
				Brick.setFilled(true);
				add(Brick);		
				if(i==0||i==1){
					Brick.setColor(Color.RED);				
				}
				else if(i==2||i==3){
					Brick.setColor(Color.ORANGE);	
				}
				else if(i==4||i==5){
					Brick.setColor(Color.YELLOW);	
				}
				else if(i==6||i==7){
					Brick.setColor(Color.GREEN);
				}
				else if(i==8||i==9){
					Brick.setColor(Color.CYAN);
				}					
			}			
		}		
	}
	
	/*Creates the paddle which moves along x-axis while y-axis is constant.*/
	private GRect Paddle;
	private void CreatePaddle(){
		double x= (getWidth()/2)-(PADDLE_WIDTH/2);
		double y = getHeight()-PADDLE_Y_OFFSET- PADDLE_HEIGHT ;
		Paddle = new GRect(x,y,PADDLE_WIDTH, PADDLE_HEIGHT);	
		Paddle.setFilled(true);
		Paddle.setColor(Color.BLACK);
		add(Paddle);
		addMouseListeners();	//to track the movement of mouse w.r.t. paddle	
	}
	
	/*tracks the movement of the mouse moved position, 
	 * eventually to sync moved moved = paddle position on game board*/
	public void mouseMoved(MouseEvent e){
		/*to check the x-axis boundary condition, so that paddle does not go out of visibility
		 * of the size of game board on both sides of x-axis & then set the paddle location
		 * w.r.t. mouse moved position*/
		if((e.getX() < getWidth()- PADDLE_WIDTH/2) && (e.getX() > PADDLE_WIDTH/2)){		
			Paddle.setLocation(e.getX()- PADDLE_WIDTH/2, getHeight()-PADDLE_Y_OFFSET- PADDLE_HEIGHT);
			}		
	}
	
	/*creates & adds the ball to canvas*/
	private GOval Ball;
	private void CreateBall(){
		Ball = new GOval((getWidth()/2)-BALL_RADIUS,(getHeight()/2)-BALL_RADIUS,BALL_RADIUS*2,BALL_RADIUS*2);
		Ball.setFilled(true);
		Ball.setFillColor(Color.BLACK);
		add(Ball);	
	}
	
	/*waits for player action of a click to start the game for the player & with the calculated 
	 * velocity of the ball to be moved around bouncing off paddle,wall, brick. Starting 
	 * angle/direction/velocity of the ball dropping down generated by random generator*/
	
	private void PlayGame(){
		waitForClick();
		getVelocity();								
		while(true){
			moveBall();								//method to move ball & bounce off paddle, wall, brick
			if(Ball.getY()>=getHeight()) break;		//exit loop if ball goes beyond the length of the board
			if(BrickCounter==0) break;				//exit the loop if all bricks are cleared & player wins
		}
	}
	
	/*the delta value along with direction to be moved w.r.t. x & y axis is calculated & set*/
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private double vx, vy;
	private void getVelocity(){
        vy = 3.0;
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) vx = -vx; 
        }
	
	/*method to move ball & bounce off paddle, wall, brick*/
	private int BrickCounter;
	private void moveBall(){
		Ball.move(vx, vy);
		/*to reverse the direction of vx, if it will cross the game board boundary 
		 *  on either left or right side before the next call to moveball() method*/
        if((Ball.getX()-vx <= 0 && vx < 0 )|| (Ball.getX() + vx >= (getWidth() - BALL_RADIUS*2) && vx>0)) {
            vx = -vx;
        }
        /*We don't need to check for the bottom wall, since the ball can fall 
         * through the wall at that point. To reverse the direction of vy, 
         * if it will cross the game board boundary at top side before the next 
         * call to moveball() method*/         
        if ((Ball.getY() - vy <= 0 && vy < 0 )) {
            vy = -vy;
        }
        
        /*if the ball collides with paddle to reverse the direction of vy*/
        GObject collider = getCollidingObject();
        /*to check the ball is at the surface of paddle & not beyond paddle to reverse the 
         * vy direction */
        if (collider == Paddle) {
            if(Ball.getY() >= getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS*2 && Ball.getY() < getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS*2 + 3) {
                vy = -vy;    
            }
        }
        
        /*condition cancels out the collision with wall to ensure to remove brick with which
         * it collided & decrement brick counter to keep track of all bricks cleared or not to 
         * declare game lose or win. & to reverse vy after collision*/
        else if (collider != null) {
                remove(collider); 
                BrickCounter--;
                vy = -vy;
            }
            pause (DELAY);				//for transition of ball position  to be visible to player
        } //end of moveBall method
	
        /*to return the object with which ball collided, this method checks for collision
         * on all corners of the ball*/
        private GObject getCollidingObject() {
            if((getElementAt(Ball.getX(), Ball.getY())) != null) {
                return getElementAt(Ball.getX(), Ball.getY());
             }
           else if (getElementAt( (Ball.getX() + BALL_RADIUS*2), Ball.getY()) != null ){
                return getElementAt(Ball.getX() + BALL_RADIUS*2, Ball.getY());
             }
           else if(getElementAt(Ball.getX(), (Ball.getY() + BALL_RADIUS*2)) != null ){
                return getElementAt(Ball.getX(), Ball.getY() + BALL_RADIUS*2);
             }
           else if(getElementAt((Ball.getX() + BALL_RADIUS*2), (Ball.getY() + BALL_RADIUS*2)) != null ){
                return getElementAt(Ball.getX() + BALL_RADIUS*2, Ball.getY() + BALL_RADIUS*2);
             }
           //need to return null if there are no objects present
           else{
                return null;
             }

        }
        
        /*method to print game over (in center)on event of loosing on all 3 chances*/
        private void printGameOver() {
            GLabel gameOver = new GLabel ("Game Over", WIDTH/2, HEIGHT/2);
            gameOver.move(-gameOver.getWidth()/2, -gameOver.getHeight());
            gameOver.setColor(Color.RED);
            add (gameOver);
        }        
     
        /*method to print game winner (in center) upon player removing all bricks
         *from game board  */
        private void printWinner() {
            GLabel Winner = new GLabel ("Winner!!", WIDTH/2, HEIGHT/2);
            Winner.move(-Winner.getWidth()/2, -Winner.getHeight());
            Winner.setColor(Color.RED);
            add (Winner);
        }		

	/* Method: init() */
	/** Sets up the Breakout program. */
	public void init() {
		/* You fill this in, along with any subsidiary methods */
	}

	/* Method: run() */
	/** Runs the Breakout program. */

}
