/* 
 * File: FixingBrokenJava.java
 * Name:
 * Section Leader:
 * 
 * This program does not work as intended.  It contains both
 * compile-time errors (errors that prevent the compiler from even
 * running the program) and run-time errors (errors where the
 * program does not function as intended).  Your job is to fix
 * this program so that it works correctly.  Note that it is *not*
 * sufficient to simply fix the compiler errors; you will need to
 * update the logic as well.
 * 
 * This program attempts to read a positive integer from the user,
 * then check whether that integer is prime (whether its only
 * divisors are 1 and itself).  If so, it prints a message saying
 * that the number is prime; otherwise it says that the number is
 * composite.
 */
import acm.program.*;
public class FixingBrokenJava extends ConsoleProgram {
	/* Reads a number from the user and reports whether or not it
	 * is prime.
	 */
	public void run() {
		/* Get the value from the user. */
		int value = readPositiveInt();
		
		/* Check whether or not it is prime. */
		if (isPrime(value)) {
			println(value + " is prime.");
		} else {
			println(value + " is composite.");
		}		
	}
	
	/**
	 * Given a positive integer, returns whether that integer is
	 * prime.
	 * 
	 * @param value The value to test.
	 * @return Whether or not it is prime.
	 */
	private boolean isPrime(int value) {
		/* Try all possible divisors of the number.  If any of them
		 * cleanly divide the number, we return that the number is
		 * composite.
		 */
		int flag=0;
		for(int div=1;div<=value;div++){
			if(value%div==0){
				flag++;
			}
		}
		if(flag<=2) return true;
		else return false;	
	}
	
	/**
	 * Reads a positive integer from the user and returns it.
	 * 
	 * @return A positive integer entered by the user.
	 */
	private int readPositiveInt() {
		/* Get an initial value. */
		int value = readInt("Enter a positive integer: ");
		
		/* If the value was negative, re prompt the user. */
		while (value <= 0) {
			println("Please enter a positive integer.");
			value = readInt("Enter a positive integer: ");
		}
		
		return value;
	}
}
