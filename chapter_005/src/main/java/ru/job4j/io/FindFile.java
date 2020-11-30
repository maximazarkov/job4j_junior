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
    private static String fileNameMask;
    private static String resultFile;
    private static String typeMask;

    private static String pathLog;

    private static StringBuilder data = new StringBuilder();

    static void parseArgs(String[] args) {
        String FORMATCOMMAND = "format: java -jar find.jar -d sourceDir -n mask -m|-f|-r -o resultFile"
                + System.lineSeparator()
                + "параметры:"
                + System.lineSeparator()
                + "-d          -ключ для указания директории (sourceDir), в которой будет производиться поиск"
                + System.lineSeparator()
                + "-m          -ключ для указания маски (mask), по которой будет производиться поиск"
                + System.lineSeparator()
                + "-m|-f|-r    -ключ для указания типа маски. искать по маске, либо -f - полное совпадение имени. -r регулярное выражение"
                + System.lineSeparator()
                + "-o          -ключ для указания имени log-файла (resultFile)";
        int countNormalKeys = 0;
        if (args.length >= 7) {
            int indexArgs = 0;
            while (indexArgs < args.length) {
                switch (args[indexArgs]) {
                    case "-d" :
                        sourceDir = args[++indexArgs];
                        countNormalKeys++;
                        break;
                    case "-n" :
                        fileNameMask = args[++indexArgs];
                        countNormalKeys++;
                        break;
                    case "-m":
                        typeMask = "mask";
                        countNormalKeys++;
                        break;
                    case "-f":
                        typeMask = "full";
                        countNormalKeys++;
                        break;
                    case "-r":
                        typeMask = "regular";
                        countNormalKeys++;
                        break;
                    case "-o":
                        resultFile = args[++indexArgs];
                        countNormalKeys++;
                        break;
                    default:
                        System.out.println("Вы ввели недопустимый параметр. Формат комманды:");
                        System.out.println(FORMATCOMMAND);
                }
                indexArgs++;
            }

        } else {
            System.out.println("Вы ввели недостаточной число параметров. Формат комманды:");
            System.out.println(FORMATCOMMAND);
            if (countNormalKeys != 4) {

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

    private void writeFileLog() {
        String pathOut = Objects.requireNonNull(Config.class.getClassLoader().getResource("")).getPath();
//        pathLog = Config
        System.out.println(pathOut);

        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(pathOut + File.separator + resultFile))) {
            out.write(String.valueOf(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getResultFindFile() {
        appendData(data, "Место поиска:", sourceDir);
        appendData(data, "Тип маски:", typeMask);
        appendData(data, "Маска поиска:", fileNameMask);
        appendData(data, "-----------------");
        appendData(data, "Результат поиска: ");
        appendData(data, "-----------------");
        appendData(data, "Всего файлов в директории: 0");
        appendData(data, "Найде файлов: 0");
    }

    public static void main(String[] args) throws IOException {
        FindFile ff = new FindFile();
        ff.parseArgs(args);
        
        ff.getResultFindFile();

        ff.writeFileLog();

    }
}
