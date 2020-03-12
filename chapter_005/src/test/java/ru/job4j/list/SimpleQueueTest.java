package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {

    @Test
    public void wherePushAndPoll() {
        SimpleQueue<String> sss = new SimpleQueue<>();
        sss.push("one");
        sss.push("two");
        sss.push("three");
        assertThat(sss.poll(), is("one"));
        assertThat(sss.poll(), is("two"));
        assertThat(sss.poll(), is("three"));
    }

    @Test
    public void wherePushAndPollInt() {
        SimpleQueue<Integer> sss = new SimpleQueue<>();
        sss.push(1);
        sss.push(2);
        sss.push(3);
        assertThat(sss.poll(), is(1));
        assertThat(sss.poll(), is(2));
        sss.push(4);
        sss.push(5);
        assertThat(sss.poll(), is(3));
    }
}