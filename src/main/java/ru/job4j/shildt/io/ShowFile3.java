package ru.job4j.shildt.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
* Отображение содержимого текстового файла в формате ASCII.
* Чтобы воспользоваться этой программой, укажите имя файла, которе требуется посмотреть.
* Например, чтобы посмотреть файл TEST.TXT, введите в командную строку
* java ShowFile3 TEST.TXT
*
* В этом варианте программы код, открывающий и получающий доступ к файлу, заключен в один
* блок try. Файл закрывается в блоке оператора finally.
*/
public class ShowFile3 {
    public static void main(String[] args) throws IOException {
        int i;
        FileInputStream fin = null;

        if (args.length != 1) {
            System.out.println("формат комманды:");
            System.out.println("java ShowFile3 fileName");
            System.out.println("где,"
                    + System.lineSeparator()
                    + "fileName - имая вашего файла");
            return;
        }

        try {
            fin = new FileInputStream((args[0]));
            do {
                i = fin.read();
                if (i != -1) {
                    System.out.print((char) i);
                }
            } while (i != -1);
        } catch (IOException e) {
            System.out.println("Произошла ошибка ввода-вывода" + e);
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                System.out.println("Ошибка закрытия файла");
            }
        }
    }
}
