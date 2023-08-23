public class NaturalOrderList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        NaturalOrderList<T> newList = new NaturalOrderList<T>();
        return newList;
    }

    @Override
    public void access(T data) {
        
    }

    @Override
    public void insert(T data) {
        Node<T> newNode = new Node<T>(data);
        Node<T> ptr = head;
        //Empty list
        if (head == null) {
            head = newNode;
            return;
        }
        //newNode is larger than head
        if (head.data.compareTo(data) < 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return;
        }
        //insert newNode
        while (ptr != null){
            if (ptr.data.compareTo(data) < 0) {
                newNode.next = ptr;
                newNode.prev = ptr.prev;
                ptr.prev.next = newNode;
                ptr.prev = newNode;
                return;
            }
            ptr = ptr.next;
        }
    }
}
