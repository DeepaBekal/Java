/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	public void run() {
		setupPlayers();
		initDisplay();
		playGame();
	}
	
	/**
	 * Prompts the user for information about the number of players, then sets up the
	 * players array and number of players.
	 */
	private void setupPlayers() {
		nPlayers = chooseNumberOfPlayers();	
		
		/* Set up the players array by reading names for each player. */
		playerNames = new String[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			/* IODialog is a class that allows us to prompt the user for information as a
			 * series of dialog boxes.  We will use this here to read player names.
			 */
			IODialog dialog = getDialog();
			playerNames[i] = dialog.readLine("Enter name for player " + (i + 1));
		}
	}
	
	/**
	 * Prompts the user for a number of players in this game, reprompting until the user
	 * enters a valid number.
	 * 
	 * @return The number of players in this game.
	 */
	private int chooseNumberOfPlayers() {
		/* See setupPlayers() for more details on how IODialog works. */
		IODialog dialog = getDialog();		//new IODialog() can also be used
		
		while (true) {
			/* Prompt the user for a number of players. */
			int result = dialog.readInt("Enter number of players");
			
			/* If the result is valid, return it. */
			if (result > 0 && result <= MAX_PLAYERS)
				return result;
			
			dialog.println("Please enter a valid number of players.");
		}
	}
	
	/**
	 * Sets up the YahtzeeDisplay associated with this game.
	 */
	private void initDisplay() {
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
	}

	/**
	 * Actually plays a game of Yahtzee.  This is where you should begin writing your
	 * implementation.
	 */
	
	private void playGame() {
		ScoreBoard = new int[nPlayers][N_CATEGORIES];
		SelectedCategory=new int[nPlayers][N_CATEGORIES];
		for(int j=0;j<N_SCORING_CATEGORIES;j++){
		
			for(int i=0;i<nPlayers;i++){
				display.printMessage(playerNames[i]+"'s turn! Click \"Roll Dice\" button to roll the dice.");
				display.waitForPlayerToClickRoll(i);
				Dice = FirstRoll(Dice);
				display.displayDice(Dice);
				for(int t=0;t<2;t++){
					display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
					display.waitForPlayerToSelectDice();
					Dice = ReRoll(Dice);
					display.displayDice(Dice);
				}
				display.printMessage("Select a category for this roll.");
				int Category= display.waitForPlayerToSelectCategory();
				//println(Category);
				//if(ValidateCategory(Dice, Category)){
				while(true){
					SelectedCategory[i][Category]=SelectedCategory[i][Category]+1;
					if(YahtzeeMagicStub.checkCategory(Dice, Category)){
						if(SelectedCategory[i][Category]==1){
						int Score = CalculateScore(Dice, Category);
						display.updateScorecard(Category, i, Score);
						ScoreBoard[i][Category]=Score;
						break;
						}
						else if(SelectedCategory[i][Category]>1){
							display.printMessage("You have already selected this category, please select another category.");
							Category= display.waitForPlayerToSelectCategory();
						}
					}
					else {
						if(SelectedCategory[i][Category]==1){
							display.updateScorecard(Category, i, 0);
							ScoreBoard[i][Category]=0;
							break;
						}
						else if(SelectedCategory[i][Category]>1){
							display.printMessage("You have already selected this category, please select another category.");
							Category= display.waitForPlayerToSelectCategory();
						}
					}
				}
				int upper = CalculateScoreUL(ScoreBoard[i],0,6);
				display.updateScorecard(6,i, upper);
				ScoreBoard[i][6]=upper;
				int bonus =0;
				if(upper>=63) {
					bonus = 35;
					display.updateScorecard(7,i,bonus);
					ScoreBoard[i][7]=bonus;
				}
				else{
					bonus = 0;
					display.updateScorecard(7,i,bonus);					
					ScoreBoard[i][7]=bonus;
				}
				int lower = CalculateScoreUL(ScoreBoard[i],8,15);
				display.updateScorecard(15,i,lower);
				ScoreBoard[i][15]=lower;
				int total=upper+bonus+lower;
				println(total);
				display.updateScorecard(16,i,total);
				ScoreBoard[i][16]=total;
			}	// end of every turn for players
			
		}	// end of all 13 rounds of game
		
		for(int u=0;u<ScoreBoard.length;u++){
			for(int v=0;v<ScoreBoard[u].length;v++){
				print(ScoreBoard[u][v]+" ");
			}
			print('\n');
		}
		HighestScorer(ScoreBoard,TOTAL);		
	}	//end of PlayGame
		
	/**Method to roll the dice
	 * Pre-condition : player rolls the dice. Takes in DICE array & returns the same array 
	 * with the roll combination.
	 */
	private int[] FirstRoll(int[]a) {
		for(int i=0;i<a.length;i++){
			a[i] = rgen.nextInt(1,6);
		}
		return a;			
	}
	/**Method to re-roll the selected dice.
	 * Pre-condition : First roll has been played by player. Dice to be re-rolled has been 
	 * chosen.Takes in the rolled dice combination & returns the new dice combination for 
	 * selected for re-roll. 
	 */
	private int[] ReRoll(int[]b) {
		/*check for re-roll selected dice & re-rolls dice*/
		for(int i=0;i<b.length;i++){
			/*re-roll happens only if dice is selected for re-roll*/
			if(display.isDieSelected(i)){
				b[i]=rgen.nextInt(1,6);
			}
		}
		return b;			
	}
	
	/**method to calculate score for the selected category
	 * Pre-condition : scoring category is selected by the player. Takes in arguments of the 
	 * rolled DICE combination & Category choice of player. Returns the calculated score.
	 */
	private int CalculateScore(int[]c,int d){
		int temp=0;									//stores the calculated score to be returned
		/*depending on the category the selected case calculates the score*/
		switch(d){
		case 0 :
			for(int i=0;i<c.length;i++){
				if(c[i]==1) temp=temp+c[i];
			}break;
		case 1 :
			for(int i=0;i<c.length;i++){
				if(c[i]==2) temp=temp+c[i];
			} break;
		case 2 :
			for(int i=0;i<c.length;i++){
				if(c[i]==3) temp=temp+c[i];
			} break;
		case 3 :
			for(int i=0;i<c.length;i++){
				if(c[i]==4) temp=temp+c[i];
			} break;
		case 4 :
			for(int i=0;i<c.length;i++){
				if(c[i]==5) temp=temp+c[i];
			} break;
		case 5 :
			for(int i=0;i<c.length;i++){
				if(c[i]==6) temp=temp+c[i];
			} break;
		case 8 :
			for(int i=0;i<c.length;i++){
				temp=temp+c[i];
			} break;
		case 9 :
			for(int i=0;i<c.length;i++){
				temp=temp+c[i];
			} break;
		case 10 : 
			temp = 25;
			break;
		case 11 : 
			temp = 30;
			break;
		case 12 : 
			temp = 40;
			break;
		case 13 : 
			temp = 50;
			break;
		case 14 : 
			for(int i=0;i<c.length;i++){
				temp=temp+c[i];
			}break;
		}
		return temp;
	}
	
	/**method to calculate running total of upper score from 1's to 6's. & also to calculate 
	 * lower score from THREE_OF_A_KIND to CHANCE.
	 * Pre-condition : Score is updated for selected category for a round of a player. Takes in 
	 * arguments single row of ScoreBoard that is scores of a player, start index & end index 
	 * through which total is to be calculated. Returns upper/lower total score.
	 */
	private int CalculateScoreUL(int[]e, int f, int g){
		int temp1=0;								//to store total
		/*sums up the scores in the range of indices */
		for(int i=f;i<g;i++){
			temp1 = temp1+e[i];
		}
		return temp1;
	}
	
	/**method to evaluate the highest scorer from single dimension array of total of all
	 * players.
	 * Pre-condition : All rounds of the game has been players, categories are populated 
	 * with scores. Takes in ScoreBoard array & the index of row TOTAL which is 16 as arguments.
	 * */
	private void HighestScorer(int[][]a,int b){
		ScoreBoardTotal = new int[nPlayers];
		int temp3=0;								//to store the highest score
		int index=0;								//to store the index of the highest score
		/*assigns TOTAL of all players to ScoreBoardTotal & evaluates the largest */
		for(int t=0;t<nPlayers;t++){				
			ScoreBoardTotal[t]= a[t][b];
			if(ScoreBoardTotal[t]>temp3){
				temp3=ScoreBoardTotal[t];
				index=t;
			}
		}
		/*prints the highest scorer's name & score*/
		display.printMessage("Congratulations, "+playerNames[index]+", you're the winner with a total score of "+temp3+"!");
	}
	
		
	/* Private instance variables */
	
	private int nPlayers;									//no. of players
	private String[] playerNames;							//array of player names
	private YahtzeeDisplay display;							//game board graphics display
	private int[] Dice = new int[N_DICE];					//array of roll dice combination
	private RandomGenerator rgen = new RandomGenerator();	//to generate random numbers for dice
	private int[][] SelectedCategory;						//keeps track of already selected category
	private int[][] ScoreBoard; 							//array of scores of all players
	private int[] ScoreBoardTotal;							//array of total score of all players
	
	
}
