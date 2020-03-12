package ru.job4j.iterator;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IteratorArrayTest {

    public static final class ForEachArray implements Iterable {
        private final int[] values;

        public ForEachArray(int[] values) {
            this.values = values;
        }

        public Iterator iterator() {
            return new IteratorArray(this.values);
        }
    }

    @Test
    public void whenGetNextCallShouldPointerForward() {
        IteratorArray it = new IteratorArray(new int[] {1, 3});
        it.next();
        int result = (Integer) it.next();
        assertThat(result, is(3));
    }

    @Test
    public void whenCheckNextPositionReturnConstanteValue() {
        IteratorArray it = new IteratorArray(new int[] {1});
        it.next();
        it.hasNext();
        boolean result = (Boolean) it.hasNext();
        assertFalse(result);
    }

    @Test
    public void forEach() {
        ForEachArray foreach = new ForEachArray((new int[] {1, 4, 5}));

        for (Object value : foreach) {
            System.out.println(value);
        }
    }
}