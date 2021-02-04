package ru.job4j.shildt.io;

// Продемонстрируем применение оператора assert
public class AssertDemo {
    static int val = 3;

    // возвратим целочисленное значение
    static int getnum() {
        return val--;
    }

    public static void main(String[] args) {
        int n;

        for (int i = 0; i < 10; i++) {
            n = getnum();

            assert n > 0; // не подтвердится, если n == 0
//            Можно assert написать так...
//            assert n > 0 : "n отрицательное";
//            тогда в исключении будет выведено дополнительно сообщение.
            System.out.println("n = " + n);
        }
    }
} //~~~
//Запуск программы необходимо выполнять с ключем -ea
// C:\projects\job4j_junior>java -ea -classpath .\chapter_005\target\classes ru.job4j.shildt.io.AssertDemo
//OutPut
//n = 3
//n = 2
//n = 1
//Exception in thread "main" java.lang.AssertionError
//        at ru.job4j.shildt.io.AssertDemo.main(AssertDemo.java:18)

//если прописано assert n > 0 : "n отрицательное", то будет выведено следующее сообщение
//Exception in thread "main" java.lang.AssertionError : n отрицательное!
//        at ru.job4j.shildt.io.AssertDemo.main(AssertDemo.java:18)