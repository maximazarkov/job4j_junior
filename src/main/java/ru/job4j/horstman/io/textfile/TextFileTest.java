package ru.job4j.horstman.io.textfile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Listing 1.1 glava 2 IO
 * @version 1.14 2016-07-11
 * @author Cay Horstman
 */
public class TextFileTest {
    public static void main(String[] args) throws IOException {

        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        /** Сохранить запись обо всех сотрудниках в файле employee.dat */
        try (PrintWriter out = new PrintWriter("employee.dat", "UTF-8")) {
            writeData(staff, out);
        }

        /** извлечь все записи в новый массив */
        try (Scanner in = new Scanner(
                new FileInputStream("employee.dat"), "UTF-8")) {
            Employee[] newStaff = readData(in);

            /** вывести вновь прочитанные записи о сотрудниках */
            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
    }

    /**
     *Записывает данные обо всех сотрудниках из массива в поток записи выводимых данных
     * @param employees массив записей о сотрудниках
     * @param out Поток записей выводимых данных
     */
    private static void writeData(Employee[] employees, PrintWriter out) throws IOException {
        /** записать количество сотрудников */
        out.println(employees.length);
        for (Employee e : employees) {
            writeEmployee(out, e);
        }
    }

    /**
     * Читает записи о сотрудниках из потока сканирования в массив
     * @param in Поток сканирования вводимых данных
     * @return Массив записей о сотрудниках
     */
    private static Employee[] readData(Scanner in) {
        /** извлечь размер массива */
        int n = in.nextInt();
        in.nextLine();

        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    /**
     * Направляет данные о сотрудниках в поток записи выводимых данных
     * @param out Поток записей выводимых данных
     * @param e - массив записей
     */
    public static void writeEmployee(PrintWriter out, Employee e) {
        out.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDay());
    }

    /**
     * Считывает данные о сотрудниках в из буферезированного потока чтения вводимых данных
     * @param in
     * @return Запись о сотруднике
     */
    public static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        LocalDate hireDate = LocalDate.parse(tokens[2]);
        int year = hireDate.getYear();
        int month = hireDate.getMonthValue();
        int day = hireDate.getDayOfMonth();
        return new Employee(name, salary, year, month, day);
    }
}
