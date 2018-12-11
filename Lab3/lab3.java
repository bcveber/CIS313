import java.util.Scanner;

public class lab3 {
    public static void main(String[] args) {
    
    	// Loop over the Scanner
        // Split each line into the task and the corresponding number (if one exists)
        // Depending on what the input task was, preform the corresponding function
        //      (ie) insert, maximum, extractMax, isEmpty, or print
        // Hint: Use a switch-case statement

        // Don't forget to close your Scanner!
        
        Scanner scan = new Scanner(System.in);
        
        //this picks up how many actions we need to do i.e.
        //the first line in the txt file
        int val = Integer.parseInt(scan.nextLine());
        
        //our pQueue we are going to perform on
        pQueue<Integer> myQueue = new pQueue<Integer>(val);
        
        int i;
       
       
        //now we are going to run for as many actions we need to perform
        for (i = 0; i < val; i++){
        	
        		//input retrieves the next line/function call
        		String input = scan.nextLine();

                //we use the space to get the split
                String[] inputArray = input.split(" ");
                
        	switch(inputArray[0]){
        		case "insert":
        			//the txt file has the number we are trying to add
        			//so the parseInt() function evaluates the string
        			//and turns it into an int that we can use
        			myQueue.insert(Integer.parseInt(inputArray[1]));
        			break;
        		case "isEmpty":
        			if (myQueue.isEmpty()) System.out.println("Empty");
        			else System.out.println("Not Empty");
        			break;
        		case "print":
        			myQueue.print();
        			System.out.println("");
        			break;
        		case "maximum":
        			System.out.println(myQueue.maximum());
        			break;
        		case "extractMax":
        			System.out.println(myQueue.extractMax());
        			break;
        	}
        }		
        scan.close();
    }
}
