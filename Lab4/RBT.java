public class RBT<E extends Comparable<E>> {
	private Node<E> nil;
    private Node<E> root;

    public RBT(){
    	nil = new Node<E>(null);	
    	nil.setColor('b');
        root = nil;
    }

    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){
        // Preform a regular insert
        // Check to make sure the tree remains an RBT tree
        Node<E> nilNode = nil; //(y) from the book
        Node<E> rootNode = root; //(x) from the book
        Node<E> insNode = new Node<E>(data);
        
        //so long as the root doesn't equal nil / or until we havent reached end of tree
        //we are going to traverse through the tree until we find the appropriate spot to insert our value
        while (rootNode != nil){  
        	nilNode = rootNode; //our placeholder
        	if (insNode.getData().compareTo(rootNode.getData()) < 0) rootNode = rootNode.getLeftChild();
        	else rootNode = rootNode.getRightChild();
        }
        
        //connect up the node we are trying to insert with its parent in place
        insNode.setParent(nilNode);
        
        //if equal to nil then the value we are trying to insert is simply our root of the tree
        if (nilNode == nil) root = insNode;
        
        //we connected the parent above, now we need to figure out whether the insNode goes
        //on the left or right side of our parent nilNode
        else if (insNode.getData().compareTo(nilNode.getData()) < 0) nilNode.setLeftChild(insNode);
        else nilNode.setRightChild(insNode);

        //maintain proper tree structure
        insNode.setLeftChild(nil);
        insNode.setRightChild(nil);
        insNode.setColor('r');
        
        //coloring our insert node red may screw up the RB tree, so we need to call this
        fixInsert(insNode);
    
    }
    
    public void fixInsert(Node<E> insNode){
    	Node<E> nilNode;
    	//make sure we're working with something
    	if (insNode.getParent().getParent() != null){
    		//if its parent is red, then we have a problem (cant have a red parent to a red insNode)
    		while (insNode.getParent().getColor() == 'r'){
    			
    			//figure out if insNode parent is a left or right child
    			//parent is left child case first
    			if (insNode.getParent() == insNode.getParent().getParent().getLeftChild()){
    				nilNode = insNode.getParent().getParent().getRightChild();
    				
    				//if InsNode's uncle nilNode is red, then property 4(every path has same # of
    				//black nodes) is present. So we change parent of InsNode and
    				//the uncle to black, and change grand parent to red
    				if ((nilNode.getColor() == 'r') && (nilNode != null)){
    					insNode.getParent().setColor('b');
    					nilNode.setColor('b');
    					insNode.getParent().getParent().setColor('r');
    					insNode = insNode.getParent().getParent();
    				}
    				
    				else {
    					//insNode's uncle is black and insNode is a right child
    					//perform a left rotation
    					if (insNode == (insNode.getParent().getRightChild())) {
    						insNode = insNode.getParent();
    						leftRotate(insNode);
    					}
    				//insNode's uncle is black and insNode is a left child
    				//now recolor and do a right rotation to create a legal RB tree
    				insNode.getParent().setColor('b');
    				insNode.getParent().getParent().setColor('r');
    				rightRotate(insNode.getParent().getParent());
    				
    				}
    			}
    			
    			else {
    				
    				//if we're here, parent is a right child
    				//we're going to do the same stuff from above and have the same
    				//fix cases, but just switch left and right since the parent is a right
    				//child and not a left
    				nilNode = insNode.getParent().getParent().getLeftChild();
    				
    				if ((nilNode.getColor() == 'r') && (nilNode != null)){
    					insNode.getParent().setColor('b');
    					nilNode.setColor('b');
    					insNode.getParent().getParent().setColor('r');
    					insNode = insNode.getParent().getParent();
    				}
    				
    				else {
    					if (insNode == (insNode.getParent().getLeftChild())) {
    						insNode = insNode.getParent();
    						rightRotate(insNode);
    					}
    				
    				insNode.getParent().setColor('b');
    				insNode.getParent().getParent().setColor('r');
    				leftRotate(insNode.getParent().getParent());
    				}
    			}
    		}
    	}
    	//root is always black
    	root.setColor('b');
    }
    				
			
    public Node<E> search(E data){
        // Return the node that corresponds with the given data
        // Note: No need to worry about duplicate values added to the tree
        
        Node<E> temp = root;
        
        //while not nil
        while (temp != nil) {
        	
        	//if we've reached end of tree or nothing in tree, return null aka not found
        	if (temp == null) return null;
        	
        	//return location if we've found the data we're looking for
        	if (temp.getData().compareTo(data) == 0) return temp;
        	
        	//if greater than or less than go left or right traversing through the tree
        	else if (temp.getData().compareTo(data) > 0) temp = temp.getLeftChild();
        	else if (temp.getData().compareTo(data) < 0) temp = temp.getRightChild();
        }	
       return null; 
    }

    public void delete(E data){
    	// Preform a regular delete
    	// Check to make sure the tree remains an RBT tree
    	
    	//find the position of the node we're trying to delete and
    	//set up nodes
    	Node<E> delNode = search(data);
    	Node<E> nilNode = nil;
        Node<E> y = delNode; 
        
        //get original color of y
        char y_original_color = y.getColor();
        
        //if theres no left child then we are just going to
        //put in the right child
        if (delNode.getLeftChild() == nil){
        	nilNode = delNode.getRightChild();
        	rb_transplant(delNode, delNode.getRightChild());
        }
        
        //if theres no right child then we are just going to
        //put in the left child
        else if (delNode.getRightChild() == nil){
        	nilNode = delNode.getLeftChild();
        	rb_transplant(delNode, delNode.getLeftChild());
        }
        
        else {
        	//if we're here, then there are 2 children present
        	//right child case
        	y = tree_minimum(delNode.getRightChild());
        	y_original_color = y.getColor(); //preserve OG color
        	nilNode = y.getRightChild();
        	
        	//now we replace the delNode if y's parent equals it
        	if (y.getParent() == delNode) nilNode.setParent(y);

        	//if we get here, then the successor wasn't on the right
        	//replace y with its right child via transplant
        	//make ys right child the delNode right child and set the parent
        	else {
        		rb_transplant(y, y.getRightChild());
        		y.setRightChild(delNode.getRightChild());
        		y.getRightChild().setParent(y);
        	}
        	
        	//replace delNode with its child
        	//replace left child of y with left child of the delNode and set parent
        	rb_transplant(delNode, y);
        	y.setLeftChild(delNode.getLeftChild());
        	y.getLeftChild().setParent(y);
        }
        	
        	//make y the same color as the delNode
        	y.setColor(delNode.getColor());
        	
        	//we have a problem if we removed a black node, so now we call fix
        	if (y_original_color == 'b') fixDelete(nilNode);
        	
    }
    
    //get the minimum by following the left child until we get nil
    public Node<E> tree_minimum(Node<E> delNode) {
    	if (delNode.getLeftChild() != nil) return tree_minimum (delNode.getLeftChild());
    	else return delNode;
    }

	public void fixDelete(Node<E> nilNode){
		//while we're not at the root and NilNode is still black
		while ((nilNode != root) && (nilNode.getColor() == 'b')){

		//determine if nilNode is a left or right child
		//left child case first
		if (nilNode == nilNode.getParent().getLeftChild()){
			Node<E> w = nilNode.getParent().getRightChild();
			
			//its sibling is red case
			//switch colors of w and nilNode, and then left rotate
			if (w.getColor() == 'r'){
				w.setColor('b');
				nilNode.getParent().setColor('r');
				leftRotate(nilNode.getParent());
				w = nilNode.getParent().getRightChild();
			}
			
			//NilNode's sibling is black, and both of w's children are also black
			//make w black
			//make nilNode the new parent
			if ((w.getRightChild().getColor() == 'b') && (w.getLeftChild().getColor() == 'b')){
				w.setColor('r');
				nilNode = nilNode.getParent();
			}
			
			else {
				
				//w is black, w's left child is red, and w's right child is black case
				//switch colors of w and its left child, and then perform right rotation
				if (w.getRightChild().getColor() == 'b'){
					w.getLeftChild().setColor('b');
					w.setColor('r');
					rightRotate(w);
					w = nilNode.getParent().getRightChild();
				}
				
				//w is black, w's right child is red case
				//change colors and left rotate
				//setting nilNode to root means we are done and the loop will terminate
				w.setColor(nilNode.getParent().getColor());
				nilNode.getParent().setColor('b');
				w.getRightChild().setColor('b');
				leftRotate(nilNode.getParent());
				nilNode = root;
			}
		}
		
		
		else {
			
			//if we're here, parent is a right child
    		//we're going to do the same stuff from above and have the same
    		//fix cases, but just switch left and right since the parent is a right
    		//child and not a left
			Node<E> w = nilNode.getParent().getLeftChild();
			if (w.getColor() == 'r'){
				w.setColor('b');
				nilNode.getParent().setColor('r');
				rightRotate(nilNode.getParent());
				w = nilNode.getParent().getLeftChild();
			}
			
			if ((w.getRightChild().getColor() == 'b') && (w.getLeftChild().getColor() == 'b')){
				w.setColor('r');
				nilNode = nilNode.getParent();
			}
			
			else {
				if (!(w.getLeftChild().getColor() == 'b')){
					w.getRightChild().setColor('b');
					w.setColor('r');
					leftRotate(w);
					w = nilNode.getParent().getLeftChild();
				}
			
				w.setColor(nilNode.getParent().getColor());
				nilNode.getParent().setColor('b');
				w.getLeftChild().setColor('b');
				rightRotate(nilNode.getParent());
				nilNode = root;
			}
		}
	} //close up while
	nilNode.setColor('b'); //color new node black
}
	//transplant replaces one subtree as a child of its parent with
	//another subtree. Node u parent becomes node v parent, and u's
	//parent has v as the child
    public void rb_transplant(Node<E> u, Node<E> v){
    	if (u.getParent() == nil) root = v;
    	else if (u.getParent().getLeftChild() == u) u.getParent().setLeftChild(v);
    	else u.getParent().setRightChild(v);
    	if (v != null) v.setParent(u.getParent());
    }

    public void traverse(String order, Node<E> top) {
        // Preform a preorder traversal of the tree
        if (top != null){
        	switch (order) {
        		case "preorder":
        			//root, left, right
        			if (top.getData() != null) {
        				System.out.print(top.getData().toString() + ' ');
        				traverse("preorder", top.getLeftChild());
        				traverse("preorder", top.getRightChild());
        			}
        	}
        }
   }


    public void rightRotate(Node<E> y){
    	
        /*
        If y is the root of the tree to rotate with right child subtree T3 and left child x, 
        where T1 and T2 are the left and right children of x:
            y becomes right child of x and T1 as its left child of x
            T2 becomes left child of y and T3 becomes right child of y
        */
        
        //get our value we're rotating
        Node<E> x = y.getLeftChild();
        
        //replicate x subtree into y subtree
        y.setLeftChild(x.getRightChild());
        if (x.getRightChild() != null) x.getRightChild().setParent(y);
        x.setParent(y.getParent());
        
        //if theres nothing there set = root
        if ((y.getParent() == null) || (y.getParent() == nil)) root = x;
        
        //if theres something, then x becomes y's parent
        else if (y.getParent().getLeftChild() == y) y.getParent().setLeftChild(x);
        else y.getParent().setRightChild(x);
        
        //once done, link up x's child
        x.setRightChild(y);
        y.setParent(x);
    	
    }
    public void leftRotate(Node<E> x){
    
    	/*
        If x is the root of the tree to rotate with left child subtree T1 and right child y, 
        where T2 and T3 are the left and right children of y:
            x becomes left child of y and T3 as its right child of y
            T1 becomes left child of x and T2 becomes right child of x
        */
        
        //get our value we're rotating
        Node<E> y = x.getRightChild();
        
        //replicate y subtree into x subtree
        x.setRightChild(y.getLeftChild());
        if (y.getLeftChild() != null) y.getLeftChild().setParent(x);
        y.setParent(x.getParent());
        
        //if theres nothing there set = root
        if ((x.getParent() == null) || (x.getParent() == nil)) root = y;
        
        //if theres something, then y becomes x's parent
        else if (x.getParent().getLeftChild() == x) x.getParent().setLeftChild(y);
        else x.getParent().setRightChild(y);
        
        //once done, link up y's child
        y.setLeftChild(x);
        x.setParent(y);
		
    }
    
    public boolean isRBT(Node<E> check){
    	//citations:
    	//https://www.geeksforgeeks.org/check-given-binary-tree-follows-height-property-red-black-tree/
    	
    	//check if every node have valid colors (red or black)
    	if (validColors(check) == false) return false;
    	
    	//checks if root is black
    	if(rootBlack(check) == false) return false;
    	
    	//check if every path from a node to leaf contains same # of black nodes
    	if (sameBlack(check) == false) return false;
    	
    	//checks that if a node is red then both of its children 
    	//are going to be black
    	if (childrenBlack(check) == false) return false;
    	
    	//if it passes all the checks, we can return true. it follows all properties
    	return true;	
    }
    
    public boolean childrenBlack(Node<E> check){
    	//empty tree or reached end of tree
    	if (check != null) {
    		//if we get a red node, then we perform the check to see if both
    		//children of it are going to be black
    		if (check.getColor() == 'r'){
    			//if either the left or right child isn't black then the property
    			//is violated and we return false
    			if ((check.getLeftChild().getColor() != 'b') || (check.getLeftChild().getColor() != 'b')) return false;
    			return true; //we can return true for this set of nodes if it passed the above line
    		}
    		//now we go down the tree and check others
    		return (childrenBlack(check.getLeftChild()) && childrenBlack(check.getLeftChild()));
    	}
    	return true; //if it gets here, then all nodes follow the RBT property
    }

    
    public boolean rootBlack(Node<E> check){
    	if (check.getColor() == 'b') return true;
    	else return false;
    }
    
    public boolean validColors(Node<E> check){
    	//keep going down the tree until you reach the end/null
    	if (check != null) {
    		//check for red or black only colors
    		if ((check.getColor() != 'b') || (check.getColor() != 'r')) return false;
    		//traverse through tree
    		return ((validColors(check.getLeftChild())) && (validColors(check.getRightChild())));
    	}
		return true; //if you get here, all colors check out
	}
	
	public boolean sameBlack(Node<E> check){
		int max = 0;
		int min = 0;
		//for recursion purposes
		return (depthCheck(check, max, min));
	}
	
	public boolean depthCheck(Node<E> check, int max, int min){
		
		//if the tree is empty, then its technically balanced
		if (root == null){
			max = 0;
			min = 0;
			return true;
		}
		
		//now split up left and right side for checking
		//left
		int left_max = 0;
		int left_min = 0;
		
		//right
		int right_max = 0;
		int right_min = 0;
		
		//check if left and right subtrees are balanced
		if (depthCheck(check.getLeftChild(), left_max, left_min) == false) return false;
		if (depthCheck(check.getRightChild(), right_max, right_min) == false) return false;
		
		//use math function to find max/min
		//these will tack on 1 for each node we go through
		max = Math.max(left_max, right_max);
		min = Math.min(left_min, right_min);
		
		//now we check for this node if its valid/balanced by taking 2x the min
		if (max <= 2*min) return true;
		
		//if it gets to the end of the loop without returning a true statement, then 
		//we get here and not balanced
		return false;
	}
		
    
    	
    
    
    // HINT: You may want to create extra methods such as fixDelete or fixInsert
}
