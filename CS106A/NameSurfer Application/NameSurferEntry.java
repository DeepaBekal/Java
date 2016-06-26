/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
	public NameSurferEntry(String line) {
		SplitLine(line);
	}
	
	
	/**
	 * Given a line this method splits the name & rankings. Converts rankings to Integer & 
	 * stores in a Integer array.
	 * @param line consists of data which has Name & rankings eg. Aaron 193 208 218 274 279 232 132 36 32 31 41 77
	 */
	private void SplitLine(String line){
		int space = line.indexOf(' ');
		Name = line.substring(0, space);					//till occurrence of 1st space is the name
		String num = line.substring(space+1);
		StringTokenizer st = new StringTokenizer(num);		//splits the rankings which r in String type
		for(int i=0;st.hasMoreTokens();i++){
			String temp = st.nextToken();					//returns every rank in String type
			int rank = Integer.parseInt(temp);				//ranking converted to Integer
			Rankings[i]=rank;								//ranking added to Integer Array
		}
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		return Name;										
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {
		return Rankings[decade];
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		String temp = "\""+Name+"[";
		for(int i=0;i<NDECADES;i++){
			temp+= getRank(i)+" ";
		}
		temp += "]\"";
		return temp;
	}
	
	private String Name;
	private int[] Rankings = new int[NDECADES];					//integer array to store the converted rankings from String
}

