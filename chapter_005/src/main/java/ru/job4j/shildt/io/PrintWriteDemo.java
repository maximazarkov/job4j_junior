package ru.job4j.shildt.io;

import java.io.PrintWriter;

// продемонстировать применение класса PrintWriter
// программа взята из главы 13
public class PrintWriteDemo {
    public static void main(String[] args) {
        // если убрать в какой либо строчке autoFlush, то в поток данные не попадут, но...
        PrintWriter pw1 = new PrintWriter(System.out, true);
        PrintWriter pw2 = new PrintWriter(System.out);

        //... можно подтолкнуть эти данные методом flush()
        pw1.println("Это строка1");
        pw2.println("Это строка2");
        // в этом месте мы передаем строку2 сразу в "эфир"
        pw2.flush();

        //а далее происходит следующее
        // поток pw1 передается сразу в консоль, а pw2 "задерживается"...
        int i = -7;
        pw1.println(i);
        i *= -1;
        pw2.println(i);

        double d = 4.5e-7;
        pw1.println(d);
        d = -0;
        pw2.println(d);
        //... по этому, как только мы даем команду очистки буфера, мы сразу
        // выводим данные в консоль (с запазданием получается)
        pw2.flush();
    }
} // ~~~ Output
//Это строка1
//Это строка2
//-7
//4.5E-7
//7
//0.0
