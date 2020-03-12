package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleDynamicLinkedListTest {

    @Test
    public void whenAddItemThenReturnOneItem() {
        SimpleDynamicLinkedList<Integer> sdll = new SimpleDynamicLinkedList<>();
        sdll.add(1);
        assertThat(sdll.get(0), is(1));
        assertThat(sdll.getSize(), is(1));
    }

    @Test
    public void when5AddItemThenAutoSize() {
        SimpleDynamicLinkedList<String> sdll = new SimpleDynamicLinkedList<>();
        for (int i = 0; i < 5; i++) {
            sdll.add(Integer.toString(i));
            assertThat(sdll.get(i), is(Integer.toString(i)));
            assertThat(sdll.getSize(), is(i + 1));
        }

    }

    @Test
    public void get() {
        SimpleDynamicLinkedList<Integer> sdll = new SimpleDynamicLinkedList<>();
        sdll.add(1);
        assertThat(sdll.get(0), is(1));
        assertNull((sdll.get(1)));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void iterator() {
        SimpleDynamicLinkedList<Integer> sdll = new SimpleDynamicLinkedList<>();
        sdll.add(123);
        sdll.add(321);
        Iterator<Integer> it = sdll.iterator(0);
        int i = 0;
        while (it.hasNext()) {
            assertThat(it.next(), is(sdll.get(i++)));
            sdll.add(555);
        }
    }
}