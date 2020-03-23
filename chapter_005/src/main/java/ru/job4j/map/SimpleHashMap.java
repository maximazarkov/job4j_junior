package ru.job4j.map;

import java.util.Objects;

public class  SimpleHashMap<K, V> {
    private K key;
    private V value;

    public SimpleHashMap() {
    }

    static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return key + " = " + value;
        }

        public void setValue(V value) {
            this.value = value;
        }


        /* пример equals() из HashMap.
         public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }*/
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || o.getClass() != getClass()) {
                return false;
            }
            SimpleHashMap<K, V> shm = (SimpleHashMap<K, V>) o;
            /* >>>>>>>>>
            прописать условие для сравнения объекта по equals() ключа и значения
             <<<<<<<<<<<*/
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    public boolean insert(K key, V value) {
        return false;
    }

    public V get(K key) {
        return null;
    }

    public boolean delete(K key) {
        return false;
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
