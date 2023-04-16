package ru.job4j.queue;

import java.util.Comparator;

public class TaskByPositionAsc implements Comparator<Task> {
    /**
     * Сортировка по полю position по возрастанию. Используется метод compareTo(), реализованный в Enum.
     * @param o1 - первая заявка;
     * @param o2 - вторая заявка;
     * @return - результат сравнения по полю position.
     */
    @Override
    public int compare(Task o1, Task o2) {
        return o1.position().compareTo(o2.position());
    }
}
