package ru.job4j.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueRunner {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.add("first");
        queue.add("second");
        queue.add("third");
        print(queue);
        queue.remove();
        System.out.println("State of Queue after remove");
        print(queue);
        System.out.println("ArrayBlockingQueue...");
        queue = new ArrayBlockingQueue<>(3);
        queue.offer("first");
        queue.offer("second");
        queue.offer("third");
        queue.offer("fourth");
        print(queue);
    }

    private static void print(Queue<String> q) {
        for (String string : q) {
            System.out.println(string);
        }
    }
}
