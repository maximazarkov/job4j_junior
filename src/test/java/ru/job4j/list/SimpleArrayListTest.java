package ru.job4j.list;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;

public class SimpleArrayListTest {
    private SimpleArrayList<Integer> list;

    @BeforeEach
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(2)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(0)).isEqualTo(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize()).isEqualTo(3);
    }

    @Test
    public void whenDeleteFirstElementsThenReturnFirstElement() {
        assertThat(list.delete()).isEqualTo(3);
        assertThat(list.get(1)).isEqualTo(1);
    }

    @Test
    public void whenDeleteAllElementsThenNull() {
        assertThat(list.delete()).isEqualTo(3);
        assertThat(list.delete()).isEqualTo(2);
        assertThat(list.delete()).isEqualTo(1);
        assertThat(list.getSize()).isEqualTo(0);
        assertThat(list.get(2)).isNull();
        assertThat(list.get(1)).isNull();
        assertThat(list.get(0)).isNull();
        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> list.delete());
        assertThat(thrown.getMessage()).isEqualTo("Index 0 out of bounds for length 0");
    }
}