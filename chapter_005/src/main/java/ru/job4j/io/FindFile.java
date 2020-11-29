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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class FindFile {
    private static String sourceDir;
    private static String fileName;
    private static String resultFile;
    private static String typeMask;

    private static String pathOut;
    private static String pathLog;

    private static StringBuilder data = new StringBuilder();

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

    private static void appendData(StringBuilder sb, String... ad) {
        for (String d : ad) {
            sb.append(d);
            sb.append(" ");
        }
        sb.append(System.lineSeparator());
    }

    public static void main(String[] args) throws IOException {
        parseArgs(args);

        appendData(data, "Место поиска:", sourceDir);
        appendData(data, "Тип маски:", typeMask);
        appendData(data, "Маска поиска:", fileName);
        appendData(data, "-----------------");
        appendData(data, "Результат поиска: ");
        appendData(data, "-----------------");
        appendData(data, "Всего файлов в директории: 0");
        appendData(data, "Найде файлов: 0");

        pathOut = Objects.requireNonNull(Config.class.getClassLoader().getResource("")).getPath();
//        pathLog = Config
        System.out.println(pathOut);

        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(pathOut + File.separator + resultFile))) {
                out.write(String.valueOf(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
