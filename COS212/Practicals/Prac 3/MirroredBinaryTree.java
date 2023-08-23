public class MirroredBinaryTree<T extends Comparable<T>> extends BinaryTree<T> {
    @Override
    public boolean contains(T data) {
        //Call the recursive function
        return contains(root, data);
    }

    protected boolean contains(Leaf<T> leaf, T data) {
        System.out.println(leaf);
        //Search for the data in the tree and print the search path
        //Empty tree
        if (leaf == null) {
            return false;
        }
        //Head is the data
        else if (leaf.data == data) {
            return true;
        }
        //Not found
        else if (leaf.left == null && leaf.right == null) {
            return false;
        }
        //Data is in the left subtree
        else if (leaf.data.compareTo(data) < 0) {
            return contains(leaf.left, data);
        }
        //Data is in the right subtree
        else {
            return contains(leaf.right, data);
        }
    }

    @Override
    public void depthFirstTraversal() {
        //Call the recursive function
        depthFirstTraversal(root);
    }

    protected void depthFirstTraversal(Leaf<T> leaf) {
        //Go through the tree and print the data
        //Empty tree
        if (leaf == null) {
            return;
        }
        //Traverse the tree right to left
        else {
            depthFirstTraversal(leaf.right);
            System.out.println(leaf);
            depthFirstTraversal(leaf.left);
        }
    }

    @Override
    public int numLeavesInTree() {
        //Call the recursive function
        return numLeavesInTree(root);
    }

    protected int numLeavesInTree(Leaf<T> leaf) {
        //Empty tree
        if (leaf == null) {
            return 0;
        }
        int numLeft = numLeavesInTree(leaf.left);
        int numRight = numLeavesInTree(leaf.right);

        return numLeft + numRight + 1;
    }

    @Override
    public int height() {
        return height(root, -1);
    }

    protected int height(Leaf<T> leaf, int height) {
        //Go through the tree and find the height
        if (leaf == null) {
            return height;
        }
        else {
            int leftHeight = height(leaf.left, height + 1);
            int rightHeight = height(leaf.right, height + 1);
            if (leftHeight == rightHeight) {
                return leftHeight;
            }
            else if (leftHeight > rightHeight) {
                return leftHeight;
            }
            else {
                return rightHeight;
            }
        }
    }

    @Override
    public Leaf<T> findParent(T data) {
        //Call the recursive function
        return findParent(root, data);
    }

    protected Leaf<T> findParent(Leaf<T> leaf, T data) {
        //Go through the tree and find the parent of the data
        System.out.println(leaf);
        //Empty tree
        if (leaf == null) {
            return null;
        }
        //Head is the data
        else if (leaf.data == data) {
            return null;
        }
        //Not found
        else if (leaf.left == null && leaf.right == null) {
            return null;
        }
        //Data is in the left subtree
        if (leaf.left != null && leaf.left.data == data) {
            return leaf;
        }
        //Data is in the right subtree
        else if (leaf.right != null && leaf.right.data == data) {
            return leaf;
        }

        if (leaf.data.compareTo(data) < 0) {
            return findParent(leaf.left, data);
        }
        else {
            return findParent(leaf.right, data);
        }
    }

    @Override
    public void insert(T data) {
        super.insert(data, false);
    }

    @Override
    public Leaf<T> find(T data) {
        //Call the recursive function
        return find(root, data);
    }

    protected Leaf<T> find(Leaf<T> leaf, T data) {
        //Search for the data in the tree and print the search path
        System.out.println(leaf);
        //Empty tree
        if (leaf == null) {
            return null;
        }
        //Head is the data
        else if (leaf.data == data) {
            return leaf;
        }
        //Data is in the left subtree
        else if (leaf.data.compareTo(data) < 0) {
            if (leaf.left == null) {
                return null;
            }
            else
            return find(leaf.left, data);
        }
        //Data is in the right subtree
        else {
            if (leaf.right == null) {
                return null;
            }
            else
            return find(leaf.right, data);
        }

       
    }

    @Override
    public boolean perfectlyBalanced() {
        int numLeft = numLeavesInTree(root.left);
        int numRight = numLeavesInTree(root.right);
        if (numLeft == numRight) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public BinaryTree<T> convertTree() {
        StandardBinaryTree<T> newTree = new StandardBinaryTree<T>();
        if (root == null) {
            return newTree;
        }
        else {
            Leaf<T> newRoot = new Leaf<T>(root.data);
            newTree.root = newRoot;
            convertTree(root, newTree.root);
            return newTree;
        }
    }

    protected void convertTree(Leaf<T> ptr, Leaf<T> leaf) {
        //Copy the tree into a new tree
        if (ptr.left != null) {
            //Insert new leaf
            Leaf<T> newLeaf = new Leaf<T>(ptr.left.data);
            leaf.right = newLeaf;

            //Recursive function

            convertTree(ptr.left, leaf.right);
        }
        if (ptr.right != null) {
            //Insert new leaf
            Leaf<T> newLeaf = new Leaf<T>(ptr.right.data);
            leaf.left = newLeaf;

            //Recursive function

            convertTree(ptr.right, leaf.left);
        }
    }
}
