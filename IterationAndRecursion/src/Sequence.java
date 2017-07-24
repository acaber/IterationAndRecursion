/**
 * File Name: Sequence.java
 * Author: Rebecca Johnson
 * Date: 7/23/2017
 * Purpose: Contains the computeIterative method and the computeRecursive
 * 	method to calculate the specified value in the sequence.
 */

public class Sequence {
	
	//holds the efficiency value
	private static int efficiency = 0;
	
	//computeIterative method that calculates the specified value in the sequence iteratively
	public static int computeIterative(int n) {
		
		//the term before n
		int previousTerm;
		
		//the term that is before the term before n
		int secondPreviousTerm;
		
		//the result
		int result = 0;
		
		//resets efficiency to 0
		efficiency = 0;

		//iterative calculation
		if(n == 0) 
			result = 0;
		else if (n == 1) 
			result = 1;
		else {
			secondPreviousTerm = 0;
			previousTerm = 1;
			
			//iterative loop
			for(int i = 2; i <= n; i++) {
				
				//increases efficiency value
				efficiency++;
				
				//calculates result
				result = (2 * previousTerm) + secondPreviousTerm;
				secondPreviousTerm = previousTerm;
				previousTerm = result;
			}
		}
			
		return result;
	}
	
	//computeRecursive method that calls the recursiveHelper method
	public static int computeRecursive(int n) {
		
		//resets efficiency to 0
		efficiency = 0;
		
		//calls recursiveHelper method
		return recursiveHelper(n);
		
	}
	
	//recursvieHelper method that calculates the specified value in the sequence recursively
	private static int recursiveHelper(int n) {
		
		//increases efficiency value
		efficiency++;
		
		if(n == 0)
			return 0;
		else if(n == 1)
			return 1;
		else  
			//recursive calculation
			return (2 * recursiveHelper(n - 1)) + recursiveHelper(n - 2);	
	}
	
	//returns the efficiency value
	public static int getEfficiency() {
		return efficiency;
	}
}
