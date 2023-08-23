import java.util.Random;

// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<T>> {
    private int maxLevel;
    private SkipListNode<T>[] root;
    private int[] powers;
    // Do not change the seed. This is used to generate the same values every run
    private Random randomGenerator = new Random(123456);

    public SkipList(int maxLevel) {
        this.maxLevel = maxLevel;
        root = new SkipListNode[maxLevel];
        for (int i = 0; i < maxLevel; i++) {
            root[i] = null;
        }
        powers = new int[maxLevel];
        powers[maxLevel-1] = (2 << (maxLevel-1)) - 1;
		for (int i = maxLevel - 2, j = 0; i >= 0; i--, j++)
			powers[i] = powers[i+1] - (2 << j);
        
    }

    public int chooseLevel() {
        int count, randomNum = Math.abs(randomGenerator.nextInt()) % powers[maxLevel-1] + 1;
        for (count = 1; count < maxLevel; count++) {
            if (randomNum < powers[count]) {
                return count-1;
            }
        }
        return count-1;
    }

    public void insert(T key) {
        SkipListNode<T>[] ptr = new SkipListNode[maxLevel];
        SkipListNode<T>[] prevPtr = new SkipListNode[maxLevel];  
        SkipListNode<T> newNode;
        int count, level;

            ptr[maxLevel - 1] = root[maxLevel - 1];
            prevPtr[maxLevel - 1] = null;

            for (level = maxLevel - 1; level >= 0; level--){
                while (ptr[level] != null && ptr[level].key.compareTo(key) <= 0){
                    prevPtr[level] = ptr[level];
                    ptr[level] = ptr[level].next[level];
                }
                

                if (level > 0){
                    if(prevPtr[level] == null){
                        ptr[level - 1] = root[level - 1];
                        prevPtr[level - 1] = null;
                    }
                    else{
                        ptr[level - 1] = prevPtr[level].next[level - 1];
                        prevPtr[level - 1] = prevPtr[level];
                    }
            }

        


            }
            level = chooseLevel();
            newNode = new SkipListNode<T>(key, level + 1);
            for (count = 0; count <= level; count++){
                newNode.next[count] = ptr[count];
                if (prevPtr[count] == null){
                    root[count] = newNode;
                }
                else{
                    prevPtr[count].next[count] = newNode;
                }
            }
    }

    public boolean isEmpty() {
        if (root[0] == null) {

            return true;

        }
        else {

            return false;

        }
    }

    public SkipListNode<T> search(T key) {
        if (this.isEmpty()) {
            return null;
        }
        SkipListNode<T> ptr;
        SkipListNode<T> prevPtr;
        int level;
        for (level = maxLevel - 1; level >= 0 && root[level] == null; level--);
        prevPtr = ptr = root[level];
        while(true) {
            if (key.equals(ptr.key))
            return ptr;
            else if (key.compareTo(ptr.key) < 0) {
                if (level == 0)
                return null;
            else if(ptr == root[level]) 
                ptr = root[--level];
            else ptr = prevPtr.next[--level];
            }
            else {
                prevPtr = ptr;
                if (ptr.next[level] != null)
                ptr = ptr.next[level];
                else {
                    for (level--; level >= 0 && ptr.next[level] == null; level--);
                    if (level < 0)
                    return null;
                    else ptr = ptr.next[level];
                }                 
            }
        }
    }


    @Override
    public String toString() {
        String[] levels = new String[maxLevel];
        for (int i = 0; i < maxLevel; i++) {
            levels[i] = "[Lvl " + i + "]";
        }
        String out = "";
        if (this.isEmpty()) {
            for (int i = maxLevel - 1; i >= 0; i--) {
                if (i == 0) {
                    out += levels[i];
                } else {
                    out += levels[i] + "\n";
                }
                    
                }
                return out;
        }
        SkipListNode<T> ptr = root[0];
        while (ptr != null) {
            for (int count = 0; count < maxLevel; count++) {
                if (count < ptr.next.length) {
                    levels[count] += "->" + ptr.toString();
                } else {
                    levels[count] += "--" + ptr.emptyString();
                }
                    
                }
                ptr = ptr.next[0];
            }
            
        //remove all excess - from the end of the strings of the levels array
        for (int i = 0; i < maxLevel; i++) {
            while (levels[i].endsWith("-")) {
                levels[i] = levels[i].substring(0, levels[i].length() - 1);
            }
        }
        for (int i = maxLevel - 1; i >= 0; i--) {
            if (i == 0) {
                out += levels[i];
            } else {
                out += levels[i] + "\n";
            }
                
            }
        return out;
    }

    public boolean delete(T key) {
        if (this.isEmpty()) {
            return false;
        }
        if (this.search(key) == null) {
            return false;
        }
        SkipListNode<T> target = search(key);
        SkipListNode<T>[] prevPtrs = new SkipListNode[target.levelofNode];
        for (int count = target.levelofNode - 1; count >= 0; count--) {
            SkipListNode<T> ptr = root[count];
            if (target == ptr) {
                prevPtrs[count] = null;
            }
            else {
                while (ptr.next[count] != target) {
                    ptr = ptr.next[count];
                }
                prevPtrs[count] = ptr;
            }
        }
        for (int count = 0; count < target.levelofNode; count++) {
            if (prevPtrs[count] == null) {
                root[count] = target.next[count];
            }
            else 
            {
                prevPtrs[count].next[count] = target.next[count];
            }
        }

        return true;


        
    }

    public void printSearchPath(T key) {
        if (this.isEmpty()) {
            return;
        }
        if (this.search(key) == null) {
            return;
        }
        
        SkipListNode<T> ptr;
        SkipListNode<T> prevPtr;
        int level;
        String out = "";
        for (level = maxLevel - 1; level >= 0 && root[level] == null; level--);
        prevPtr = ptr = root[level];
        while(ptr.key.compareTo(key) != 0) {
            out += ptr.toString();
            if (key.equals(ptr.key))
            out += ptr.toString();
            else if (key.compareTo(ptr.key) < 0) {
                if(ptr == root[level]) 
                ptr = root[--level];

            else ptr = prevPtr.next[--level];
            }
            else {
                prevPtr = ptr;
                if (ptr.next[level] != null)
                ptr = ptr.next[level];
                else {
                    for (level--; level >= 0 && ptr.next[level] == null; level--);
                    ptr = ptr.next[level];
                }                 
            }
        }
       System.out.println(out + ptr.toString()); 
    }
}
