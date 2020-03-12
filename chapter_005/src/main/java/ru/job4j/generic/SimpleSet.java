package ru.job4j.generic;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private SimpleList<E> sl;
    private int modCount = 0;
    private int count = 0;
    private int size;

    public SimpleSet(int size) {
        this.sl = new SimpleList<>(size);
        this.size = size;
    }

    public void add(E value) {
        if (count >= size) {
            this.size = size + 10;
            SimpleList<E> newSl = new SimpleList<>(size);
            Iterator<E> el = this.iterator();
            while (el.hasNext()) {
                E element = el.next();
                if (!element.equals(value)) {
                    newSl.add(element);
                }
            }
//            System.arraycopy(sl, 0, newSl, 0, size);
            this.sl = newSl;
        }
        boolean containValue = true;
        if (modCount == 0) {
            containValue = false;
        } else {
            Iterator<E> cont = this.iterator();
            while (cont.hasNext()) {
                if (!cont.next().equals(value)) {
                    containValue = false;
                }
            }
        }
        if (!containValue) {
            sl.add(value);
            modCount++;
            count++;
        }
    }

    public boolean contains(E value) {
        Iterator<E> cont = this.iterator();
        boolean result = false;
        while (cont.hasNext()) {
            if (cont.next().equals(value)) {
                result = true;
            }
        }
        return result;
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int mc = modCount;
            int it = 0;
            @Override
            public boolean hasNext() {
                if (modCount != mc) {
                    new ConcurrentModificationException();
                }
                return count > it;
            }

            @Override
            public E next() {
                return (E) sl.get(it++);
            }
        };
    }
}
