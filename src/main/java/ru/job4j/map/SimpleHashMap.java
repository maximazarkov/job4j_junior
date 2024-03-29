package ru.job4j.map;

public class  SimpleHashMap<K, V> {
    private final static int DEFAULT_ARRAY_SIZE = 1 << 4; /** aka 16 */
    private final static float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] table;
    private int size;
    private int count = 0;
    private float loadFactor = 0;

    public SimpleHashMap() {
         size = DEFAULT_ARRAY_SIZE;
         table = new Node[size];
    }

    static class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        increase(hash(key));
        int h = hash(key);
        if (table[h] == null) {
            table[h] = new Node<>(key, value);
            loadFactor = (float) ++count / size;
            result = true;
        }
        return result;
    }

    private void increase(int h) {
        if (loadFactor > DEFAULT_LOAD_FACTOR) {
            Node<K, V>[] tmp = table;
            size = size << 1;
            table = new Node[size];
            int index = 0;
            while (index < tmp.length) {
                if (tmp[index] != null) {
                    h = hash(tmp[index].key);
                    table[h] = new Node<>(tmp[index].key, tmp[index].value);
                }
                index++;
            }
        }
    }

    public int hash(K key) {
        return key.hashCode() % size;
    }

    public V get(K key) {
        int h = hash(key);
        return (table[h] != null && key.equals(table[h].key)) ? table[h].value : null;
    }

    public boolean delete(K key) {
        int h = hash(key);
        boolean result = false;
        if (table[h] != null && key.equals(table[h].key)) {
            table[h] = null;
            count--;
            result = true;
        }
        return result;
    }
}
