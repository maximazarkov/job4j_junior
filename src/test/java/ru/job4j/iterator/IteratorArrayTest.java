package ru.job4j.iterator;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.Iterator;

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
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void whenCheckNextPositionReturnConstanteValue() {
        IteratorArray it = new IteratorArray(new int[] {1});
        it.next();
        it.hasNext();
        boolean result = (Boolean) it.hasNext();
        assertThat(result).isFalse();
    }

    @Test
    public void forEach() {
        ForEachArray foreach = new ForEachArray((new int[] {1, 4, 5}));

        for (Object value : foreach) {
            System.out.println(value);
        }
    }
}