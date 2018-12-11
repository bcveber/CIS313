
import java.util.Scanner;

public class TreeCompare{
	public static void main(String[] args){

		Scanner scan = new Scanner(System.in);
		
		//Essentially what we are doing for TreeCompare is duplicating the lab2.java
		//to service two trees, but we only need the insert function for each one
        
        //our BST's to perform on
        BST<Integer> BST1 = new BST<Integer>();
        BST<Integer> BST2 = new BST<Integer>();
        
        //data will be the raw int value we are trying to perform insert,del, etc. on
        int data;
        
        //scan.nextInt() picks up how many actions need to be performed
        //in this case for inSample.txt it is 10, so we need to loop
        //10 times
        int iterator = scan.nextInt();
        //System.out.println(iterator);
        scan.nextLine(); //takes us past the line "10" because its useless now
        
        for (int i = 0; i < iterator; i++){
        	//we make an array to split the function call and its data
        	//i.e. "insert 30" splits into "insert" and "30"
        	String input = scan.nextLine(); //input retrieves the next line/function call, up to 10 in this case	
        	String[] inputArray = input.split(" "); //we use the space to get the split
        	
        	switch(inputArray[0]){
        		case "insert":
        			//the txt file and array have the number we are trying to
        			//add/delete/find as a string, so the parseInt() function
        			//evaluates the string and turns it into an int that we can use
        			data = Integer.parseInt(inputArray[1]);
        			BST1.insert(data);
        			break;
        	}
        }
        	
        for (int i = 0; i < iterator; i++){
        	//we make an array to split the function call and its data
        	//i.e. "insert 30" splits into "insert" and "30"
        	String input = scan.nextLine(); //input retrieves the next line/function call, up to 10 in this case	
        	String[] inputArray = input.split(" "); //we use the space to get the split
				switch(inputArray[0]){
					case "insert":
						//the txt file and array have the number we are trying to
						//add/delete/find as a string, so the parseInt() function
						//evaluates the string and turns it into an int that we can use
						data = Integer.parseInt(inputArray[1]);
						BST2.insert(data);
						break;
				}
			}
			
			//if there the same, print success
			if (BST2.Same(BST1.getRoot(), BST2.getRoot())) System.out.println("The trees do have the same shape.");
        	//if not, print failure
			else System.out.println("The trees do not have the same shape.");
        	
        scan.close();
    }
}        					