public class RecursiveTraverse<T extends Comparable<T>> extends Traverser<T>{
    public RecursiveTraverse(){
        list = null;
    }
    
    public RecursiveTraverse(SelfOrderingList<T> list){
        //Use clone for deep copy
        this.list = clone(list);
    }

    @Override
    public SelfOrderingList<T> reverseList() {
        if (list.head == null){
            return this.list.getBlankList();
        }
        return reverseHelp(this.list.getTail(), this.list.getBlankList());
    }

    public SelfOrderingList<T> reverseHelp(Node<T> node, SelfOrderingList<T> newList){
        //End of list
        if (node == null) {
            return newList;
        }
        //Keep adding
        else {
            newList.insert(node.data);
            return reverseHelp(node.prev, newList);
        }
    }

    @Override
    public boolean contains(T data) {
        Node<T> ptr = find(data);
        if (ptr == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        if (list.head == null) {
            return "->";
        }
        return toStringHelp(this.list.head, "->");
    }

    public String toStringHelp(Node<T> node, String out){
        //End of list
        if (node == null) {
            return out;
        }
        //Keep adding
        else {
            if (node.next != null) {
                out += node + "->";
                return toStringHelp(node.next, out);
            } else {
                out += node;
                return toStringHelp(node.next, out);
            }
        }
    }


    @Override
    public Node<T> get(int pos) {
        if (list.head == null) {
            return null;
        }
            
        return getHelp(this.list.head, pos, 0);
    }

    public Node<T> getHelp(Node<T> node, int pos, int count){
        //Invalid pos
        if (node==null && pos > count || pos < 0) {
            return null;
        }
        //End of list
        if (node == null) {
            return null;
        }
        //Found
        else if (count == pos) {
            return node;
        }
        //Keep looking
        else {
            count++;
            return getHelp(node.next, pos, count);
        }
    }

    @Override
    public Node<T> find(T data) {
        //Call Recursive function
        if (list.head == null) {
            return null;
        }
        return findHelp(this.list.head, data);
    }

    public Node<T> findHelp(Node<T> node, T data){
        //Not found
        if (node == null) {
            return null;
        }
        //Found
        else if (node.data.compareTo(data) == 0) {
            return node;
        }
        //Keep looking
        else {
            return findHelp(node.next, data);
        }
    }

    @Override
    public int size() {
        //Call Recursive function
        if (list.head == null) {
            return 0;
        }
        return sizeHelp(this.list.head, 0);
    }

    public int sizeHelp(Node<T> node, int count){
        //End of list
        if (node == null) {
            return count;
        }
        //Keep counting
        else {
            count++;
            return sizeHelp(node.next, count);
        }
    }

    @Override
    public SelfOrderingList<T> clone(SelfOrderingList<T> otherList) {
        if (otherList == null) {
            return null;
         }
        if (otherList.head == null) {
            return null;
         }
                 
        return cloneHelp(otherList.head, otherList.getBlankList());
    }

    public SelfOrderingList<T> cloneHelp(Node<T> node, SelfOrderingList<T> newList){
        //End of list
        if (node == null) {
            return newList;
        }
        //Keep inserting
        else {
            newList.insert(node.data);
            return cloneHelp(node.next, newList);
        }

    }
        
}
