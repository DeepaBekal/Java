/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;


public class Hangman extends ConsoleProgram {
	
	/*This is a console program so the console is already setup, to display the hangman 
	 * graphics, object of HangmanCanvas is created in the init() & added so occupies the second column. 
	 * Console program occupies the first column */
	private HangmanCanvas canvas;	
	public void init(){
		canvas = new HangmanCanvas();
		add(canvas);
	}
	
	
	/*run has methods for setting up the game & to play the game*/
	public void run() {			
   		SetUpGame();
   		PlayGame();   		
   	}	
	
	
	/*sets up the initial state of the game. Displays text in first column & second column with 
	 * scaffold & rope. Has a counter set to number of wrong guesses allowed */
	private int GuessCounter;
	private void SetUpGame(){
		println("Welcome to Hangman Game!");
   		print("The word now looks like this : " );
   		DisplayNewDash();		//displays the word to be guessed in the form of '-' for number of letters
   		GuessCounter = 8;
   		canvas.reset();   		
	}
	
	
	/*displays the word to be guessed in the form of '-' for number of letters in the word*/
	private String word ;
	private String temp1,temp2;
	private void DisplayNewDash(){
		word = NewWord();
		//print(word);							//added for ease of testing
		temp1="-";
		temp2="-";
		for (int j=0;j<=(word.length()-2);j++){
			temp1 =temp1+temp2;
		}		
		print(temp1);							//'\t'+
		canvas.displayWord(temp1);	//letters of word to be guessed to be displayed as'-' in second column
	}
	
	
	/*randomly picks a word from the set of words*/
	RandomGenerator rgen = RandomGenerator.getInstance();
	HangmanLexicon HL = new HangmanLexicon();	//object of HangmanLexicon class
	private String NewWord(){
		int n = HL.getWordCount();				//calls the method to get the no. of words in collection
		//println("total words = "+n);			//added for debug
		int i= rgen.nextInt(0, n-1);
		return HL.getWord(i);					//calls the method to get randomly chosen word
	}
	
	
	/*This keeps track of right & wrong guesses of letters in the word and 
	 * displays it accordingly. For every wrong guess a counter is decremented & correspondingly
	 * hangman's parts are drawn*/
	String IncorrectGuess="";
	private void PlayGame(){
   		while(true){
   			if((GuessCounter>1))print('\n'+"You have "+GuessCounter + " guesses left."+'\n');
   			if(GuessCounter==1) print('\n' +"You have only one guess left."+'\n'); 
   			
   			/*this method asks player to enter the guessed character & prompts if more than 1 
   			 * character is entered*/
   			GuessChar();
   			
   			/*in case if valid input of 1 character is entered by player, then this method 
   			 *checks is the chosen word has the same character. Depending on the outcome 
   			 *displays the current word filled in if guessed character is present in the word */
   			if(guessChar.length()==1)	CharPresent();
   			
   			/*displays the current word filled in if guessed character is present in the word
   			 * in the second column i.e., canvas*/
   			canvas.displayWord(temp1);
   			
   			/*at the event of an incorrect guess, this keeps track of all the incorrect guessed
   			 * characters*/
   			if(word.indexOf(guessChar)== -1){
   				IncorrectGuess += guessChar;   							
   			}
   			
   			/*for every incorrect guess the counter is decremented to keep track of the chances 
   			 * of incorrect guess left for the player  & at the same time pass this parameter to
   			 * display the hang man parts added for every incorrect guess*/
   			if((word.indexOf(guessChar)== -1)||(guessChar.length()>1)){
   				if (GuessCounter>1)print("The word now looks like this : " +temp1);	
   				GuessCounter--; 
   				canvas.noteIncorrectGuess(IncorrectGuess,GuessCounter);   				
   			}
   			
   			/*to keep track at any point in game once the guessed word is same as the word
   			 * chosen then exits the loop*/
   			if(temp1.equals(word)||GuessCounter==0) break;
   		}	//end of while loop
   		
   		/*exit of loop is either guessed word is correct to print player won or because of 
   		 * 8 chances are exhausted by the player to print player loses*/
   		if(temp1.equals(word)) GameWinner();
   		else if (GuessCounter==0) GameLooser();
   		}	//end of PlayGame method
		
	
	/*this method asks player to enter the guessed character & prompts if more than 1 
	*character is entered*/
	private String guessChar;
	private void GuessChar(){		
		guessChar = readLine("Your guess : ");
		if (guessChar.length()>1){
			println("You can enter only 1 character!");	
			GuessCounter++;
		}
		guessChar = guessChar.toUpperCase();		
	}
	
	
	/*in case if valid input of 1 character is entered by player, then this method 
	*checks is the chosen word has the same character. Depending on the outcome 
	*displays the current word filled in if guessed character is present in the word */
	private void CharPresent(){
		if(word.indexOf(guessChar)== -1){
			println("There are no "+guessChar + "'s in the word");
		}
		else{
			int c = word.indexOf(guessChar);
			
			//println("pos " +c);		//added for the purpose of debugging
			
			/*upon a correct guess to fill the guessed character at the right position in
			 *the word & display the same */
			FillWord(c, guessChar);
		}
	}
	
	
	/*upon a correct guess to fill the guessed character at the right position in the word
	 * & display the same */
	private void FillWord(int c, String guessChar){
		println("That guess is correct");
		temp1 = temp1.substring(0,c)+guessChar+temp1.substring(c+1);
		while(word.indexOf(guessChar,c+1)!=-1){
			c = word.indexOf(guessChar,c+1);
			temp1 = temp1.substring(0,c)+guessChar+temp1.substring(c+1);
		}
		if(!temp1.equals(word))	print("The word now looks like this : " +temp1);
	}
	
	/*prints when player wins*/
	private void GameWinner(){
		println("You guessed the word: "+word);
		println("You Win!");
	}
	
	/*prints when player loses*/
	private void GameLooser(){
		println("You are completely hung.");
   		println("The word was :" +word);
   		println("You Lose.");
	}
}
