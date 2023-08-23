public class List<T> {
    public int length;
    public Node<T> head;

    public List() {
        length = 0;
        head = null;
    }

    public String toString() {
        //Empty list
        if (head == null) {
            return "";
        }
        else {
            Node<T> ptr = head;
            String out = "";
            while (ptr != null) {
                if (ptr.next == null) {
                    out += ptr;
                    ptr = ptr.next;
                }
                else{
                    out += ptr + ",";
                    ptr = ptr.next;
                }
                
            }

            return out;
        }
    }

    public void append(T val) {
        //Empty list
        if (head == null) {
            head = new Node<T>(val);
        }
        //Go to end of list
        else {
            Node<T> ptr = head;
            while (ptr.next != null) {
                ptr = ptr.next;
            }
            //Append to end of list
            ptr.next = new Node<T>(val);
        }
        length++;
    }

    public boolean remove(T val) {
        //Empty list
        if (head == null) {
            return false;
        }
        else {
            Node<T> ptr = head;
            Node<T> prev = null;
            while (ptr != null) {
                if (ptr.data.equals(val)) {
                    //Remove head
                    if (prev == null) {
                        head = ptr.next;
                        length--;
                        return true;
                    }
                    //Remove tail
                    else if (ptr.next == null) {
                        prev.next = null;
                        length--;
                        return true;
                    }
                    //Remove middle
                    else {
                        prev.next = ptr.next;
                        length--;
                        return true;
                    }
                }
                prev = ptr;
                ptr = ptr.next;
            }
            return false;
        }
    }

    public boolean remove(List<T> val) {
        if (val.head == null) {
            return true;
        }
        if (head == null) {
            return false;
        }
        Node<T> ptr = head;
        Node<T> ptr2 = val.head;
        int valLength = val.length;
        while (ptr != null) {
            ptr2 = val.head;
            while (ptr2 != null) {
                if (ptr.data.equals(ptr2.data)) {
                    remove(ptr.data);
                    remove(ptr2.data);
                    ptr2 = ptr2.next;
                }
                else {
                    ptr2 = ptr2.next;
                }
            }
            ptr = ptr.next;
        }
        if (valLength == val.length) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean contains(T search) {
        //Empty list
        if (head==null) {
            return false;
        }
        //Search list
        Node<T> ptr = head;
        while (ptr != null) {
            if (ptr.data.equals(search)) {
                return true;
            }
            ptr = ptr.next;
        }
        //Not found
        return false;
            
        
    }

    public boolean equals(List<T> other) {        
        if (other == null) {
            return false;
        }
            
        if (head==null && other.head==null) {
            return true;
        }
        else if (head==null || other.head==null) {
            return false;
        }
        Node<T> ptr1 = head;
        Node<T> ptr2 = other.head;
        while (ptr1 != null && ptr2 != null) {
            if (!ptr1.data.equals(ptr2.data)) {
                return false;
            }
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        if (ptr1 != null || ptr2 != null) {
            return false;
        }
        return true;
    }
}
