package ru.job4j.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Search {

    public static FilenameFilter searchFilter(final  String regex) {
        //создадим анонимный внутренний класс
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        }; // конец анонимного класса
    }

    public static void main(String[] args) throws IOException {
        String dirname = "c:\\projects\\job4j_junior";  //.
        String ext = "txt";
        Path start = Paths.get(dirname);
        //прежде чем решить задачу по заданию, проведем экперименты с более простыми вариантами
        // поиска файлов по расширению. например с помощью интерфейса FilenameFilter
        // первый костыльный вариант, который имеет сигнатуру запроса, похожую на указанную в задании
        searchFF(start, ext).forEach(System.out::println);

        //второй костыльный вариант, с применением listFiles()
        searchLF(start, ext).forEach(System.out::println);

        // метод по заданию
        search(start, ext).forEach(System.out::println);

        // альтернативный метод через Pattern
        searchPattern(start, ext).forEach(System.out::println);
    }

    // т.к. пока не могу понять
    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p
                .toFile()
                .getName()
                .endsWith(ext)
        );


        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static List<Path> searchPattern(Path root, String ext) throws IOException {
//        SearchFiles searcher = new SearchFiles(p -> {
//            Arrays.asList(p.toFile().list(
//                    new FilenameFilter() {
//                        private Pattern pattern = Pattern.compile(ext);
//                        @Override
//                        public boolean accept(File dir, String name) {
//                            return pattern.matcher(name).matches();
//                        }
//                    })
//            );
//            return false;   // не понятно, почему здесь требуется return false.
//        });

//        Files.walkFileTree(root, searcher);
//        return searcher.getPaths();
        return null;
    }

    /**
     * метод возвращает коллекцию найденных файлов с помощью метода listFiles() и интерфейса FileFilter
     * @param dirname - рабоачая директория, где производится поиск
     * @param ext - расширение
     * @return - возвращает список найденных файлов в виде коллекции
     */
    public static List<Path> searchLF(Path dirname, String ext) {
        File f = dirname.toFile();
        FilenameFilter ff = new OnlyExt(ext);
        return Arrays.stream(Objects.requireNonNull(f.listFiles(ff)))
                .map(File::toPath)
                .collect(Collectors.toList());
    }

    /**
     *  метод возвращает коллекцию найденных файлов с помощью интерфейса FileFilter
     * @param dirname - рабоачая директория, где производится поиск
     * @param ext - расширение
     * @return - возвращает список найденных файлов в виде коллекции
     */
    public static List<Path> searchFF(Path dirname, String ext) {
        File f = dirname.toFile();
        FilenameFilter ff = new OnlyExt(ext);
        return Arrays.stream(Objects.requireNonNull(f.list(ff)))
                .map(p -> Paths.get(p))
                .collect(Collectors.toList());
    }
}

/**
 * Реализцем интерфейс FilenameFilter
 */
class OnlyExt implements FilenameFilter {
    String ext;
    public OnlyExt(String ext) {
        this.ext = "." + ext;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(ext);
    }
}

//Если у вас лямбда вызывают затруднения, то вы можете создать классы,
// которые реализуют Predicate<Path> и использовать их при передаче аргумента
// в SearchFiles, по идеи у вас их должно будет получить три: один для поиска
// по имени, второй по маске, третий по регулярному выражению