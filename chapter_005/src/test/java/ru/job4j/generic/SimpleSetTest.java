package ru.job4j.generic;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleSetTest {

    @Test
    public void whenCheckConteins() {
        SimpleSet<Integer> ss = new SimpleSet<>(3);
        ss.add(0);
        ss.add(0);
        ss.add(1);
        assertTrue(ss.contains(1));
        assertFalse(ss.contains(2));
    }

    @Test
    public void whenAutoReSize() {
        SimpleSet<Integer> ss = new SimpleSet<>(2);
        ss.add(0);
        ss.add(1);
        ss.add(2);
        assertThat(ss.size(), is(12));
    }


}