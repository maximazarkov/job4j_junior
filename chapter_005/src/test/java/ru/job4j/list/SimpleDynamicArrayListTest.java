package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleDynamicArrayListTest {

    @Test
    public void whenAddNullThenGetNull() {
        SimpleDynamicArrayList<Integer> sdal = new SimpleDynamicArrayList<>();
        assertNull(sdal.get(sdal.getSize() - 1));
    }

    @Test
    public void whenAdd20ElementsThenAutoResize() {
        SimpleDynamicArrayList<Integer> sdal = new SimpleDynamicArrayList<>();
        for (int i = 0; i < 20; i++) {
            sdal.add(i);
        }

        assertThat(sdal.get(18), is(18));
        assertThat(sdal.getSize(), is(32));
    }

    @Test
    public void whenAddValueThenGetValue() {
        SimpleDynamicArrayList<Integer> sdal = new SimpleDynamicArrayList<>();
        sdal.add(123);
        sdal.add(321);
        assertThat(sdal.get(1), is(321));
    }

    @Test
    public void whenGetSizeThen16() {
        SimpleDynamicArrayList<Integer> sdal = new SimpleDynamicArrayList<>();
        assertThat(sdal.getSize(), is(16));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIteratorThenConcurrentModificationException()  {
        SimpleDynamicArrayList<Integer> sdal = new SimpleDynamicArrayList<>();
        sdal.add(123);
        sdal.add(321);
        Iterator<Integer> it = sdal.iterator();
        int i = 0;
        while (it.hasNext()) {
            assertThat(it.next(), is(sdal.get(i++)));
            sdal.add(555);
        }
    }
}