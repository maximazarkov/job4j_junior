package ru.job4j.iterator;

import java.util.Iterator;

public class IteratorArray implements Iterator {

    private int[] values;
    private int index = 0;

    public IteratorArray(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return values.length > index;
    }

    @Override
    public Object next() {
        return values[index++];
    }
}
