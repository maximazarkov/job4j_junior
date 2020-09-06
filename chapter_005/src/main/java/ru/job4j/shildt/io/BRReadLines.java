package ru.job4j.shildt.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// программа взята из главы 13
// использовать класс BufferedReader для чтения строк с консоли
public class BRReadLines {
    public static void main(String[] args) throws IOException {
        // Создать поток ввода типа BufferedReader,
        // используя стандартный поток ввода типа System.in
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        System.out.println("Введите строки текста");
        System.out.println("Введите 'стоп' для завершени");
        do {
            str = br.readLine();
            System.out.println(str);
        } while(!str.equals("стоп"));
    }
}
