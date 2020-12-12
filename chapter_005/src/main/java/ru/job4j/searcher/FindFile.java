package ru.job4j.searcher;

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

import ru.job4j.io.Config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static ru.job4j.io.Search.search;

public class FindFile {

    private static StringBuilder data = new StringBuilder();



    private static void appendData(StringBuilder sb, String... ad) {
        for (String d : ad) {
            sb.append(d);
            sb.append(" ");
        }
        sb.append(System.lineSeparator());
    }

    private static void writeFileLog(ParseArgs pArgs) {
        String pathOut = Objects.requireNonNull(Config.class.getClassLoader().getResource("")).getPath();
//        pathLog = Config
        System.out.println(pathOut);

        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(pathOut + File.separator + pArgs.resultFile()))) {
            out.write(String.valueOf(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getResultFindFile(ParseArgs pa, List<Path> filesList) {
        appendData(data, "Место поиска:", pa.sourceDir());
        appendData(data, "Тип маски:", pa.typeMask());
        appendData(data, "Маска поиска:", pa.fileNameMask());
        appendData(data, "-----------------");
        appendData(data, "Результат поиска: ");
        for (Path path : filesList) {
            appendData(data, path.toString());
        }
        appendData(data, "-----------------");
        appendData(data, "Найдено файлов: " + filesList.size());
    }

    private static List<Path> findFile(ParseArgs pArgs, Predicate<Path> searchRules) throws IOException {
        LinkedList<Path> fileList = new LinkedList<>();
        //работает!
            search(Paths.get(pArgs.sourceDir()), searchRules).forEach(p -> fileList.add(p.toAbsolutePath()));
        return fileList;
    }

    public static void main(String[] args) throws IOException {
//        FindFile ff = new FindFile();
        ParseArgs pArgs = new ParseArgs();

        pArgs.parseArgs(args);
        Predicate<Path> searchRules = new FindPredicateFactory().checksEqualityOfNames(pArgs.typeMask(), pArgs.fileNameMask());

        getResultFindFile(pArgs, findFile(pArgs, searchRules));

        writeFileLog(pArgs);
    }
}
