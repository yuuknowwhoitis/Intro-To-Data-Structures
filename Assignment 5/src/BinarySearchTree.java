public class BinarySearchTree implements BSTInterface {


	protected class BSTNode{
		String item;
		BSTNode left;
		BSTNode right;

		public BSTNode(){
			left = null;
			right = null;
		}

		public BSTNode(String item){
			this.item = item;
			left = null;
			right = null;
		}

	}

	BSTNode root;

	public BinarySearchTree(){
		root = null;
	}

	public BinarySearchTree(BSTNode root){
		this.root = root;
	}

	// returns whether tree is empty or not
	public boolean isEmpty() {
		return root == null;
	}

	//Empty the tree
	public void makeEmpty() {
		root = null;
	}

	//add string to BST
	public void put(String s) {
		root = recursiveInsert(root, s);
	}

	//returns true is bst contains the string
	public boolean contains(String s) {
		return recursiveSearch(root, s);
	}

	//removes string from bst
	public void delete(String s) {
		root = recursiveRemove(root, s);
	}

	//returns queue with elements in order
	public MyQueue inOrder() {
		MyQueue queue = new MyQueue();
		inOrderRecursive(root, queue);
		return queue;
	}

	//returns queue with elements in preorder
	public MyQueue preOrder() {
		MyQueue queue = new MyQueue();
		preOrderRecursive(root, queue);
		return queue;
	}

	//returns queue with elements in postorder
	public MyQueue postOrder() {
		MyQueue queue = new MyQueue();
		postOrderRecursive(root, queue);
		return queue;
	}
/*
	//balance tree
	public void balanceBST() {
		MyQueue orderQueue = inOrder();
		MyQueue temp = new MyQueue();
		int count = 0;
		//get count
		while(!orderQueue.isEmpty()){
			temp.enqueue(orderQueue.dequeue());
			count++;
		}

		String[] items = new String[count];
		int index = 0;
		while(!temp.isEmpty()){
			items[index] = (String) temp.dequeue();
			index++;
		}
		makeEmpty();
		root = balanceRecursive(items, 0, index - 1);


	}*/

	// TODO: Fill this in and call it from contains()
	protected boolean recursiveSearch(BSTNode node, String s) {
		if(node == null){
			return false;
		}
		else if(node.item.compareTo(s) == 0){
			return true;
		}
		else if(s.compareTo(node.item) < 0){
			return recursiveSearch(node.left, s);
		}
		else if(s.compareTo(node.item) > 0){
			return recursiveSearch(node.right, s);
		}

		return false;
	}

	// TODO: Fill this in and call it from put()
	protected BSTNode recursiveInsert(BSTNode node, String s){
		if(node == null){
			BSTNode newNode = new BSTNode(s);
			return newNode;
		}

		else if(s.compareTo(node.item) < 0){
			node.left = recursiveInsert(node.left, s);
		}
		else if(s.compareTo(node.item) > 0){
			node.right = recursiveInsert(node.right, s);
		}
		else if(s.compareTo(node.item) == 0){

		}

		return node;
	}

	// TODO: Fill this in and call it from delete()
	protected BSTNode recursiveRemove(BSTNode node, String s) {
		if(node != null){
			if(s.compareTo(node.item) < 0){
				node.left = recursiveRemove(node.left, s);
			}

			else if(s.compareTo(node.item) > 0){
				node.right = recursiveRemove(node.right, s);
			}
			else if(s.compareTo(node.item) == 0){
				node = deleteNode(node);
			}
		}
		else if(node == null){

		}
		return node;

	}
	
	// TODO: Fill this in and call it from recursiveRemove()
	protected BSTNode deleteNode(BSTNode node) {
		if(node.left == null && node.right == null){
			node = null;
		}
		else if(node.left != null && node.right == null){
			node = node.left;
		}
		else if(node.left == null && node.right != null){
			node = node.right;
		}
		else if(node.left != null && node.right != null){
			String small = getSmallest(node.right);
			node.item = small;
			node.right = recursiveRemove(node.right, small);
		}

		return node;

	}

	// TODO: Fill this in and call it from deleteNode()
	protected String getSmallest(BSTNode node) {
		String smallest = node.item;
		while(node.left != null){
			smallest = node.left.item;
			node = node.left;
		}

		return smallest;
	}


	// TODO: Fill this in and call it from inOrder()
	protected void inOrderRecursive(BSTNode node, MyQueue queue) {
		if(node != null){
			inOrderRecursive(node.left, queue);
			queue.enqueue(node.item);
			inOrderRecursive(node.right, queue);
		}
	}


	// TODO: Fill this in and call it from preOrder()
	protected void preOrderRecursive(BSTNode node, MyQueue queue) {
		if(node != null){
			queue.enqueue(node.item);
			inOrderRecursive(node.left, queue);
			inOrderRecursive(node.right, queue);
		}
	}

	// TODO: Fill this in and call it from postOrder()
	protected void postOrderRecursive(BSTNode node, MyQueue queue) {
		if(node != null){
			inOrderRecursive(node.left, queue);
			inOrderRecursive(node.right, queue);
			queue.enqueue(node.item);
		}
	}

	// Prints out the tree structure, using indenting to show the different levels of the tree
	public void printTreeStructure() { 
		printTree(0, root);
	}

	// Recursive helper for printTreeStructure()
	protected void printTree(int depth, BSTNode node) {
		indent(depth);
		if (node != null) {
	    	System.out.println(node.item);
	    	printTree(depth + 1, node.left);
	    	printTree(depth + 1, node.right);
	 	} 
	 	else {
	  		System.out.println("null");
	  	}
	}

	// Indents with with spaces 
	protected void indent(int depth) {
		for(int i = 0; i < depth; i++)
			System.out.print("  "); // Indents two spaces for each unit of depth
	}


	// Extra Credit 

	// TODO: If doing the extra credit, fill this in and call it from balanceBST()
	/*protected BSTNode balanceRecursive(String[] array, int first, int last) {
		int index;
		if(first > last){
			return	null;
		}
		else{
			int mid = (first + last) / 2;

		}
	}*/
}