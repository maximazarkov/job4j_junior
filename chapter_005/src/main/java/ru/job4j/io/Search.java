package ru.job4j.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Search {
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
//        search(start, ext).forEach(System.out::println);
    }

    public static List<Path> searchLF(Path dirname, String ext) {
        File f = dirname.toFile();
        FilenameFilter ff = new OnlyExt(ext);
        return Arrays.stream(Objects.requireNonNull(f.listFiles(ff)))
                .map(File::toPath)
                .collect(Collectors.toList());
    }

    public static List<Path> searchFF(Path dirname, String ext) {
        File f = dirname.toFile();
        FilenameFilter ff = new OnlyExt(ext);
        return Arrays.stream(Objects.requireNonNull(f.list(ff)))
                .map(p -> Paths.get(p))
                .collect(Collectors.toList());
    }
// т.к. пока не могу понять
//    public static List<Path> search(Path root, String ext) throws IOException {
//        SearchFiles searcher = new SearchFiles(p -> {
//            return p.toFile()
//                    .getName
//                    .endsWith(ext);
//        });
//        Files.walkFileTree(root, searcher);
//        return searcher.getPaths();
//    }
}

class OnlyExt implements FilenameFilter{
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