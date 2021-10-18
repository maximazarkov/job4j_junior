package ru.job4j.shildt.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
* Отображение содержимого текстового файла в формате ASCII.
* Чтобы воспользоваться этой программой, укажите имя файла, которе требуется посмотреть.
* Например, чтобы посмотреть файл TEST.TXT, введите в командную строку
* java ShowFile TEST.TXT
*/
public class ShowFile {
    public static void main(String[] args) throws IOException {
        int i;
        FileInputStream fin = null;

        if (args.length != 1) {
            System.out.println("формат команды:");
            System.out.println("java ShowFile fileName");
            System.out.println("где,"
                    + System.lineSeparator()
                    + "fileName - имя вашего файла");
            return;
        }

        try {
            fin = new FileInputStream((args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + args[0] + " не найден");
        }

        try {
            do {
                i = fin.read();
                if (i != -1) {
                    System.out.print((char) i);
                }
            } while (i != -1);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }

        try {
            fin.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия файла");
        }
    }
}
