package ru.job4j.shildt.io;

/** Продемонстрировать применение метода System.out.write()
 * программа взята из главы 13
 */
public class WriteDemo {
    public static void main(String[] args) {
        int b;
        b = 'A';
        System.out.write(b);
        System.out.write('\n');
        System.out.println(b);
        System.out.println(System.lineSeparator());
    }
}
