import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DisjointSets<T> implements DisjointSetsInterface<T> {
    //Should be a map of data to its parent; root data maps to itself.
    private Map<T, Node> set;

    /**
     * @param setItems the initial items (sameSet and merge will never be called
     * with items not in this set, this set will never contain null elements).
     */
    public DisjointSets(Set<T> setItems) {
        set = new HashMap<T, Node>();
        for (T thing : setItems) {
            set.put(thing, new Node(thing, 0));
        }
    }

    @Override
    public boolean sameSet(T u, T v) {
        if (u == null || v == null) {
            throw new IllegalArgumentException();
        }
        return find(u).equals(find(v));
    }

    @Override
    public void merge(T u, T v) {
        if (u == null || v == null) {
            throw new IllegalArgumentException();
        }
        T rU = find(u);
        T rV = find(v);
        if (!rU.equals(rV)) {
            Node nU = set.get(rU);
            Node nV = set.get(rV);
            if (nU.rank < nV.rank) {
                nU.parent = rV;
            } else if (nU.rank > nV.rank) {
                nV.parent = rU;
            } else {
                nV.parent = rU;
                nU.rank++;
            }
        }
    }

    /*
     * Finds the root of the item and compresses the path by
     * setting the item's parent equal to the root.
     * @param T item to find
     * @return T root
     */
    private T find(T item) {
        Node node = set.get(item);
        if (node == null) {
            return null;
        }
        if (item.equals(node.parent)) {
            return item;
        }
        node.parent = find(node.parent);
        return node.parent;
    }

    private class Node {
        public T parent;
        public int rank;

        public Node(T parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }
}
