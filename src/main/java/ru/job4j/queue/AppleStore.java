package ru.job4j.queue;

import java.util.Queue;

public class AppleStore {
    private final Queue<Customer> queue;

    private final int count;

    public AppleStore(Queue<Customer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    public String getLastHappyCustomer() {
        Customer result = null;
        for (int i = 0; i < count; i++) {
            result = queue.poll();
        }
        assert result != null;
        return result.printName();
    }

    public String getFirstUpsetCustomer() {
        Customer result = null;
        for (int i = 0; i <= count; i++) {
            result = queue.poll();
        }
        assert result != null;
        return result.printName();
    }
}