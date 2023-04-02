package ru.job4j.generic;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class SimpleSetTest {

    @Test
    public void whenCheckConteins() {
        SimpleSet<Integer> ss = new SimpleSet<>(3);
        ss.add(0);
        ss.add(0);
        ss.add(1);
        assertThat(ss.contains(1)).isTrue();
        assertThat(ss.contains(2)).isFalse();
    }

    @Test
    public void whenAutoReSize() {
        SimpleSet<Integer> ss = new SimpleSet<>(2);
        ss.add(0);
        ss.add(1);
        ss.add(2);
        assertThat(ss.size()).isEqualTo(12);
    }
}