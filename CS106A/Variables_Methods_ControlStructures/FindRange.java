/*
 * File: FindRange.java
 * Name: Deepa
 * FindRange problem : 
 * If the user enters the sentinel on the very first input line, then no values have been entered, and
   your program should display a message to that effect.
 * If the user enters only one value before the sentinel, the program should report that value as
   both the largest and smallest.
 * display the smallest and largest values in the list, list terminates when sentinel is entered
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
		public void run() {
		println("This program finds the largest and smallest numbers.");
		int smallest=0, largest=0, n=0;
		n = readInt(" ? ");
		if (n==sentinel){							//condition check if 0 entered in 1st attempt
			println("No values entered");
		}
		else{
			smallest = n;
			largest = n;
			n = readInt(" ? ");
			if(n==sentinel){						//condition check if 0 entered in 2nd attempt
				println("Largest : "+largest);		//when true print smallest & largest equal to 1st no. entered
				println("Smallest : "+smallest);
			}
			else{
				while(n!=sentinel){					//evaluate largest/smallest only till entered no. is non zero
				if(n>largest){
					largest=n;
				}
				else if(n<smallest){
					smallest=n;
				}
				n = readInt(" ? ");
				}
				println("Largest : "+largest);
				println("Smallest : "+smallest);
			}			
		}	
	}
private static final int sentinel = 0;			//can be changed to any value
}
	
	

