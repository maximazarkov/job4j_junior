package ru.job4j.list;

import java.util.Objects;

/**
 * Класс SimpleArrayList.
 * В этом задании необходимо реализовать метод delete для односвязного списка, в котором каждый элемент
 * (то есть объект) содержит ссылку на следующий элемент списка.
 * Поле, в котором эта ссылка храниться, обычно называется next.
 * Объект списка содержит ссылку на первый элемент first.
 */

public class SimpleArrayList<E> {
    private int size;
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     * Метод сдвигает все элементы вправо, при этом новый элемент добавляется слева
     * @param data - сохраняет
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     * @return - возвращает удаленный элемент
     */
    public E delete() {
        Objects.checkIndex(0, size);
        E r = this.first.data;
        this.first = this.first.next;
        size--;
        return r;
    }

    /**
     * Метод получения элемента по индексу.
     * @param index - искомое значение
     * @return - найденный элемент
     */
    public E get(int index) {
        E r = null;
        if (this.first != null) {
            Node<E> result = this.first;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
            r = result.data;
        }
        return r;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }
}
