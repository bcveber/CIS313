import java.util.Comparator;
import java.lang.Math;

public class MaxHeap<E extends Comparable> {
    private E[] myArray;
    private int maxSize;
    private int length;

    public MaxHeap(int s){
    	// Build the constructor
    	maxSize = s;
    	length = 0;
    	myArray = (E[]) new Comparable[maxSize];
    }

	// helper functions
    public E[] getArray(){
        return myArray;
    }

    public void setArray(E[] newArray){
    	if (newArray.length > maxSize){
    		return;
    	}
        myArray = newArray;
        length = newArray.length-1;
    }

    public int getMaxSize(){
        return maxSize;
    }

    public void setMaxSize(int ms){
        maxSize = ms;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int l){
        length = l;
    }

    // Other Methods
    public void insert(E data){
    
    	// Insert an element into your heap.
    	
    	// When adding a node to your heap, remember that for every node n, 
    	// the value in n is less than or equal to the values of its children, 
    	// but your heap must also maintain the correct shape.
		// (ie there is at most one node with one child, and that child is the left child.)
		// (Additionally, that child is farthest left possible.)
		
		//add the value to be inserted at the bottom of the tree
		int myLength = length;
		myArray[myLength] = data;
		E myData;
	
		//Loop: keep going until you reach the root/top of the tree
		//and while the inserted node is bigger than the parent node, keep pushing the
		//inserted node up by switching its parent node with the spot that it was in before
		//and vice versa
		while ((myLength > 0) && (myArray[(myLength - 1)/2].compareTo(myArray[myLength])) < 0){
			
			//parent and inserted node switching procedure triggers if parent is smaller than
			//inserted node
			myData = myArray[(myLength - 1)/2];
			myArray[(myLength - 1)/2] = myArray[myLength];
			myArray[myLength] = myData;
			myLength = (myLength - 1)/2;
		}
		length++;
    }

    public Comparable<E> maximum(){
        // return the minimum value in the heap
        //max value of a maxHeap will be at the top (root) of the tree,
        //so we just simply return the 0 index (assuming the tree isn't
        //empty, in that case we would have null)
        if (myArray.length != 0) return myArray[0];
        else return null;
        
    }

    public Comparable<E> extractMax(){
        // remove and return the minimum value in the heap
        
        //max value will be at the top of our heap
        E data = myArray[0];
        
        //take the last value in our tree and make it the first
        //i.e. replace the value we are removing
        myArray[0] = myArray[length - 1];
        
        //decrease our tree size (cut off the value we just put
        //at the top)
        length--;
        
        //now heapify this new value
        heapify(0);
        
        //return our max value (myArray[0])
        return data;
    }

    public void heapify(int i){
    	// helper function for reshaping the array
    	
    	//our root (biggest value), left and right children
    	int big = i;
        int right = (i*2) + 2;
        int left = (i*2) + 1;
        
        //if the left child is bigger than the big(root), then we initiate
        //a switch needing to be made and make the left now in the parent spot
        if ((left <= length) && (myArray[big].compareTo(myArray[left]) < 0))
        	big = left;
        
        //same situation as above except right
        if ((right <= length) && (myArray[big].compareTo(myArray[right]) < 0))
        	big = right;
        
        //if the left or right children was bigger, then this will trigger
        //and begin the switching of the two values
        if (big != i){
        	E data = myArray[big];
        	myArray[big] = myArray[i];
        	myArray[i] = data;
        	
        	//since we made a switch, there could be more issues with that
        	//switch and new placement of the value,
        	//so we need to run heapify via recursion again to
        	//check and see if more issues arise
        	heapify(big);
        }
        	
       //if we have reached here, then everything is in order
    }
    
    public void buildHeap(E[] newArr){
		// used for the extra credit

	}
}
