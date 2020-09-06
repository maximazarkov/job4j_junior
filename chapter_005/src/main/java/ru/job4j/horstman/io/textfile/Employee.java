package ru.job4j.horstman.io.textfile;

import java.time.LocalDate;

public class Employee {
    private String name;
    private double salary;
    private int year;
    private int month;
    private int day;

    Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        LocalDate ld = LocalDate.of(year, month, day);
        return ld;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}

