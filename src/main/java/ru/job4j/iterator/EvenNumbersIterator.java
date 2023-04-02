package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator, возвращающий только четные цифры
 */
public class EvenNumbersIterator implements Iterator {

    private int[] values;
    private int index = 0;

    /**
     * Constuctor
     * @param values - массив чисел
     */
    public EvenNumbersIterator(int[] values) {
        this.values = values;
    }

    /**
     * Перегруженный метод hasNext, позволяет найти следующее четное число из массива
     * @return - возвращает true, если в массиве чледующее число по индексу четное, иначе false
     */
    @Override
    public boolean hasNext() {
        boolean result = false;

        while (values.length > index) {
            if (values[index] % 2 == 0) {
                result = true;
                break;
            } else {
                index++;
            }
        }
        return result;
    }

    /**
     * Перегруженный метод next()
     * @return - позволяет получить следующее четное число из массива
     * @throws NoSuchElementException - исключение генерируется в случае отсутствия элементов
     */
    @Override
    public Object next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("There are no even numbers");
        } else {
            return values[index++];
        }
    }
}
