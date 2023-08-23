public class Treap<T extends Comparable<T>> {
    public Node<T> root = null;

    @Override
    public String toString() {
        if (root == null) {
            return "";
        }

        return root.toString() + "\n" + toString(root, "");
    }

    private String toString(Node<T> curr, String pre) {
        if (curr == null)
            return "";
        String res = "";

        if (curr.left != null) {
            if (curr.right != null) {
                res += pre + "├(L)─ " + curr.left.toString() + "\n" + toString(curr.left, pre + "|    ");
            } else {
                res += pre + "└(L)─ " + curr.left.toString() + "\n" + toString(curr.left, pre + "     ");
            }
        }

        if (curr.right != null) {
            res += pre + "└(R)─ " + curr.right.toString() + "\n" + toString(curr.right, pre + "   ");
        }
        return res;
    }

    /*
     * Don't change anything above this line
     */

    public void insert(T data) throws DatabaseException {
        //Check if root is null, if so, create a new node and set it to root
        if (root == null) {
            root = new Node<T>(data);
            return;
        }
        //Check if data is already in the tree, if so, throw an exception
        if (search(root, data) != null) {
            throw DatabaseException.duplicateInsert(data);
        }

        recInsert(root, data);


        
    }

    public Node<T> remove(T data) {
        //Check if root is null, if so, return null
        if (root == null) {
            return null;
        }
        //Check if data is in the tree, if not, return null
        Node<T> node = search(root, data);
        if (node == null) {
            return null;
        }
        //If node is only node in tree, set root to null and return node
        if (node == root && root.left == null && root.right == null) {
            root = null;
            return node;
        }
        //Check if node is leaf
        if (node.left == null && node.right == null) {
            Node<T> parent = searchParent(root, data);
            if (parent.left == node) {
                parent.left = null;
            }
            else {
                parent.right = null;
            }
            return node;
        }

        return recRemove(data, node);
        

    }

    public Node<T> access(T data) {
        //Check if root is null, if so, return null
        if (root == null) {
            return null;
        }
        //Check if data is in the tree, if not, return null
        Node<T> node = search(root, data);
        if (node == null) {
            return null;
        }
        node.priority++;
        if(node == root){
            return node;
        }
        //Check if node needs to be rotated based on new priority
        Node<T> parent = searchParent(root, data);
        while (parent != null && parent.priority <= node.priority) {
            if (parent.left == node) {
                node = rotateRight(parent.left);
            }
            else {
                node = rotateLeft(parent.right);
            }
            parent = searchParent(root, data);
        }
        return node;
    }

    //HELPER METHODS

    public Node<T> search(Node<T> root,T data) {
        if (root == null) {
            return null;
        }
        if (root.data.compareTo(data) == 0) {
            return root;
        }
        if (root.data.compareTo(data) > 0) {
            return search(root.left, data);
        }
        return search(root.right, data);
    }

    public Node<T> searchParent(Node<T> node, T data) {
        //Check if root is null, if so, return null
        if (node == null) {
            return null;
        }
        //Check if root is the parent of the node we are looking for, if so, return root
        if (node.left != null && node.left.data.compareTo(data) == 0) {
            return node;
        }
        //Check if root is the parent of the node we are looking for, if so, return root
        if (node.right != null && node.right.data.compareTo(data) == 0) {
            return node;
        }
        //Check if root is greater than data, if so, search left subtree
        if (node.data.compareTo(data) >= 0) {
            return searchParent(node.left, data);
        }
        //Check if root is less than data, if so, search right subtree
        return searchParent(node.right, data);
    }

    public Node<T> recInsert(Node<T> node, T data) {
        if(node == null){
            return new Node<T>(data);
        }
        if(node.data.compareTo(data) <= 0){
            node.right = recInsert(node.right, data);

            if(node.right.priority >= node.priority){
                    return rotateLeft(node.right);
            }
            
                
        }
        else{

            node.left = recInsert(node.left, data);

            if(node.left.priority >= node.priority){
                return rotateRight(node.left);
            }
        }
        return node;
        
    }

    public Node<T> rotateRight(Node<T> node) {
        if(node == null){
            return null;
        }
        if(node == root){
            return null;
        }
        Node<T> p = searchParent(root, node.data);
        Node<T> gp = searchParent(root, p.data);
        p.left = node.right;
        node.right = p;
        if (p == root){
            root = node;
        }
        else {
            if (gp.left == p){
            gp.left = node;
        }
        else{
            gp.right = node;
        }
    }
        return node;

    }

    public Node<T> rotateLeft(Node<T> node) {
        if(node == null){
            return null;
        }
        if(node == root){
            return null;
        }
        Node<T> p = searchParent(root, node.data);
        Node<T> gp = searchParent(root, p.data);
        p.right = node.left;
        node.left = p;
        if (p == root){
            root = node;
        }
        else
            { if (gp.left == p){
                gp.left = node;
            }
            else{
                gp.right = node;
            }
        }
        return node;
    }

    public Node<T> recRemove(T data, Node<T> node){
        if(node.right != null && node.left != null){
            if(node.right.priority >= node.left.priority){
                rotateLeft(node.right);
            }else{
                rotateRight(node.left);
            }
            return recRemove(data, node);
        }
        else{
            Node<T> parent = searchParent(root, data);
            if(parent == null){
                if(node.left != null){
                    root = node.left;
                }
                else{
                    root = node.right;
                }
            }
              else if(parent.left == node){
                    if(node.left != null){
                        parent.left = node.left;
                    }
                    else{
                        parent.left = node.right;
                    }
                }
                else{
                    if(node.left != null){
                        parent.right = node.left;
                    }
                    else{
                        parent.right = node.right;
                    }
                }
    }
    return node;
    }

    


}
