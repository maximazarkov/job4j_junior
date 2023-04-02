package ru.job4j.io;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

public class TestCharsetEncoding {
    public static void main(String[] args) throws FileNotFoundException {
        String path = Objects.requireNonNull(Config.class.getClassLoader().getResource("textCharsetEncoding.txt").getFile());
        UsageEncoding encoding = new UsageEncoding();
        List<String> strings = List.of(
                "Новая строка 1",
                "Новая строка 2",
                "Новая строка 3",
                "Новая строка 4",
                "Новая строка 5"
        );
        for (String str : strings) {
            encoding.writeDateInFile(path, str);
        }

        String s = encoding.readFile(path);
        System.out.println("Данные из файла: ");
        System.out.println(s);
    }

}
