// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
public class SkipListNode<T extends Comparable<T>> {
    public T key;
    public SkipListNode<T>[] next;
    public int levelofNode;

    public SkipListNode(T key, int levels) {
        this.key = key;
        next = new SkipListNode[levels];
        levelofNode = levels;
        for (int i = 0; i < levels; i++) {
            next[i] = null;
        }
    }

    @Override
    public String toString() {
        String out = "[" + key.toString() + "]";
        return out;
    }

    public String emptyString() {
        String out = "";
        for(int i = 0; i < this.toString().length(); i++) {
            out += "-";
        }
        return out;
    }
}