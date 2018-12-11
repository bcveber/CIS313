
import java.util.Scanner;

public class lab1 {
	public static void main(String[] args){
	
		// Create a Scanner that reads system input
		
		// Loop over the scanner's input
		// For each line of the input, send it to isPalindrome()
		// If isPalindrome returns true, print "This is a Palindrome." 
		// Otherwise print "Not a Palindrome."
		
		// Close the Scanner
		
		//open up scanner
		Scanner scan = new Scanner(System.in);
		int val = scan.nextInt(); //get nextint in file
		//System.out.println(val);
		
		
		for(int i = 0; i < val; i++){
			String input = scan.next(); //grabs the entire line of numbers i.e. 30325
			//now take the input into isPalindrome and print accordingly
			if (isPalindrome(input)) System.out.println("This is a Palindrome.");
	        else System.out.println("Not a Palindrome.");
	    }
		
		scan.close();

	}
	
	public static boolean isPalindrome(String s){
	
		// Create a stack and a Queue of chars that represents the passed in string
		// Hint: While you loop through the given string, push the same char onto your stack
		//		 that you enqueue into your Queue. This way you can use dequeue to get 
		//       the string from left to right, but you pop the string from right to left
		
		// Compare your Queue and Stack to see if the input String was a Palindrome or not
		
		//create your stack and queue
		Queue<Character> queue = new Queue<Character>();
		Stack<Character> stack = new Stack<Character>();
		
		char val;
		int size = s.length(); //get size of the number in question
		
		//queue and push up
		for(int i = 0; i < size; i++){
			queue.enqueue(s.charAt(i));
			stack.push(s.charAt(i));
		}
	
		for(int i = 0; i < size; i++){
	
			//now pop and dequeue and compare results
			Character myQueueData = queue.dequeue().getData();
			Character myStackData = stack.pop().getData();
			
			if (myQueueData != myStackData){
				return false;
			}
			
		}
		
		return true;
	}
		
	
	public static boolean isPalindromeEC(String s){
	
		// Implement if you wish to do the extra credit.
		//Going to assume based off Piazza that this doesn't need to be done
		return false;
		
	}
}
