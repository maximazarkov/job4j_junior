package ru.job4j.shildt.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
* Копирвоание файла
* Чтобы воспользоваться этой программой укажите имена исходного и целевого файлов.
* Например, чтобы скопировать файл FIRST.TXT в файл SECOND.TXT, введите в коммандной строке следующую комманду, например:
* java -classpath C:\projects\job4j_junior\chapter_005\target\classes ru.job4j.shildt.io.CopyFile c:\projects\job4j_junior\employee.dat c:\employee.dat
*/
public class CopyFile {
    public static void main(String[] args) throws IOException {
        int i;
        FileInputStream fin = null;
        FileOutputStream fout = null;

        // проверим, что параметры - имена имен переданы программе
        if (args.length != 2) {
            System.out.println("необходимо указать названия исходного и целевого файлов.");
            return;
        }

        try {
            // попытка открыть файлы
            fin = new FileInputStream((args[0]));
            fout = new FileOutputStream(args[1]);

            do {
                i = fin.read();
                if (i != -1) {
                    fout.write(i);
                }
            } while (i != -1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e2) {
                System.out.println("Ошибка закрытия файла ввода - источник");
            }
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e2) {
                System.out.println("Ошибка закрытия файла вывода - приемник");
            }
        }
    }
}
