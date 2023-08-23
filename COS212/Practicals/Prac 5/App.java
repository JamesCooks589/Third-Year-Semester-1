public class App {
	public static void main(String[] args) throws Exception {
		// TODO - implement App.main
		BTree<Integer> tree = new BTree<Integer>(3);
		System.out.println("Number of keys: " + tree.numKeys());
		System.out.println("Number of nodes: " + tree.countNumNodes());
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		tree.insert(4);
		tree.insert(5);
		tree.insert(6);
		tree.insert(7);

		System.out.println("Number of keys: " + tree.numKeys());
		System.out.println("Number of nodes: " + tree.countNumNodes());
		






	}

}