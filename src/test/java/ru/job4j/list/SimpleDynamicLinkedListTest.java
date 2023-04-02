package ru.job4j.list;

import org.junit.jupiter.api.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleDynamicLinkedListTest {

    @Test
    public void whenAddItemThenReturnOneItem() {
        SimpleDynamicLinkedList<Integer> sdll = new SimpleDynamicLinkedList<>();
        sdll.add(1);
        assertThat(sdll.get(0)).isEqualTo(1);
        assertThat(sdll.getSize()).isEqualTo(1);
    }

    @Test
    public void when5AddItemThenAutoSize() {
        SimpleDynamicLinkedList<String> sdll = new SimpleDynamicLinkedList<>();
        for (int i = 0; i < 5; i++) {
            sdll.add(Integer.toString(i));
            assertThat(sdll.get(i)).isEqualTo(Integer.toString(i));
            assertThat(sdll.getSize()).isEqualTo(i + 1);
        }

    }

    @Test
    public void get() {
        SimpleDynamicLinkedList<Integer> sdll = new SimpleDynamicLinkedList<>();
        sdll.add(1);
        assertThat(sdll.get(0)).isEqualTo(1);
        assertThat(sdll.get(1)).isNull();
    }

    @Test
    public void iterator() {
        SimpleDynamicLinkedList<Integer> sdll = new SimpleDynamicLinkedList<>();
        sdll.add(123);
        sdll.add(321);
        Iterator<Integer> it = sdll.iterator(0);
        int sizeSdll = sdll.getSize();
        int i = 0;
        while (it.hasNext()) {
            if (sizeSdll < sdll.getSize()) {
                Throwable thrown = assertThrows(ConcurrentModificationException.class, it::next);
                assertThat(thrown.getMessage()).isNull();
                break;
            }
            assertThat(it.next())
                    .isEqualTo(sdll.get(i++));
            sdll.add(555);
        }
    }
}