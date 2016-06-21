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
	
	private void SplitLine(String line){
		int space = line.indexOf(' ');
		Name = line.substring(0, space);
		String num = line.substring(space+1);
		StringTokenizer st = new StringTokenizer(num);
		for(int i=0;st.hasMoreTokens();i++){
			String temp = st.nextToken();
			int rank = Integer.parseInt(temp);
			Rankings[i]=rank;
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
	private int[] Rankings = new int[NDECADES];
}

