package ru.job4j.list;

import org.junit.jupiter.api.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleDynamicArrayListTest {

    @Test
    public void whenAddNullThenGetNull() {
        SimpleDynamicArrayList<Integer> sdal = new SimpleDynamicArrayList<>();
        assertThat(sdal.get(sdal.getSize() - 1)).isNull();
    }

    @Test
    public void whenAdd20ElementsThenAutoResize() {
        SimpleDynamicArrayList<Integer> sdal = new SimpleDynamicArrayList<>();
        for (int i = 0; i < 20; i++) {
            sdal.add(i);
        }

        assertThat(sdal.get(18)).isEqualTo(18);
        assertThat(sdal.getSize()).isEqualTo(32);
    }

    @Test
    public void whenAddValueThenGetValue() {
        SimpleDynamicArrayList<Integer> sdal = new SimpleDynamicArrayList<>();
        sdal.add(123);
        sdal.add(321);
        assertThat(sdal.get(1)).isEqualTo(321);
    }

    @Test
    public void whenGetSizeThen16() {
        SimpleDynamicArrayList<Integer> sdal = new SimpleDynamicArrayList<>();
        assertThat(sdal.getSize()).isEqualTo(16);
    }

    @Test
    public void whenIteratorThenConcurrentModificationException()  {
        SimpleDynamicArrayList<Integer> sdal = new SimpleDynamicArrayList<>();
        sdal.add(123);
        sdal.add(321);
        Iterator<Integer> it = sdal.iterator();
        int i = 0;
        while (it.hasNext()) {
            it.next();
            sdal.get(i++);
            sdal.add(555);
            Throwable thrown = assertThrows(ConcurrentModificationException.class, it::hasNext);
            assertThat(thrown.getMessage()).isNull();
            if (thrown.getMessage() == null) {
                break;
            }
        }
    }
}