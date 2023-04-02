package ru.job4j.generic;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Iterator;

public class SimpleArrayTest {

    @Test
    public void whenCreateStringArrayShouldReturnTheString() {
        StackArrayString simpleArray = new StackArrayString(4);
        simpleArray.add("test");
        simpleArray.add("test2");
        simpleArray.add("test3");
        simpleArray.add("test4");
        String result = simpleArray.get(0);

        assertThat(result).isEqualTo("test");
    }

    @Test
    public void whenCreateIntegerArrayShouldReturnTheInteger() {
        StackArrayInteger simpleArray = new StackArrayInteger(4);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        Integer result = simpleArray.get(3);

        assertThat(result).isEqualTo(4);
    }

    @Test
    public void whenCreateStringArrayShouldReturnArrayIndexOutOfBounds() {
        StackArrayString simpleArray = new StackArrayString(2);
        simpleArray.add("test");
        simpleArray.add("test2");
        Throwable thrown = assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> simpleArray.add("test3"));
        assertThat(simpleArray.get(0)).isEqualTo("test");
        assertThat(simpleArray.get(1)).isEqualTo("test2");
        assertThat(thrown.getMessage()).isEqualTo("Index 2 out of bounds for length 2");
        //следующая строка генерит Exception, но при установленной заглуше мы этого не видим
        thrown = assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> simpleArray.get(2));
        assertThat(thrown.getMessage()).isEqualTo("Index 2 out of bounds for length 2");
        thrown = assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> simpleArray.get(2));
        assertThat(thrown.getMessage()).isEqualTo("Index 2 out of bounds for length 2");
    }

    @Test
    public void whenSetNewStringArrayShouldReturnTheString() {
        SimpleArray<String> simpleArray = new StackArrayString(15);
        simpleArray.add("test");
        simpleArray.set(0, "New test");
        String result = simpleArray.get(0);

        assertThat(result).isEqualTo("New test");

        Throwable thrown = assertThrows(ArrayIndexOutOfBoundsException.class, () -> simpleArray.set(10, "Index out of range"));
        assertThat(thrown.getMessage()).isEqualTo("попытка обратиться за пределы массива");
    }

    @Test
    public void whenRemoveStringArray() {
        SimpleArray<String> simpleArray = new StackArrayString(4);
        simpleArray.add("test1");
        simpleArray.add("test2");
        simpleArray.add("test3");
        simpleArray.remove(1);

        String result = simpleArray.get(0);
        assertThat(result).isEqualTo("test1");

        result = simpleArray.get(1);
        assertThat(result).isEqualTo("test3");
    }

    @Test
    public void whenRemoveLastElement() {
        SimpleArray<String> simpleArray = new StackArrayString(3);
        simpleArray.add("test1");
        simpleArray.add("test2");
        simpleArray.add("test3");
        simpleArray.remove(2);

        String result = simpleArray.get(0);
        assertThat(result).isEqualTo("test1");

        result = simpleArray.get(1);
        assertThat(result).isEqualTo("test2");
    }

    @Test
    public void whenIterableStringArray() {
        SimpleArray<String> simpleArray = new StackArrayString(40);
        simpleArray.add("test");
        Iterator<String> it = simpleArray.iterator();
        String result = "";
        if (it.hasNext()) {
            result = (String) it.next();
        }

        assertThat(result).isEqualTo("test");
    }
}
