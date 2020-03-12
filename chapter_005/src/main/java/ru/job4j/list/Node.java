package ru.job4j.list;

import java.util.Iterator;

/**
 * Задан связанный список.
 *
 * class Node<T> {
 *    T value;
 *    Node<T> next;
 * }
 *
 * Node first = new Node(1);
 * Node two = new Node(2);
 * Node third = new Node(3);
 * Node four = new Node(4);
 *
 * first.next = two;
 * two.next = third;
 * third.next = four;
 * four.next = first;
 *
 * Написать алгоритм определяющий, что список содержит замыкания.
 *
 * boolean hasCycle(Node first);
 *
 * Обратите внимание, что список может быть замкнут и в середине.
 * К примеру, 3-й узел будет ссылаться на 2-й узел. Определение зацикленности необходимо
 * реализовать путем прохода по узлам, без использования коллекций.
 * @param <T>
 */
public class Node<T> {

    // напишем элементарнейший контейнер, только для примера и отработки алгоритма
    private T value;
    public Node<T> next;

    public Node(T i) {
        this.value = i;
    }

    public void next(Node<T> n) {
        this.next = n;
    }

}
