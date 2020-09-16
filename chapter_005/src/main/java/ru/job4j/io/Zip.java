package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
//        try (ZipOutputStream zip = new ZipOutputStream(
//                new BufferedOutputStream(
//                        new FileOutputStream(target)))) {
//            zip.putNextEntry(new ZipEntry(source.getPath()));
//            try (BufferedInputStream out = new BufferedInputStream(
//                    new FileInputStream(source))) {
//
//                zip.write(out.readAllBytes());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        new Zip().packSingleFile(
                new File("./chapter_005/pom.xml"),
//                new File("c:/projects/job4j_junior/chapter_005/pom.xml"),
                new File("./chapter_005/pom.zip")
        );
        String dirname = "c:/projects/job4j_junior";
        String ext = "";
        Path start = Paths.get(dirname);

    }
}