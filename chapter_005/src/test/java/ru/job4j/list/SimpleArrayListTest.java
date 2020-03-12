package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleArrayListTest {
    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(2), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(0), is(3));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenDeleteFirstElementsThenReturnFirstElement() {
        assertThat(list.delete(), is(3));
        assertThat(list.get(1), is(1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenDeleteAllElementsThenNull() {
        assertThat(list.delete(), is(3));
        assertThat(list.delete(), is(2));
        assertThat(list.delete(), is(1));
        assertThat(list.getSize(), is(0));
        assertNull(list.get(2));
        assertNull(list.get(1));
        assertNull(list.get(0));
        assertNull(list.delete());

    }
}