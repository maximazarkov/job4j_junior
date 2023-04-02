package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter<T> {

    Iterator<T> convert(Iterator<Iterator<T>> iters) {
        return new Iterator<T>() {
            private Iterator<T> iterator = (new ArrayList<T>()).iterator();

            @Override
            public boolean hasNext() {
                while (iters.hasNext() && !iterator.hasNext()) {
                    iterator = iters.next();
                }
                return iterator.hasNext();
            }

            @Override
            public T next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return iterator.next();
            }
        };
    }
}
