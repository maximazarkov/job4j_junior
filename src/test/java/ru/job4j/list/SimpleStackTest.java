package ru.job4j.list;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class SimpleStackTest {

    @Test
    public void wherePushAndPoll() {
        SimpleStack<String> sss = new SimpleStack<>();
        sss.push("one");
        sss.push("two");
        sss.push("three");
        assertThat(sss.poll()).isEqualTo("three");
        assertThat(sss.poll()).isEqualTo("two");
        assertThat(sss.poll()).isEqualTo("one");
    }
}