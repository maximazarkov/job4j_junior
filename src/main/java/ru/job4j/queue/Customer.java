package ru.job4j.queue;

public record Customer(String name, int amount) {
    String printName() {
        return this.name;
    }
}