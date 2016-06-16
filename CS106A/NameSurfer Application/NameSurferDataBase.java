import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import acm.util.ErrorException;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
		BuildDataBase(filename);
	}
	
	
	private void BuildDataBase(String filename){
		try{
			RD = new BufferedReader(new FileReader(filename));
			while(true){
				String temp = RD.readLine();
				if(temp==null) break;
				NameSurferEntry Reader = new NameSurferEntry(temp);
				NameSurfMap.put(Reader.getName(), Reader);
			} RD.close();			
		}
		catch(Exception e){
			throw new ErrorException ("An error occured : "+e);
		}
		
		
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		
		String CheckCase1, CheckCase2 ="";
		CheckCase1 = name.substring(0,1);
		CheckCase1=CheckCase1.toUpperCase();
		CheckCase2=name.substring(1);
		CheckCase2=CheckCase2.toLowerCase();
		name = CheckCase1+CheckCase2;
		if (NameSurfMap.containsKey(name)) return NameSurfMap.get(name);
		else return null;
	}
	
	private BufferedReader RD;
	private HashMap<String, NameSurferEntry> NameSurfMap = new HashMap<String, NameSurferEntry>();
	
}

