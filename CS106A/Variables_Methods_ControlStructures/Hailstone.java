/*
 * File: Hailstone.java
 * Name: Deepa
 * ConsoleProgram that reads in a number from the user and then displays the Hailstone
 * sequence for that number, just as in Hofstadter’s book, followed by a line showing the number of steps
 * taken to reach 1.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int i=0;
		int n = readInt("Enter a number : ");
		/*End point of the Hailstone is 1, so the loop should continue until n=1*/
		while(n!=1){		
		/*formula to calculate "n" when user input is even*/
		if(n%2==0){			
			println(n + " is even so I take half : " + n/2);
			n =n/2;
		}
		/*formula to calculate "n" when user input is odd*/
		else{
			println(n + " is odd, so I make 3n+1 : " + ((3*n)+1));
			n=(3*n)+1;
		}
		/*"i" is used to count the number of steps to reach n=1*/
		i++;
		}
		println("The process took " +i +" steps to reach 1");
	}			
		
}


