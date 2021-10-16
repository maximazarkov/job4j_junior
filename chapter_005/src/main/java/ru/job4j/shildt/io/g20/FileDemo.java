package ru.job4j.shildt.io.g20;

import java.io.File;

public class FileDemo {
    static void p(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        File f1 = new File("c:\\projects\\job4j_junior\\employee.dat");
        p("Имя файла: " + f1.getName());
        p("Путь: " + f1.getPath());
        p("Абсолютный путь: " + f1.getAbsolutePath());
        p("Родительский каталог: " + f1.getParent());
        p(f1.exists() ? "существует" : "не существует");
        p(f1.canWrite() ? "доступен для записи" : "не доступен для записи");
        p(f1.canRead() ? "доступен для чтения" : "не до ступен для чтения");
        p(f1.isDirectory() ? "является каталогом" : "не является каталогом");
        p(f1.isFile() ? "является обычгым файлом" : "не является обычным файлом");
        p(f1.isAbsolute() ? "является абсолютным" : "не является абсолютным");
        p("Последнее изменение файла: " + f1.lastModified());
        p("Размер: " + f1.length() + " байт.");
        p("Количество доступных байтов, доступных в на диске: " + f1.getFreeSpace() + " байт.");
        p("Размер диска: " + f1.getTotalSpace() + " байт.");
        p("Количество пригодных для употребления свободных байтов на диске: " + f1.getUsableSpace() + " байт.");
    }
}
