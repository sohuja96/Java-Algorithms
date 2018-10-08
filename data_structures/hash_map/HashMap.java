import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Your implementation of HashMap.
 * 
 * @author Joshua Santillo
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        this.size = 0;
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException(
                "Arrays can't be negative size!");
        }
        this.table = new MapEntry[initialCapacity];
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException(
                "Null data is not allowed here!");
        }
        size++;
        if (((double) size / (double) table.length) > MAX_LOAD_FACTOR) {
            this.resizeBackingTable(table.length * 2 + 1);
        }
        V out = null;
        int location = Math.abs(key.hashCode()) % table.length;
        if (table[location] == null) {
            table[location] = new MapEntry(key, value);
        } else {
            MapEntry<K, V> search = table[location];
            while (search != null) {
                if (search.getKey().equals(key)) {
                    V found = search.getValue();
                    search.setValue(value);
                    size--;
                    return found;
                }
                search = search.getNext();
            }
            if (search == null) {
                out = null;
                MapEntry<K, V> put = new MapEntry(key, value);
                put.setNext(table[location]);
                table[location] = put;
            } else {
                size--;
                out = table[location].getValue();
                MapEntry<K, V> put = new MapEntry(key, value);
                put.setNext(table[location]);
                table[location] = put;
            }
        }
        return out;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException(
                "Null data is not allowed here!");
        }
        if (size == 0
            || table[Math.abs(key.hashCode()) % table.length] == null) {
            throw new NoSuchElementException("Data does not exist here!");
        }
        MapEntry<K, V> remove = table[Math.abs(key.hashCode()) % table.length];
        if (!remove.getKey().equals(key)) {
            while (remove != null
                && remove.getNext() != null
                && !remove.getNext().getKey().equals(key)) {
                remove = remove.getNext();
            }
            if (remove == null || (remove.getNext() == null
                && !remove.getKey().equals(key))) {
                throw new NoSuchElementException(
                    "The element isn't in the table!");
            }
            MapEntry<K, V> temp = remove.getNext();
            if (remove.getNext() != null) {
                remove.setNext(remove.getNext().getNext());
            }
            size--;
            return temp.getValue();
        }
        table[Math.abs(key.hashCode()) % table.length]
            = table[Math.abs(key.hashCode()) % table.length].getNext();
        size--;
        return remove.getValue();
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException(
                "Null data is not allowed here!");
        }
        MapEntry<K, V> get = table[Math.abs(key.hashCode()) % table.length];
        while (get != null && !get.getKey().equals(key)) {
            get = get.getNext();
        }
        if (get == null) {
            throw new NoSuchElementException(
                "The value does not exist in the table!");
        }
        return get.getValue();
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Null data will not be found!");
        }
        MapEntry<K, V> get = table[Math.abs(key.hashCode()) % table.length];
        if (get == null) {
            return false;
        }
        while (!get.getKey().equals(key)) {
            get = get.getNext();
            if (get == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        table = new MapEntry[INITIAL_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        java.util.HashSet<K> set = new java.util.HashSet();
        for (MapEntry<K, V> i : table) {
            while (i != null) {
                set.add(i.getKey());
                i = i.getNext();
            }
        }
        return set;
    }

    @Override
    public List<V> values() {
        java.util.ArrayList<V> set = new java.util.ArrayList();
        for (MapEntry<K, V> i : table) {
            while (i != null) {
                set.add(i.getValue());
                i = i.getNext();
            }
        }
        return set;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length < size) {
            throw new IllegalArgumentException(
                "Resize length cannot be that small!");
        }
        //resize
        HashMap<K, V> temp = new HashMap(length);
        //rehash
        for (MapEntry<K, V> i : this.table) {
            if (i != null) {
                temp.put(i.getKey(), i.getValue());
                while (i.getNext() != null) {
                    i = i.getNext();
                    temp.put(i.getKey(), i.getValue());
                }
            }
        }
        this.table = temp.getTable();

    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
