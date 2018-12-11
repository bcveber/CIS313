
public class Queue<E> {
	private Node<E> head;
	private Node<E> tail;
	
	public Queue(){
		
		// We want to initialize our Queue to be empty
		// (ie) set head and tail to be null
		head = null;
		tail = null;
		
	}
	
	public void enqueue(E newData){
	
		// Create a new node whose data is newData and whose next node is null
		// Update head and tail
		// Hint: Think about what's different for the first node added to the Queue
		Node<E> n = new Node<E>(newData, null);
		
		//set head and tail = if nothing there already
		if (isEmpty()) head = tail = n;
		
		//add the node w/ the newdata if something already there
		else{
			tail.setNext(n);
			tail = n;
		}

	}
	
	public Node<E> dequeue(){
	
		// Return the head of the Queue
		// Update head
		// Hint: The order you implement the above 2 tasks matters, so use a temporary node
	 	//	     to hold important information
		// Hint: Return null on a empty Queue
		Node<E> temp;
		
		//return nothing if you can't dequeue anything
		if (isEmpty()) return null;
		
		//pop the front off if there is something to be popped off
		else{
			temp = head;
			head = head.getNext();
			return temp;
		}
	}
	
	public boolean isEmpty(){
	
		// Check if the Queue is empty
		if (head == null) return true;
		else return false;
		
	}
	
	public void printQueue(){

		// Loop through your queue and print each Node's data
		//take the head and cycle through
		Node<E> current = head;
		while (current != null){
			System.out.println(current.getData());
			current = current.getNext();		
		}
	}
}
