package ru.job4j.queue;

import java.util.Comparator;

public class TaskByUrgencyDesc implements Comparator<Task> {

    /**
     * Сортирует по полю urgency в обратном порядке, т.е. чем больше число, тем ближе к началу оно должно находиться.
     * @param o1 - первая заявка;
     * @param o2 - вторая заявка;
     * @return - результат сравнения по полю position.
     */
    @Override
    public int compare(Task o1, Task o2) {
        return Integer.compare(o2.urgency(), o1.urgency());
    }
}
