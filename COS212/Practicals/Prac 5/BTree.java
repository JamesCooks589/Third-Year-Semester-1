public class BTree<T extends Comparable<T>> {

	private int m;
	private Node<T> root;

	/**
	 * 
	 * @param m
	 */
	public BTree(int m) {
		this.m = m;
		root = null;
	}

	/**
	 * 
	 * @param data
	 */
	public Node<T> insert(T data) {
		//Empty tree
		if (root == null) {
			root = new Node<T>(m);
			root.getKeys()[0] = data;
			root.Leaf = true;
			root.numKeys = 1;
			return root;
		}

		//Node already exists
		if (find(data) != null){
			return find(data);
		}

		//Non empty tree
		if (root != null){
			root = root.insert(data);
			
		}
		
		//Return inserted node
		return find(data);


		
	}

	/**
	 * 
	 * @param data
	 */
	public Node<T> find(T data) {
		//Find the node that contains the data else return null

		if (root != null){
			return root.find(data);
		}
		else{
			return null;
		}

	}

	public Node<T>[] nodes() {
		// TODO - implement BTree.nodes
		throw new UnsupportedOperationException();
	}

	public int numKeys() {
		if (root == null){
			return 0;
		}
		else{
			return RECcountNumKeys(root);
		}		
	}

	public int countNumNodes() {
		if (root == null){
			return 0;
		}
		else{
			return RECcountNumNodes(root);
		}
	}

	//Helper getRoot
	public Node<T> getRoot(){
		return root;
	}

	//Recursive count number of keys
	public int RECcountNumKeys(Node<T> node){
		int counter = 0;
		if (node != null){
			counter += node.numKeys;
			//if not leaf
			if (!node.Leaf){
				for (int i = 0; i < node.numKeys + 1; i++){
					counter += RECcountNumKeys(node.getChildren()[i]);
				}
			}
		}
		return counter;
	}

	//Recursive count number of nodes
	public int RECcountNumNodes(Node<T> node){
		int counter = 0;
		if (node != null){
			counter++;
			//if not leaf
			if (!node.Leaf){
				for (int i = 0; i < node.numKeys + 1; i++){
					counter += RECcountNumNodes(node.getChildren()[i]);
				}
			}
		}
		return counter;
	}




}