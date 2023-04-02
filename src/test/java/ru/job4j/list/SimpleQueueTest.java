package ru.job4j.list;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class SimpleQueueTest {

    @Test
    public void wherePushAndPoll() {
        SimpleQueue<String> sss = new SimpleQueue<>();
        sss.push("one");
        sss.push("two");
        sss.push("three");
        assertThat(sss.poll()).isEqualTo("one");
        assertThat(sss.poll()).isEqualTo("two");
        assertThat(sss.poll()).isEqualTo("three");
    }

    @Test
    public void wherePushAndPollInt() {
        SimpleQueue<Integer> sss = new SimpleQueue<>();
        sss.push(1);
        sss.push(2);
        sss.push(3);
        assertThat(sss.poll()).isEqualTo(1);
        assertThat(sss.poll()).isEqualTo(2);
        sss.push(4);
        sss.push(5);
        assertThat(sss.poll()).isEqualTo(3);
    }
}