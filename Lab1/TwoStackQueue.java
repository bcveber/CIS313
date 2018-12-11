
public class TwoStackQueue<E> {
	private Stack<E> headStack;
	private Stack<E> tailStack;
	
	public TwoStackQueue(){
		
		// We want to initialize our Queue to be empty
		// (ie) set head and tail to be null
		//make your 2 stacks
		headStack = new Stack<E>();
		tailStack = new Stack<E>();
		
	}
	
	public void enqueue(E newData){
	
		// Create a new node whose data is newData and whose next node is null
		// Update head and tail
		// Hint: Think about what's different for the first node added to the Queue
		//push them all onto one stack
		headStack.push(newData);

	}
	
	public Node<E> dequeue(){
	
		// Return the head of the Queue
		// Update head
		// Hint: The order you implement the above 2 tasks matters, so use a temporary node
	 	//	     to hold important information
		// Hint: Return null on a empty Queue
		
		Node<E> temp;
		
		//if nothings there return null
		if (headStack.isEmpty()) return null;
		
		//while the headStack isn't empty, pop off all its values and push them onto the other stack
		while (!(headStack.isEmpty())) tailStack.push(headStack.pop().getData());
		
		//now take the one value (the dequeue) off the other stack
		temp = tailStack.pop();
		
		//empty out the 2nd stack and put them all back on, with the bottom val in the 1st stack now being gone from previous actions of above line
		while (!(tailStack.isEmpty())) headStack.push(tailStack.pop().getData());
		return temp;	
	}
	
	public boolean isEmpty(){
	
		// Check if the Queue is empty
		if (headStack == null) return true;
		else return false;
		
	}
	
	public void printQueue(){

		// Loop through your queue and print each Node's data
		//empty the headStack and push them all onto the other stack
		while(!headStack.isEmpty())
			tailStack.push(headStack.pop().getData());
		//now print them off the 2nd stack, successfully replicating the order of what a queue would be
		tailStack.printStack();
	}
}