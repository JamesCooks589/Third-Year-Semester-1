abstract class SelfOrderingList<T extends Comparable<T>> {
    public Node<T> head = null;

    public void insert(T data){
        Node<T> ptr = head;
        Node<T> newNode = new Node<T>(data);
        if (head == null) {
            head = newNode;
        } else {
            while (ptr.next != null) {
                ptr = ptr.next;
            }
            ptr.next = newNode;
            newNode.prev = ptr;
        }
    }

    public void remove(T data){
       //TODO: Implement the function
         Node<T> target = head;
         if (head == null) {
             return;
         }

         //head is the node and only node
         if (head.data.compareTo(data) == 0 && head.next == null) {
             head = null;
             return;
         }

         //Second node is the node and only 2 nodes
            if (head.next.data.compareTo(data) == 0 && head.next.next == null) {
                head.next = null;
                return;
            }

            //head is the node and more than 1 node
            if (head.data.compareTo(data) == 0 && head.next != null) {
                head = head.next;
                head.prev = null;
                return;
            }

            //search for node

         
            while (target != null) {
                if (target.data.compareTo(data) == 0) {
                    if (target.prev == null) {
                        head = target.next;
                        if (target.next != null) {
                            target.next.prev = null;
                            
                        }
                    } else {
                        target.prev.next = target.next;
                        if (target.next != null) {
                            target.next.prev = target.prev;
                        }
                    }
                    return;
                }
                target = target.next;
            }
    }

    public abstract void access(T data);

    public abstract SelfOrderingList<T> getBlankList();

    public Node<T> getTail(){
        Node<T> ptr = head;
        if (head == null) {
            return null;
        }
        while (ptr.next != null) {
            ptr = ptr.next;
        }
        return ptr;
        
    }
}
