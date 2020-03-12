package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;


public class SimpleDynamicArrayList<E> implements SimpleContainer<E> {

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 10;

    /**
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * контейнер
     */
    private Object[] container;
    private int modCount;

    public SimpleDynamicArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + capacity);
        }
        if (capacity > MAXIMUM_CAPACITY) {
            capacity = MAXIMUM_CAPACITY;
        }
        this.container = new Object[capacity];
    }

    public SimpleDynamicArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public void add(E value) {
        if (container.length <= modCount) {
            Object[] tmp = container;
            container = new Object[tmp.length << 1];
            System.arraycopy(container, 0, tmp, 0, tmp.length);
        }
        this.container[modCount++] = value;
    }

    public E get(int index) {
        if (index < 0 || index > container.length) {
            throw new IllegalArgumentException("Illegal index: " + index);
        }
        E e = (E) this.container[index];
        return (E) e == null ? null : e;
    }

    public int getSize() {
        return container.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int mc = modCount;
            int it = 0;
            @Override
            public boolean hasNext() {
                if (modCount != mc) {
                    throw new ConcurrentModificationException();
                }
                return  container.length > it;
            }

            @Override
            public E next() {
                return (E) container[it++];
            }
        };
    }
}
