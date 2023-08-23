public class Node<T extends Comparable<T>> {

	private Comparable<T>[] keys;
	private Node<T>[] children;
	//Helper variables
	public boolean Leaf;
	public int numKeys;
	public int orderOfTree;

	/**
	 * 
	 * @param m
	 */
	@SuppressWarnings("unchecked")
	public Node(int m) {
		// TODO - implement Node.Node
		keys = new Comparable[m - 1];
		children = new Node[m];

		//Initialize helper variable
		numKeys = 0;
		orderOfTree = m;
	}

	@Override
	public String toString() {
		String res = "[";
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null)
				res += keys[i];
			else
				res += "null";
			res += ",";
		}
		if (res.length() > 1) {
			res = res.substring(0, res.length() - 1);
		}
		return res + "]";
	}

	//Helper functions
	public Node<T> insert(T key) {
		if (this.numKeys == orderOfTree - 1){
			Node<T> newNode = new Node<T>(orderOfTree);
			newNode.Leaf = false;
			newNode.children[0] = this;
			newNode.splitNode(0, this, key);

			return newNode;
		}
		else{
			insertNonFullNode(key);
			return this;
		}
	}

	public void insertNonFullNode(T key){
		//i is last index of keys
		int i = numKeys - 1;

		if(Leaf){
			//Find the correct position for the key
			//Then shift up the keys to make room for the new key
			while(i >= 0){
				if(keys[i].compareTo(key) > 0){
					keys[i+1] = keys[i];
				}
				else{
					break;
				}
				i--;
			}

			//Insert the key
			keys[i+1] = key;
			numKeys++;
		}
		else
		{
			//Find the correct child to insert the key
			while(i >= 0 && keys[i].compareTo(key) > 0){
				i--;
			}

			//If the child is full, split it
			if(children[i+1].numKeys == orderOfTree - 1){
				splitNode(i+1, children[i+1], key);

				//If the key is greater than the key at the split, go to the right child
				if(keys[i+1].compareTo(key) < 0){
					i++;
				}
			}

			//Insert the key into the child
			children[i+1].insertNonFullNode(key);
		}
	}

	@SuppressWarnings("unchecked")
	public void splitNode(int index, Node<T> node, T key){
	/*	//Create a new node to hold the second half of the keys
		Node<T> newNode = new Node<T>(orderOfTree);
		newNode.Leaf = node.Leaf;
		newNode.numKeys = orderOfTree - 1;
		int middleNum = (int) Math.floor((orderOfTree - 1)/2);

		//Copy the second half of the keys to the new node using floor of (m-1)/2

		for(int i = 0; i < orderOfTree - 1; i++){
			newNode.keys[i] = node.keys[i + orderOfTree];
			node.keys[i + orderOfTree] = null;
		}

		//If the node is not a leaf, copy the second half of the children to the new node
		if(!node.Leaf){
			for(int i = 0; i < orderOfTree; i++){
				newNode.children[i] = node.children[i + orderOfTree];
				node.children[i + orderOfTree] = null;
			}
		}

		//Set the number of keys in the original node
		node.numKeys = orderOfTree - 1;

		//Move the children of the current node to make room for the new node
		for(int i = numKeys; i >= index + 1; i--){
			children[i+1] = children[i];
		}

		//Link new child to this node
		children[index+1] = newNode;

		//Move the keys of the current node to make room for the new key
		for(int i = numKeys - 1; i >= index; i--){
			keys[i+1] = keys[i];
		}

		//Copy the middle key of the original node to this node
		keys[index] = node.keys[orderOfTree - 1];
		node.keys[orderOfTree - 1] = null;

		//Increment the number of keys in this node
		numKeys++;*/

		//Create a new node to hold the second half of the keys
		int middleNum = (int) Math.floor((orderOfTree - 1)/2);
		Node<T> newNode = new Node<T>(orderOfTree);
		newNode.Leaf = node.Leaf;
		newNode.numKeys = orderOfTree - 1/2;

		//Take all the keys from the original node and the new data and put it in a temp array
		Comparable<T>[] temp = (Comparable<T>[]) new Comparable[orderOfTree];
		for(int i = 0; i < orderOfTree - 1; i++){
			temp[i] = node.keys[i];
		}
		temp[orderOfTree - 1] = key;

		//Sort the temp array
		

		//Take the middle key and put it in the parent node
		keys[index] = (T) temp[middleNum];


		//Distribute the keys between old node and new node
		for(int i = 0; i < middleNum; i++){
			node.keys[i] = temp[i];
		}

		for(int i = middleNum + 1; i < orderOfTree - 1; i++){
			newNode.keys[i - middleNum - 1] = temp[i];
		}

		//If the node is not a leaf, copy the second half of the children to the new node
		if(!node.Leaf){
			for(int i = 0; i < orderOfTree; i++){
				newNode.children[i] = node.children[i + orderOfTree];
				node.children[i + orderOfTree] = null;
			}
		}

		//Set the number of keys in the original node
		node.numKeys = orderOfTree - 1/2;

		//Move the children of the current node to make room for the new node
		for(int i = numKeys; i >= index + 1; i--){
			children[i+1] = children[i];
		}

		//Link new child to this node
		children[index+1] = newNode;

		
		numKeys++;


	}

	//Helper find function

	public Node<T> find(T key){
		//Find the first key greater than key
		int i = 0;
		while(i < numKeys && keys[i].compareTo(key) < 0){
			i++;
		}

		//If the key is found, return this node
		if(keys[i -1].compareTo(key) == 0){
			return this;
		}

		//If the key is not found and this is a leaf, return null
		if(Leaf){
			return null;
		}
		else{
			return children[i - 1].find(key);
		}

	}

	//getKeys function
	public Comparable<T>[] getKeys(){
		return keys;
	}

	//Traversal functions
		// Function to traverse all nodes in a subtree rooted with this node
		public void traverse(){
			int index;
	
			//traverse the keys, if it is not a leaf go to child node first.
			for(index = 0; index < numKeys; index++){
				if(!Leaf){
					children[index].traverse();
				}
				System.out.print(" " + keys[index]);
			}
	
			//traverse the missed node
			if(!Leaf){
				children[index].traverse();
			}
		}

	//GetChildren function
	public Node<T>[] getChildren(){
		return children;
	}


}