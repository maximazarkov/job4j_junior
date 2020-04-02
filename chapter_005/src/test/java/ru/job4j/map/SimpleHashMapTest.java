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
    public void when18InsertThenTrue() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        int i = 0;
        while (i < 18) {
            boolean insertStatus = shm.insert((Integer) (++i), "Test" + i);
            assertTrue(insertStatus);
        }

        i = 0;
        while (i < 18) {
            String expected = "Test" + ++i;
            String result = shm.get(i);
            assertThat(expected, is(result));
        }
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