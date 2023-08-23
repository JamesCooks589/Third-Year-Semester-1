public class IterativeTraverse<T extends Comparable<T>> extends Traverser<T>{
    public IterativeTraverse(){
        list = null;
    };
    
    public IterativeTraverse(SelfOrderingList<T> list){
        //Use clone to make deep copy
        this.list = clone(list);


    }

    @Override
    public SelfOrderingList<T> reverseList() {
        Node<T> ptr = list.getTail();
        //Work backwards
        SelfOrderingList<T> newList = this.list.getBlankList();
        while (ptr != null) {
            newList.insert(ptr.data);
            ptr = ptr.prev;
        }
        return newList;
        
    }

    @Override
    public boolean contains(T data) {
        Node<T> ptr = list.head;
        if (list.head == null) {
            return false;
        }
        while (ptr != null) {
            if (ptr.data.compareTo(data) == 0) {
                return true;
            }
            ptr = ptr.next;
        }
        return false;
    }

    @Override
    public String toString() {
        String out = "->";
        Node<T> ptr = list.head;
        if (list.head == null) {
            return out;
        }
        while (ptr != null){
            if (ptr.next != null) {
                out += ptr + "->";
                ptr = ptr.next;
            } else {
                out += ptr;
                ptr = ptr.next;
            }
                
            
        }
        return out;
    }

    @Override
    public Node<T> get(int pos) {
        Node<T> ptr = list.head;
        int count = 0;
        if (list.head == null) {
            return null;
        }
        while (ptr != null) {
            if (count == pos) {
                return ptr;
            }
            count++;
            ptr = ptr.next;
        }
        if (ptr == null && count != pos) {
            return null;
        }
        return null;
    }

    @Override
    public Node<T> find(T data) {
        Node<T> ptr = list.head;
        if (list.head == null) {
            return null;
        }
        while (ptr != null) {
            if (ptr.data.compareTo(data) == 0) {
                return ptr;
            }
            ptr = ptr.next;
        }
        return null;
    }

    @Override
    public int size() {
        int count = 0;
        Node<T> ptr = list.head;
        while(ptr != null){
            count++;
            ptr = ptr.next;
        }
        return count;
    }

    @Override
    public SelfOrderingList<T> clone(SelfOrderingList<T> otherList) {
        if (otherList == null) {
            return null;
        }
        Node<T> ptr = otherList.head;
        SelfOrderingList<T> newList = otherList.getBlankList();
        while(ptr != null){
            newList.insert(ptr.data);
            ptr = ptr.next;
        }
        return newList;
    }
}
