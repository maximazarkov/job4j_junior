package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
                        /** На этом месте при архивации одного файла или директории, ошибки не происходит
                        // если файлов архивируется много, то программа падает с ошибкой
                        // java.io.FileNotFoundException: c:\projects\job4j_junior (Отказано в доступе)
                        // при этом запись c:\projects\job4j_junior выглядит как файл без расширения на
                        // директорию не похож. Видимо в этом проблема
                         */
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
        /** для одного файла метод pickFiles(...) работает, но если напрямую закинуть List, падает */
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

/** При архивировании одного файла проблем с созданием .zip файла не происходит, но архив создается
 * какой-то битой конфигурации. Через окно windows он открывается
 * пустым. Через 7zip открывается, но видно, что структура битая. Тем не менее удаляю
 * @Deprecated

 *когда пытаюсь заархивировать проект, то архив "./chapter_005/project.zip" создается
 * не зависимо от вариант запущенной IDEA. Под админом и без формируется ошибка:
 * java.io.FileNotFoundException: c:\projects\job4j_junior (Отказано в доступе)
 * at java.base/java.io.FileInputStream.open0(Native Method)
 * at java.base/java.io.FileInputStream.open(FileInputStream.java:213)
 * at java.base/java.io.FileInputStream.<init>(FileInputStream.java:155)
 * at java.base/java.io.FileInputStream.<init>(FileInputStream.java:110)
 * at ru.job4j.io.Zip.packFiles(Zip.java:36)
 * at ru.job4j.io.Zip.main(Zip.java:75)

 * т.е. создается архив, но при первой попытке его наполнить, сразу падает FNFE
*/
        ArgZip az = new ArgZip(args);
        az.valid();
        Path start = Paths.get(az.directory());
        List<Path> sourcePaths = search(start,
                p -> !(p.toFile()
                    .getAbsolutePath()
                    .endsWith("." + az.exclude())));
        System.out.println(sourcePaths.toString());
        new Zip().packFiles(convertListPathToFile(sourcePaths),
                new File("." + File.separator + "chapter_005" + File.separator + az.output()));
    }
}
