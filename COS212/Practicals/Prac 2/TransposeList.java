public class TransposeList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        TransposeList<T> newList = new TransposeList<T>();
        return newList;
    }

    @Override
    public void access(T data) {
        Node<T> ptr = head;
        //Empty list
        if (head == null) {
            return;
        }
        //head is the node
        if (head.data.compareTo(data) == 0) {
            return;
        }
        //second node is the node
        if (head.next.data.compareTo(data) == 0){
            ptr = head.next;
            head.next = ptr.next;
            ptr.next.prev = head;
            ptr.next = head;
            ptr.prev = null;
            head.prev = ptr;
            head = ptr;
            return;
        }
        //find node
        while (ptr != null){
            if (ptr.data.compareTo(data) == 0) {
                break;
            }
            ptr = ptr.next;
        }
        //not found
        if (ptr == null) {
            return;
        }
        //ptr is last node swap with previous node
        if (ptr.next == null) {
            ptr.prev.next = null;
            ptr.next = ptr.prev;
            ptr.prev = ptr.next.prev;
            ptr.next.prev = ptr;
            ptr.prev.next = ptr;
            return;
        }
        //swap node with previous node
        ptr.prev.next = ptr.next;
        ptr.next.prev = ptr.prev;
        ptr.next = ptr.prev;
        ptr.prev = ptr.next.prev;
        ptr.next.prev = ptr;
        ptr.prev.next = ptr;


        
    }
}
