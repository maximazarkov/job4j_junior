package ru.job4j.shildt.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
* В этой версии программы ShowFile4 оператор try с ресурасами применяется для автоматического закрытия файла
* Примечание: Для выполнения этого кода требуется версия JDK 7+
*
* java -classpath C:\projects\job4j_junior\chapter_005\target\classes ru.job4j.shildt.io.ShowFile4 c:\projects\job4j_junior\employee.dat
*/
public class ShowFile4 {
    public static void main(String[] args) {
        int i;

        //сначала убедимся, что имя файла указано
        if (args.length != 1) {
            System.out.println("укажите имя файла как параметр");
            return;
        }

        //ниже оператор try с ресурсами применяется сначла для открытия, а затем
        // для автоматического закрытия файла по завершении блока этого опрератора

        try (FileInputStream fin = new FileInputStream(args[0])) {
            do {
                i = fin.read();
                if (i != -1) {
                    System.out.print((char) i);
                }
            } while (i != -1);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Произошла ошибка ввода вывода");
        }
    }
}
