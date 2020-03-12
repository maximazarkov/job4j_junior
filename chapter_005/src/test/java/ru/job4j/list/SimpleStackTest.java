package ru.job4j.list;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleStackTest {

    @Test
    public void wherePushAndPoll() {
        SimpleStack<String> sss = new SimpleStack<>();
        sss.push("one");
        sss.push("two");
        sss.push("three");
        assertThat(sss.poll(), is("three"));
        assertThat(sss.poll(), is("two"));
        assertThat(sss.poll(), is("one"));
    }
}