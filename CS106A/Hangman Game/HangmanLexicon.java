/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import acm.util.*;

public class HangmanLexicon {

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		ReadFile();
		return Lexicon.size();
		//return 10;
	}

/** Returns the word at the specified index. */
	
	private BufferedReader RD;
	private ArrayList <String> Lexicon;
	public String getWord(int index) {
		return Lexicon.get(index);
	}
	
	public void ReadFile(){
		try{
			RD = new BufferedReader(new FileReader("HangmanLexicon.txt"));	//reads words from text file word by word
			Lexicon = new ArrayList <String>();
			while(true){
			String ReadWord = RD.readLine();	
			if(ReadWord==null) break;	//adds the word read from text file to the ArrayList
			Lexicon.add(ReadWord);			
			}
			RD.close();
			
		} catch (IOException e){
			throw new ErrorException ("An error occured: " +e);
		}
	}
		/*switch (index) {
			case 0: return "BUOY";
			case 1: return "COMPUTER";
			case 2: return "CONNOISSEUR";
			case 3: return "DEHYDRATE";
			case 4: return "FUZZY";
			case 5: return "HUBBUB";
			case 6: return "KEYHOLE";
			case 7: return "QUAGMIRE";
			case 8: return "SLITHER";
			case 9: return "ZIRCON";
			default: throw new ErrorException("getWord: Illegal index");
		}*/	
}
	

