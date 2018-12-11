import java.util.Scanner;

public class lab2 {
    public static void main(String[] args) {

        // Loop over the Scanner
        // Split each line into the task and the corresponding number (if one exists)
        // Depending on what the input task was, preform the corresponding function
        //      (ie) insert, delete, find, inoder, preorder, or postorder
        // Hint: Use a switch-case statement

        // Don't forget to close your Scanner!
        
        Scanner scan = new Scanner(System.in);
        
        //our BST to perform on
        BST<Integer> BST = new BST<Integer>();
        
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
        			BST.insert(data);
        			break;
        		
        		case "find":
        			data = Integer.parseInt(inputArray[1]);
        			BST.find(data);
        			break;
        		
        		case "delete":
        			data = Integer.parseInt(inputArray[1]);
        			BST.delete(data);
        			break;
        			
        		case "preorder":
        			//inputArray[0] will be one of the orders along with the root of
        			//our BST that we made earlier
        			BST.traverse(inputArray[0], BST.getRoot());
        			System.out.println(" "); //so spacing is correct
        			break;
        			
        		case "inorder":
        			BST.traverse(inputArray[0], BST.getRoot());
        			System.out.println(" ");
        			break;
        			
        		case "postorder":
        			BST.traverse(inputArray[0], BST.getRoot());
        			System.out.println(" ");
        			break;
        	}
        }
        
        scan.close();
    }
}