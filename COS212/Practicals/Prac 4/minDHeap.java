@SuppressWarnings("unchecked")
public class minDHeap<T extends Comparable<T>> {
    private int d;
    private T[] nodes;

    @Override
    public String toString() {
        if (nodes.length == 0) {
            return "";
        }

        return "[" + nodes[0] + "]\n" + toStringRec(0, "");
    }

    public String toStringRec(int i, String pre) {
        if (i >= nodes.length) {
            return "";
        }
        String res = "";
        for (int k = 0; k < d; k++) {
            int c = d * i + k + 1;
            if (c < nodes.length) {
                if (k == d - 1 || c + 1 >= nodes.length) {
                    res += pre + "└── " + "[" + nodes[c] + "]\n" + toStringRec(c, pre + "    ");
                } else {
                    res += pre + "├── " + "[" + nodes[c] + "]\n" + toStringRec(c, pre + "│   ");
                }
            }
        }
        return res;
    }

    public T[] getNodes() {
        return nodes;
    }

    /*
     * Don't change anything above this line
     */

    public minDHeap(int d) {
        this.d = d;
        nodes = (T[]) new Comparable[0];
    }

    public void insert(T val) {
        //Temp to copy over the old array
        T[] temp = (T[]) new Comparable[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            temp[i] = nodes[i];
        }

        //Recreate the array with a new size
        nodes = (T[]) new Comparable[nodes.length + 1];
        for (int i = 0; i < temp.length; i++) {
            nodes[i] = temp[i];
        }

        nodes[nodes.length - 1] = val;

        //Bubble up
        int i = nodes.length - 1;
        while (i > 0 && nodes[i].compareTo(nodes[parent(i)]) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public void remove(T val) {
        //Remove out of an empty heap
        if (nodes.length == 0) {
            return;
        }
        //Find the index of the value
        int i = 0;
        for (int j = 0; j < nodes.length; j++) {
            if (nodes[j].equals(val)) {
                i = j;
                break;
            }
            //not found in the heap
            if (j == nodes.length - 1) {
                return;
            }
        }

        //Swap the value with the last value
        swap(i, nodes.length - 1);

        //Temp to copy over the old array
        T[] temp = (T[]) new Comparable[nodes.length];
        for (int j = 0; j < nodes.length; j++) {
            temp[j] = nodes[j];
        }

        //Recreate the array with a new size
        nodes = (T[]) new Comparable[nodes.length - 1];
        for (int j = 0; j < nodes.length; j++) {
            nodes[j] = temp[j];
        }

        //Push down
        pushDown(i);
    }

    public void changeD(int newD) {
       d = newD;
       //Last non-leaf node
         int i = (nodes.length - 1) / d;
      //Floyd algorithm
         while (i >= 0) {
             pushDown(i);
             i--;
         }
       
    }

    public T min(int i) {
        //Min heap so the smallest value is at the root
        if (nodes.length == 0) {
            return null;
        }
        if (i < 0) {
            return null;
        }
        if (i >= nodes.length) {
            return null;
        }
        return nodes[i];
    }

    public T max(int i) {
        //call the recursive max method
        return maxRec(i);
    }

    public String pathToRoot(T val) {
        //Keep finding parent till you reach the root
        int i = 0;
        if (nodes.length == 0) {
            return "";
        }
        for (int j = 0; j < nodes.length; j++) {
            if (nodes[j].equals(val)) {
                i = j;
                break;
            }
            //not found in the heap
            if (j == nodes.length - 1) {
                return "";
            }
        }
        String res = "";
        while (i > 0) {
            res += "[" + nodes[i].toString() + "]";
            i = parent(i);
        }
        res += "[" + nodes[i].toString() + "]";
        return res;
    }




    //Helper methods
    private void swap(int i, int j) {
        T temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;
    }

    public int parent(int i) {
        return  (i - 1) / d;
    }

    //Push down method
    public void pushDown(int i) {
        int c = d * i + 1;
        int min = c;
        for (int k = 1; k < d; k++) {
            if (c + k < nodes.length && nodes[c + k].compareTo(nodes[min]) < 0) {
                min = c + k;
            }
        }
        if (min < nodes.length && nodes[min].compareTo(nodes[i]) < 0) {
            swap(i, min);
            pushDown(min);
        }
    }

    //recursive max method
    public T maxRec(int i) {
        if (nodes.length == 0) {
            return null;
        }
        if (i < 0) {
            return null;
        }
        if (i >= nodes.length) {
            return null;
        }
        T max = nodes[i];
        for (int k = 0; k < d; k++) {
            int c = d * i + k + 1;
            if (c < nodes.length) {
                T childMax = maxRec(c);
                if (childMax != null && childMax.compareTo(max) > 0) {
                    max = childMax;
                }
            }
        }
        return max;
    }


}
