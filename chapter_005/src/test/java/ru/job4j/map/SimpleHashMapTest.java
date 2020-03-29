package ru.job4j.map;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleHashMapTest {

    @Test
    public void whenOneInsertThenTrue() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        assertTrue(shm.insert(1234, "Test"));
    }

    @Test
    public void whenTwoInsertThenFalse() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        assertTrue(shm.insert(1234, "Test"));
        assertFalse(shm.insert(1234, "Test"));

    }

    @Test
    public void whenGetThetTrue() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1234, "Test");
        String expected = "Test";
        assertThat(expected, is(shm.get(1234)));

    }

    @Test
    public void whenOneDeleteThenTrue() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1234, "Test");
        assertTrue(shm.delete(1234));
    }

    @Test
    public void whenTwoDeleteThenFalse() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1234, "Test");
        assertTrue(shm.delete(1234));
        assertFalse(shm.delete(1234));
    }

}