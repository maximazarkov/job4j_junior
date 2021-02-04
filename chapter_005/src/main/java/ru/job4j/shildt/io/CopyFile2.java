package ru.job4j.shildt.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
* Копирвоание файла
* Версия программы CopyFile, использующая оператора try с ресурсами. Она демонстрирует  управление
* двумя ресурсами  (в данном случае - файлами) в одном операторе try/
* Например, чтобы скопировать файл FIRST.TXT в файл SECOND.TXT, введите в коммандной строке следующую комманду, например:
* java -classpath C:\projects\job4j_junior\chapter_005\target\classes ru.job4j.shildt.io.CopyFile c:\projects\job4j_junior\employee.dat c:\employee.dat
*/
public class CopyFile2 {
    public static void main(String[] args) throws IOException {
        int i;

        // проверим, что параметры - имена имен переданы программе
        if (args.length != 2) {
            System.out.println("необходимо указать названия исходного и целевого файлов.");
            return;
        }

        try (FileInputStream fin = new FileInputStream(args[0]);
                FileOutputStream fout = new FileOutputStream(args[1])) {

            do {
                i = fin.read();
                if (i != -1) {
                    fout.write(i);
                }
            } while (i != -1);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }
}
