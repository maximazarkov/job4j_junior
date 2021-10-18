package ru.job4j.shildt.io;

import java.io.PrintWriter;

/** Пример применение класса PrintWriter
 * программа взята из главы 13
 */
public class PrintWriteDemo {
    public static void main(String[] args) {
        PrintWriter pw1 = new PrintWriter(System.out, true);
        PrintWriter pw2 = new PrintWriter(System.out);

        pw1.println("Это строка1");
        pw2.println("Это строка2");
        pw2.flush();

        int i = -7;
        pw1.println(i);
        i *= -1;
        pw2.println(i);

        double d = 4.5e-7;
        pw1.println(d);
        d = -0;
        pw2.println(d);
        pw2.flush();
    }
}
/** ~~~ Output
 *Это строка1
 *Это строка2
 *-7
 *4.5E-7
 *7
 *0.0
 */
