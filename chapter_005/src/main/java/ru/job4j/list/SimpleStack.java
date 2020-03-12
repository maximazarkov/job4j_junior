package ru.job4j.list;

import java.util.Iterator;

public class SimpleStack<T>  {
    private SimpleDynamicLinkedList<T> sdll = new SimpleDynamicLinkedList<>();

    /**
     * Метод вставляет в начало списка данные.
     * Метод сдвигает все элементы вправо, при этом новый элемент добавляется слева
     * @param data - сохраняет
     */
    public void push(T data) {
        sdll.add(data);
    }

    /**
     * метод возвращает значение и удаляет его из коллекции
     * @return
     */
    public T poll() {
        return (T) sdll.removeLast();
    }

    public boolean isEmpty() {
        return (sdll.getSize() > 0) ? false : true;
    }
}
