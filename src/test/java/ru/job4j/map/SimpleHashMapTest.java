package ru.job4j.map;

import  org.junit.jupiter.api.Test;
import  static org.assertj.core.api.Assertions.*;

public class SimpleHashMapTest {

    @Test
    public void whenOneInsertThenTrue() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        assertThat(shm.insert(1234, "Test")).isTrue();
    }

    @Test
    public void when18InsertThenTrue() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        int i = 0;
        while (i < 18) {
            boolean insertStatus = shm.insert(++i, "Test" + i);
            assertThat(insertStatus).isTrue();
            System.out.print(shm.hash(i) + " | ");
        }
        System.out.println();
        i = 0;
        while (i < 18) {
            String expected = "Test" + ++i;
            String result = shm.get(i);
            assertThat(expected).isEqualTo(result);
            System.out.print(shm.hash(i) + " | ");
        }
    }

    @Test
    public void whenTwoInsertThenFalse() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        assertThat(shm.insert(1234, "Test")).isTrue();
        assertThat(shm.insert(1234, "Test")).isFalse();
    }

    @Test
    public void whenGetThetTrue() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1234, "Test");
        String expected = "Test";
        assertThat(expected).isEqualTo(shm.get(1234));
    }

    @Test
    public void whenOneDeleteThenTrue() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1234, "Test");
        assertThat(shm.delete(1234)).isTrue();
    }

    @Test
    public void whenTwoDeleteThenFalse() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1234, "Test");
        assertThat(shm.delete(1234)).isTrue();
        assertThat(shm.delete(1234)).isFalse();
    }

}