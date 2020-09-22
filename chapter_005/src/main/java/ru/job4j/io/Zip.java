package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.Search.search;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        Iterator<File> it = sources.iterator();
        File source;
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            while (it.hasNext()) {
                source = it.next();
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        // на этом месте при архивации одного файла или директории, ошибки не происходит
                        // если файлов архивируется много, то программа падает с ошибкой
                        // java.io.FileNotFoundException: c:\projects\job4j_junior (Отказано в доступе)
                        // при этом запись c:\projects\job4j_junior выглядит как файл без расширенияи на
                        // директорию не похож. видимо в этом проблема
                        new FileInputStream(source.getPath()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        List<File> sources = new LinkedList<>();
        sources.add(source);
        packFiles(sources, target);
    }

    public static List<File> convertListPathToFile(List<Path> in) {
        Iterator<Path> it = in.iterator();
        List<File> out = new LinkedList<>();
        while (it.hasNext()) {
            out.add(it.next().toFile());
        }
        return out;
    }

    public static void main(String[] args) throws IOException {
        new Zip().packSingleFile(
                new File("./chapter_005/pom.xml"),
                new File("./chapter_005/pom.zip")
        );
        String dirname = "c:/projects/job4j_junior";
        File target =new File("./chapter_005/project.zip");
        String ext = "class";
        Path start = Paths.get(dirname);
        List<Path> sourcePaths = search(start, p -> !(p
                .toFile()
                .getAbsolutePath()
                .endsWith("." + ext)));

        System.out.println();
        new Zip().packFiles(convertListPathToFile(sourcePaths), target);
    }
}
