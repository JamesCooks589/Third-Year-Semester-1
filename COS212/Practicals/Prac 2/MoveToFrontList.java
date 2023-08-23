public class MoveToFrontList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        MoveToFrontList<T> newList = new MoveToFrontList<T>();
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
        
        //search for node
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


        //remove node from list
        //check if node is last node
        if (ptr.next == null) {
            ptr.prev.next = null;
            ptr.next = head;
            head.prev = ptr;
            head = ptr;
            ptr.prev = null;
        }
        else
        {
            ptr.prev.next = ptr.next;
            ptr.next.prev = ptr.prev;
            ptr.next = head;
            head.prev = ptr;
            head = ptr;
            ptr.prev = null;
        }        


    }
}
