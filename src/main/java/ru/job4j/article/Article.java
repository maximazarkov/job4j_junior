package ru.job4j.article;

import java.lang.reflect.Array;
import java.util.*;

public class Article {
    public static boolean generateBy(String origin, String line) {
        String[] words = origin.split("[\\s,.!]");
        HashSet<String> orgnWords = new HashSet<>(Arrays.asList(origin.split("[\\s.,!]")));
        HashSet<String> lineWords = new HashSet<>(Arrays.asList(line.split("[\\s.,!]")));

        Iterator<String> it = lineWords.iterator();

        while (it.hasNext()) {
            if (!orgnWords.contains(it.next())) {
                return false;
            }
        }

        return true;
    }
}
