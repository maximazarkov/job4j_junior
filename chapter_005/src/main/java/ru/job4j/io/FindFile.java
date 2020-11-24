package ru.job4j.io;

/*
1. Создать программу для поиска файла.
2. Программа должна искать данные в заданном каталоге и подкаталогах.
3. Имя файла может задаваться, целиком, по маске, по регулярному выражение(не обязательно).
4. Программа должна собираться в jar и запускаться через java -jar find.jar -d c:/ -n *.txt -m -o log.txt
Ключи
-d - директория в которая начинать поиск.
-n - имя файл, маска, либо регулярное выражение.
-m - искать по макс, либо -f - полное совпадение имени. -r регулярное выражение.
-o - результат записать в файл.
5. Программа должна записывать результат в файл.
6. В программе должна быть валидация ключей и подсказка.
* */

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

public class FindFile {
    private static String sourceDir;
    private static String fileName;
    private static String resultFile;
    private static String typeMask;

    static void parseArgs(String[] args) {
        if (args.length < 7) {
            System.out.println("format: java -jar find.jar -d sourceDir -n mask -m|-f|-r -o resultFile");
        } else {
            int indexArgs = 0;
            while (indexArgs < args.length) {
                if (args[indexArgs].equals("-d")) {
                    sourceDir = args[++indexArgs];
                }
                if (args[indexArgs].equals("-n")) {
                    fileName = args[++indexArgs];
                }
                if (args[indexArgs].equals("-m")) {
                    typeMask = "mask";
                }
                if (args[indexArgs].equals("-f")) {
                    typeMask = "full";
                }
                if (args[indexArgs].equals("-r")) {
                    typeMask = "regular";
                }
                if (args[indexArgs].equals("-o")) {
                    resultFile = args[++indexArgs];
                }

                indexArgs++;
            }
        }
    }

    public static void main(String[] args) {
        parseArgs(args);
        System.out.println(sourceDir);
        System.out.println(fileName);
        System.out.println(typeMask);
        System.out.println(resultFile);
    }
}
