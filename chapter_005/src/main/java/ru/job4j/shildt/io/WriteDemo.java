package ru.job4j.shildt.io;

// продемонстировать применение метода System.out.write()
// программа взята из главы 13
public class WriteDemo {
    public static void main(String[] args) {
        int b;
        // из всех байтов символа А записывается только младший байт!!!
        b = 'A';
        // метод write() выводит символ 'A' по одному байту
        System.out.write(b);
        // и выводим перевод строки. Тут System.lineSeparator не работает)))
        System.out.write('\n');
        // для примера метод println() выведет только число
        System.out.println(b);
        //а здесь уже можно передать System.lineSeparator;
        System.out.println(System.lineSeparator());
    }
}
