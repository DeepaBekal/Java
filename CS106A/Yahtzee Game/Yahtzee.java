/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.ArrayList;

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
		ScoreBoard = new int[nPlayers][N_CATEGORIES];			//to keep track of scores of all players 
		SelectedCategory=new int[nPlayers][N_CATEGORIES];		//to keep track of selected scoring categories
		
		/*for N_SCORING_CATEGORIES=13 rounds of the game*/
		for(int j=0;j<N_SCORING_CATEGORIES;j++){
			
			/*for nPlayers= no. of players in the game*/
			for(int i=0;i<nPlayers;i++){
				display.printMessage(playerNames[i]+"'s turn! Click \"Roll Dice\" button to roll the dice.");
				display.waitForPlayerToClickRoll(i);		//waits for player to click roll dice
				Dice = FirstRoll(Dice);						//generates random numbers(1-6) on dice
				display.displayDice(Dice);					//displays the rolled dice
				
				
				/*repeats re-roll twice only for selected dice for re-roll*/
				for(int t=0;t<2;t++){
					display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
					display.waitForPlayerToSelectDice();	//waits for player to select dice for re-roll
					Dice = ReRoll(Dice);
					display.displayDice(Dice);
				}
				
				
				display.printMessage("Select a category for this roll.");
				int Category= display.waitForPlayerToSelectCategory();		//waits for player to click on score board to select a category
				//println(Category);											//for testing purpose	
				int YahtzeeCounter;											//to track multiple Yahtzee scoring
				
				
				/*if at all player selects the same category for scoring the loop repeats until
				 * a fresh scoring category is selected */
				while(true){
					SelectedCategory[i][Category]=SelectedCategory[i][Category]+1;		//to increment each category counter when selected
					
					
					//if(YahtzeeMagicStub.checkCategory(Dice, Category)){				//for debugging
					/*Validates the selected category*/
					if(ValidateCategory(Dice, Category)){
						
						/*execute score calculation & update only if the category was not previously 
						 * selected, except in case of YAHTZEE & then breaks from loop*/
						if(SelectedCategory[i][Category]==1||Category==YAHTZEE){
							YahtzeeCounter=SelectedCategory[i][Category];					//for bonus score score calculation counter
							int Score = CalculateScore(Dice, Category, YahtzeeCounter);		//calculates score
							display.updateScorecard(Category, i, Score);					//displays calculated score on board
							ScoreBoard[i][Category]=Score;									//updates score in score board array
							break;
						}
						
						/*if the same category is selected more than once*/
						else if(SelectedCategory[i][Category]>1){
							display.printMessage("You have already selected this category, please select another category.");
							Category= display.waitForPlayerToSelectCategory();
						}
					}
					
					
					/*if the validation for category fails then score is 0 & breaks from loop*/
					else {
						
						/*not a valid category & category is selected 1st time*/
						if(SelectedCategory[i][Category]==1){
							display.updateScorecard(Category, i, 0);
							ScoreBoard[i][Category]=0;
							break;
						}
						
						/*not a valid category & category is already selected*/
						else if(SelectedCategory[i][Category]>1){
							display.printMessage("You have already selected this category, please select another category.");
							Category= display.waitForPlayerToSelectCategory();
						}
					}
				}	//end of while loop
				
				int upper = CalculateScoreUL(ScoreBoard[i],0,6);		//calculates upper score
				display.updateScorecard(6,i, upper);					//updates in score board
				ScoreBoard[i][6]=upper;									//updates score board array
				int bonus =0;	
				
				
				/*bonus=35 of upper score>63*/
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
				
				
				int lower = CalculateScoreUL(ScoreBoard[i],8,15);			//calculates lower score
				display.updateScorecard(15,i,lower);
				ScoreBoard[i][15]=lower;
				
				int total=upper+bonus+lower;				//total score calculation
				println(total);
				display.updateScorecard(16,i,total);
				ScoreBoard[i][16]=total;
			}	// end of every turn for players
			
		}	// end of all 13 rounds of game
		
		/*for testing*/
		/*for(int u=0;u<ScoreBoard.length;u++){					
			for(int v=0;v<ScoreBoard[u].length;v++){
				print(ScoreBoard[u][v]+" ");
			}
			print('\n');
		}*/
		
		HighestScorer(ScoreBoard,TOTAL);			//checks for highest scorer & prints		
	}	//end of PlayGame
		
	
	/**Method to roll the dice & to generate random numbers(1-6) on the dices
	 * Pre-condition : Player clicks on roll dice. Takes in DICE array & returns the same array 
	 * with the roll combination.
	 */
	private int[] FirstRoll(int[]a) {
		for(int i=0;i<a.length;i++){
			a[i] =rgen.nextInt(1,6);
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
	
	
	/**Method to validate if the selected category by the player is valid. Returns always true 
	 * for ONES, TWOS, THREES, FOURS, FIVES, SIXES, CHANCE, since these cases does not have
	 * combination restrictions as in other cases & present of these numbers & score is taken 
	 * care in method CalculateScore. Takes in rolled dice data, category selected by player.
	 * Evaluates & returns boolean true/false.
	 * Pre-condition : Final combination of dice rolled is available for 3 turns & player 
	 * has selected the category for scoring 
	 */	
	private boolean ValidateCategory(int[]a, int b){
		boolean result = false;							//result to be returned is stored in this
		
		/*Checks for presence of digits in dice array & sets flag respectively in d array
		 * created for each digit from 1 to 6*/
		SetFlag(a);
		
		/*returns always true for these categories*/
		if (b==ONES||b==TWOS||b==THREES||b==FOURS||b==FIVES||b==SIXES||b==CHANCE){
			result = true;
		}
		
		else if(b==THREE_OF_A_KIND){
				if(Ones.size()>=3||Twos.size()>=3||Threes.size()>=3||Fours.size()>=3||Fives.size()>=3||Sixes.size()>=3){
					result = true;
					println("three of a kind");
				}
		}
		
		else if(b==FOUR_OF_A_KIND){
			if(Ones.size()>=4||Twos.size()>=4||Threes.size()>=4||Fours.size()>=4||Fives.size()>=4||Sixes.size()>=4){
				result = true;
				println("four of a kind");
			}
		}
		
		else if(b==YAHTZEE){
			if(Ones.size()>=5||Twos.size()>=5||Threes.size()>=5||Fours.size()>=5||Fives.size()>=5||Sixes.size()>=5){
				result = true;
				println("yahtzee");
			}
		}
		
		else if(b==FULL_HOUSE){
			if(Ones.size()==3||Twos.size()==3||Threes.size()==3||Fours.size()==3||Fives.size()==3||Sixes.size()==3){
				if((Ones.size()==2||Twos.size()==2||Threes.size()==2||Fours.size()==2||Fives.size()==2||Sixes.size()==2)){
					result=true;
					println("full house");
				}
			}
		}
		
		else if(b==LARGE_STRAIGHT){
			if(Ones.size()==1&&Twos.size()==1&&Threes.size()==1&&Fours.size()==1&&Fives.size()==1){
				result = true;
				println("large straight");
			}
			else if(Twos.size()==1&&Threes.size()==1&&Fours.size()==1&&Fives.size()==1&&Sixes.size()==1){
			result = true;
			println("large straight");
			}
		}		
		
		else if(b==SMALL_STRAIGHT){
			if(Ones.size()>=1&&Twos.size()>=1&&Threes.size()>=1&&Fours.size()>=1){
				result = true;
				println("small straight");	
			}
			else if(Twos.size()>=1&&Threes.size()>=1&&Fours.size()>=1&&Fives.size()>=1){
				result = true;
				println("small straight");	
			}
			else if(Threes.size()>=1&&Fours.size()>=1&&Fives.size()>=1&&Sixes.size()>=1){
			result = true;
			println("small straight");
			}
		}
		/*println("combi ones "+Ones.size()+" twos"+Twos.size()+" threes"+Threes.size()
		+" fours"+Fours.size()+" fives"+Fives.size()+" sixes"+Sixes.size());*/ //for testing
		return result;
	}
	

	/**Method increases the array length by adding 1 to the array for each number found
	 * respectively as rolled in the dice at the end of 3 turns. Takes in the dice rolled 
	 * array as arguments.
	 * Pre-condition : Player has selected category to scoring & ValidateCategory method is 
	 * called.
	 */
	private void SetFlag(int[]a){
		Ones = new ArrayList<Integer>();
		Twos = new ArrayList<Integer>();
		Threes = new ArrayList<Integer>();
		Fours = new ArrayList<Integer>();
		Fives = new ArrayList<Integer>();
		Sixes = new ArrayList<Integer>();	
		for(int i=0;i<a.length;i++){
			if(a[i]==1) Ones.add(1);
			else if (a[i]==2) Twos.add(1);
			else if (a[i]==3) Threes.add(1);
			else if (a[i]==4) Fours.add(1);
			else if (a[i]==5) Fives.add(1);
			else if (a[i]==6) Sixes.add(1);
		}
	}
	
	
	/**method to calculate score for the selected category
	 * Pre-condition : scoring category is selected by the player. Takes in arguments of the 
	 * rolled DICE combination & Category choice of player. Returns the calculated score.
	 */
	private int CalculateScore(int[]c,int d, int e){
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
		case 13 : 									//+100 for Yahtzee>1
			temp = 50;
			for(int p=0;p<(e-1);p++){
				if (e>=2) temp= temp +100;				
			}
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
	private ArrayList<Integer> Ones;		//Array to keep track of number of 1's in dice
	private ArrayList<Integer> Twos;		//Array to keep track of number of 2's in dice
	private ArrayList<Integer> Threes;		//Array to keep track of number of 3's in dice
	private ArrayList<Integer> Fours;		//Array to keep track of number of 4's in dice
	private ArrayList<Integer> Fives;		//Array to keep track of number of 5's in dice
	private ArrayList<Integer> Sixes;		//Array to keep track of number of 6's in dice
	
	
}
