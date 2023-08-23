public class CountList<T extends Comparable<T>> extends SelfOrderingList<T>{
    @Override
    public SelfOrderingList<T> getBlankList() {
        CountList<T> newList = new CountList<T>();
        return newList;
    }

    @Override
    public void access(T data) {
        Node<T> ptr = head;
        int tempCount = 0;
        //Empty list
        if (head == null) {
            return;
        }
        //head is the node
        if (head.data.compareTo(data) == 0) {
            head.accessCount++;
            return;
        }
        //search for node and increment accessCount
        while (ptr != null){
            if (ptr.data.compareTo(data) == 0) {
                ptr.accessCount++;
                break;
            }
            ptr = ptr.next;
        }
        Node<T> traverser = head;
        // traverse list and sort it by accessCount use flag to check if whole list has been sorted
        while (traverser != null) {
            boolean flag = true;
            while (traverser.next != null) {
                if (traverser.accessCount < traverser.next.accessCount) {
                    tempCount = traverser.accessCount;
                    traverser.accessCount = traverser.next.accessCount;
                    traverser.next.accessCount = tempCount;
                    T tempData = traverser.data;
                    traverser.data = traverser.next.data;
                    traverser.next.data = tempData;
                    flag = false;
                }
                traverser = traverser.next;
            }
            if (flag) {
                break;
            }
            traverser = head;
        }
       
        

    }
}
