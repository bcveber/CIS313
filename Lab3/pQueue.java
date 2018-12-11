public class pQueue<E extends Comparable> {
    private MaxHeap myHeap;

    public pQueue (int s) {
        // Build the Constructor
        myHeap = new MaxHeap(s);
    }

    public void insert(E data){
        myHeap.insert(data);
    }

    public Comparable<E> maximum(){
        return myHeap.maximum();
    }

    public Comparable<E> extractMax(){
        return myHeap.extractMax();
    }

    public boolean isEmpty(){
        // Return true when the priority queue is empty
        // Hint: Do the actual printing in your lab3.java
        return (myHeap.getLength() == 0);
    }

	public void build(E[] arr){
    	// used for the extra credit

    }
    
    public void print(){
        // print out ”Current Queue: ” 
        // followed by each element separated by a comma. 
        // Do not add spaces between your elements.
        int i;
        System.out.print("Current Queue: ");
        //loop through all the way through the heap and print
        for (i = 0; i <= myHeap.getLength()-1; i++){
        	System.out.print(myHeap.getArray()[i]);
        	
        	//this will prevent the printing of an extra comma at the end
        	if (i < myHeap.getLength()-1){
        		System.out.print(",");
        	}
        }
    }
}
