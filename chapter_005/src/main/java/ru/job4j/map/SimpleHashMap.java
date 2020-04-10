package ru.job4j.map;

public class  SimpleHashMap<K, V> {
    private final static int DEFAULT_ARRAY_SIZE = 1 << 4; // aka 16
    private final static float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] sHashMapArray;
    private int size;
    private int count = 0;
    private float loadFactor = 0;

    public SimpleHashMap() {
         size = DEFAULT_ARRAY_SIZE;
         sHashMapArray = new Node[size];
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
        int h = hash(key);
        if (loadFactor > DEFAULT_LOAD_FACTOR) {
            Node<K, V>[] tmp = sHashMapArray;
            size = size << 1;
            sHashMapArray = new Node[size];
            int index = 0;
            while (index < tmp.length) {
                if (tmp[index] != null) {
                    h = hash(tmp[index].key);
                    sHashMapArray[h] = new Node<>(tmp[index].key, tmp[index].value);
                }
                index++;
            }
        }
        h = hash(key);
        if (sHashMapArray[h] == null) {
            sHashMapArray[h] = new Node<>(key, value);
            loadFactor = (float) ++count / size;
            result = true;
        }
        return result;
    }

    public int hash(K key) {
//        int result = 1;
//        result = (31 * result + key.hashCode()) % size;
        return key.hashCode() % size;
    }

    public V get(K key) {
        int h = hash(key);
        return (sHashMapArray[h] != null && key.equals(sHashMapArray[h].key)) ? sHashMapArray[h].value : null;
    }

    public boolean delete(K key) {
        int h = hash(key);
        boolean result = false;
        if (sHashMapArray[h] != null && key.equals(sHashMapArray[h].key)) {
            sHashMapArray[h] = null;
            count--;
            result = true;
        }
        return result;
    }
}

/*
Ассоциативный массив на базе хэш-таблицы должен быть унифицирован через генерики и иметь методы:
boolean insert(K key, V value);
V get(K key);
boolean delete(K key);

Реализовывать итератор.
Внутренняя реализация должна использовать массив. Нужно обеспечить фиксированное время вставки и получение. Предусмотрите возможность роста хэш-таблицы при нехватке места для нового элемента.

Методы разрешения коллизий реализовывать не надо. Например: если при добавлении ключ уже есть, то возвращать false.
 */
