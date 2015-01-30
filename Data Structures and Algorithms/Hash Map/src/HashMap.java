import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashMap<K, V> implements HashMapInterface<K, V>, Gradable<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        table = new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public V add(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        if (size + 1 > table.length * MAX_LOAD_FACTOR) {
            resize();
        }
        MapEntry<K, V> entry = new MapEntry<K, V>(key, value);
        int index = getIndex(key);
        if (table[index] != null && !table[index].isRemoved()) {
            if (table[index].getKey() == key) {
                MapEntry<K, V> current = table[index];
                V old = current.getValue();
                current.setValue(value);
                return old;
            } else {
                int nextEmpty = findNext(index);
                size++;
                table[nextEmpty] = entry;
                return null;
            }
        }
        size++;
        table[index] = entry;
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int index = getIndex(key);
        if (table[index] == null) {
            return null;
        } else if (table[index].getKey() == key
                && !table[index].isRemoved()) {
            MapEntry<K, V> old = table[index];
            old.setRemoved(true);
            size--;
            return old.getValue();
        }
        int prober = index;
        while (table[prober] != null
                && prober + 1 != index) {
            if (table[prober].getKey() == key
                    && !table[prober].isRemoved()) {
                MapEntry<K, V> old = table[prober];
                old.setRemoved(true);
                size--;
                return old.getValue();
            }
            prober++;
            if (prober == table.length) {
                prober = 0;
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (size == 0) {
            return null;
        }
        int index = getIndex(key);
        if (table[index] != null
                && table[index].getKey() == key
                && !table[index].isRemoved()) {
            return table[index].getValue();
        }
        int prober = index + 1;
        while (table[prober] != null
                && prober + 1 != index) {
            if (table[prober].getKey() == key
                    && !table[index].isRemoved()) {
                return table[prober].getValue();
            }
            prober++;
            if (prober == table.length) {
                prober = 0;
            }
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (size == 0) {
            return false;
        }
        int index = getIndex(key);
        if (table[index] != null
                && table[index].getKey() == key
                && !table[index].isRemoved()) {
            return true;
        }
        int prober = index + 1;
        while (table[prober] != null
                && prober + 1 != index) {
            if (table[prober].getKey() == key
                    && !table[index].isRemoved()) {
                return true;
            }
            prober++;
            if (prober == table.length) {
                prober = 0;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        table = new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public MapEntry<K, V>[] toArray() {
        return table;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<K>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                keys.add(table[i].getKey());
            }
        }
        return keys;
    }

    @Override
    public List<V> values() {
        List<V> values = new ArrayList<V>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                values.add(table[i].getValue());
            }
        }
        return values;
    }

    /*
     * Resizes the array and assigns new indices for each
     * key using the new length of the array.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[table.length * 2];
        for (int i = 0; i < table.length / 2; i++) {
            if (oldTable[i] != null && !oldTable[i].isRemoved()) {
                int index = getIndex(oldTable[i].getKey());
                if (table[index] == null) {
                    table[index] = oldTable[i];
                } else {
                    int nextEmpty = findNext(index);
                    table[nextEmpty] = oldTable[i];
                }
            }
        }
    }

    /*
     * Finds index using given key's hashCode
     * @param key
     */
    private int getIndex(K key) {
        int hash = Math.abs(key.hashCode());
        int index = hash % table.length;
        return index;
    }

    /*
     * Finds index of next removed or null spot.
     * @param index the index you're starting at
     */
    private int findNext(int index) {
        int nextEmpty = index + 1;
        while (table[nextEmpty] != null
                && !table[nextEmpty].isRemoved()
                && nextEmpty + 1 != index) {
            nextEmpty++;
            if (nextEmpty == table.length) {
                nextEmpty = 0;
            }
        }
        return nextEmpty;
    }

}
