public class BST<E extends Comparable<E>> {

    private Node<E> root;

    public BST(){
        root = null;
    }
    // down and slow, focus
    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){

        // Find the right spot in the tree for the new node
        // Make sure to check if anything is in the tree
        // Hint: if a node n is null, calling n.getData() will cause an error
        root = insertHelper(getRoot(), data);
    }
    
    private Node<E> insertHelper(Node<E> root, E data){
   
    	//once we have reached the appropriate place in the tree,
    	//we make a new node with the data we wish to insert,
    	//add the data into the tree (in a spot that was previously null/
    	//empty leaf), and then return our tree
    	//reaching root == null means that we're at the end of the tree and
    	//at the right spot because of the tree traversing >, < below
    	if (root == null){
    		Node<E> addNode = new Node<E>(data);
    		root = addNode;
    		return root;
    	}
    	
    	//the below is the same as the delete function in that we are searching
    	//through the tree until we find the appropriate spot to add the new
    	//data
    	
    	//if data is bigger than the root in question (compareTo return > 0)
    	//then go to left where
    	//smaller values in the BST will be obviously via recursion
    	if (root.getData().compareTo(data) > 0)
    		root.setLeftChild(insertHelper(root.getLeftChild(), data));
    	//same situation except go to the right when the tree has
    	//nodes that are bigger than the data value trying to be inserted
    	else if (root.getData().compareTo(data) < 0)
    		root.setRightChild(insertHelper(root.getRightChild(), data));
    	
    	//return our tree
    	return root;
    }

    public void find(E data){

        // Search the tree for a node whose data field is equal to data
        root = findHelper(getRoot(), data);
    }
    
    private Node<E> findHelper(Node<E> root, E data){
    	
    	//go so long as it hasn't reached the end of the tree (i.e. null)
    	while (root != null){
    	
        //if it found the data value in the tree, break out and return position
    	if (data == root.getData()) break;
    	
    	//if it didn't, then go left or right down the tree
    	//depending on whether the tree val is greater/less than the
    	//value we are searching for
    	if (data.compareTo(root.getData()) > 0) root = root.getLeftChild();		
    	else if (data.compareTo(root.getData()) < 0) root = root.getRightChild();

    	}
    	
    	return root; 
    }

    public void delete(E data){
    	
    	// Find the right node to be deleted

        // If said node has no children, simply remove it by setting its parent to point to null instead of it.
        // If said node has one child, delete it by making its parent point to its child.
        // If said node has two children, then replace it with its successor,
        //          and remove the successor from its previous location in the tree.
        // The successor of a node is the left-most node in the node's right subtree.
        // If the value specified by delete does not exist in the tree, then the structure is left unchanged.

        // Hint: You may want to implement a findMin() method to find the successor when there are two children
    	
    	root = deleteHelper(getRoot(), data);
    }
    
   private Node<E> deleteHelper(Node<E> root, E data){
   	   	//if tree is empty then there's nothing to delete. 
   	   	//So just return the empty tree
    	if (root == null) return root;
    	
    	//if data is bigger than the root in question (compareTo return > 0)
    	//then go to left where
    	//smaller values in the BST will be obviously via recursion
    	if (root.getData().compareTo(data) > 0)
    		root.setLeftChild(deleteHelper(root.getLeftChild(), data));
    	//same situation except go to the right when the tree has
    	//nodes that are bigger than the data value trying to be deleted
    	else if (root.getData().compareTo(data) < 0)
    		root.setRightChild(deleteHelper(root.getRightChild(), data));
    	
    	//case for when we have found the node that matches the one needing
    	//to be deleted
    	else if (root.getData().compareTo(data) == 0){
    		
    		//if both children are leaves (i.e. null) then no problem,
    		//delete the node by setting root = null and good to go
    		if ((root.getLeftChild() == null) && (root.getRightChild() == null)){
    			root = null;
    			return root;
    		}
 
    		//if the node does have a child, then we are going to skip over it
    		//via getRightChild() or getLeftChild() to continue the tree connection
    		//and remove the node to be deleted. It will be the opposite child to the
    		//one thats empty because we know from above if we are still here in the
    		//algorithm, there has to be a child present. 
			if (root.getLeftChild() == null){
				root = root.getRightChild();
				return root;
			}
			else if (root.getRightChild() == null){
				root = root.getLeftChild();
				return root;
			}
    		
    	
			//if we get here, we know the root to be deleted has two children
			//from the root to be deleted, find the minimum element in the subtree
			//this is also known as the inorder successor
    		Node<E> getMinR = getMin(root.getRightChild());
    		
    		//now we replace the node to be deleted with the min node we just found
    		root.setData(getMinR.getData());
    		
    		//our recursive function then goes right and will take the right input
    		//along with root.getData() which is our min node we just found. We must
    		//delete it at the position that it came from to avoid duplicates
    		root.setRightChild(deleteHelper(root.getRightChild(), root.getData()));
    	}
    	//when the min node is deleted, return the root
    	return root;
    }
    	
    public void traverse(String order, Node<E> top) {
        if (top != null){
            switch (order) {
                case "preorder":
                	//root, left, right
                    System.out.print(top.getData() + " "); // " " makes it print out correctly as seen in the pdf
                    traverse(order, top.getLeftChild());
                    traverse(order, top.getRightChild());
                    break;

                case "inorder":
                	//left, root, right
                    traverse(order, top.getLeftChild());
                    System.out.print(top.getData() + " ");
                    traverse(order, top.getRightChild());
                    break;

                case "postorder":
                	//left, right, root
                    traverse(order, top.getLeftChild());
                    traverse(order, top.getRightChild());
                    System.out.print(top.getData() + " ");
                    break;
            }
        }
    }
    
    public Node<E> getMin(Node<E> top){
        // Return the min node
        
        //our current min value is the value given to us,
        //and may stay that way if we don't find anything
        //smaller
        E min = top.getData();
        
        //keep on looping left because the based off a BST,
        //thats where the smallest (or min) values will be
        while (top.getLeftChild() != null){
        	//set new min
        	min = top.getLeftChild().getData();
        	//go to next node
        	top = top.getLeftChild();
        }
        //return the node with the smallest value, we hit a leaf
        return top;
    }
    
    
    
    public boolean Same(Node<E> tree1, Node<E> tree2) {
    	
       //if both null(empty) then they are the same, return true
       if ((tree1 == null) && (tree2 == null)) return true;
       
       //if they both have elements, then we are simply going to check the
       //left side first, and then the right side. This will be done
       //via the return statement at the end that checks the data,
       //which the recursion will eventually get to
       if ((tree1 != null) && (tree2 != null)){
       	   Same(tree1.getLeftChild(), tree2.getLeftChild());
       	   Same(tree1.getRightChild(), tree2.getRightChild());
       }
       
       //if one or the other ever empties out (meaning its longer/shorter
       //than the other, then we know we know they aren't the same so return
       //false
       if ((tree1 != null) && (tree2 == null)) return false;
       if ((tree1 == null) && (tree2 != null)) return false;
       
       //will return true if the recursive values above keep coming back the
       //same via this line
       return (tree1.getData() == tree2.getData());
       
       }
   }
       	  