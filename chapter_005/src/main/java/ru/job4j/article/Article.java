package ru.job4j.article;

import java.util.HashMap;
import java.util.Map;

public class Article {
    public static boolean generateBy(String origin, String line) {
        String[] words = origin.split("[\\s,.!]");
        Map<String, Integer> orgnFreq = new HashMap<>();
        for (String w : words) {
            Integer n = orgnFreq.get(w);
            n = (n == null) ? 1 : ++n;
            orgnFreq.put(w, n);
        }

        words = line.split("[\\s,.!]");
        Map<String, Integer> lineFreq = new HashMap<>();
        for (String w : words) {
            Integer n = lineFreq.get(w);
            n = (n == null) ? 1 : ++n;
            lineFreq.put(w, n);
        }

        for (String w : words) {
            // в этом цикле можно декрементировать позаимствованные слова из первой мапы,
            //но я этого делать не стал. просто ловлю совпадения
            Integer o = orgnFreq.get(w);
            if (o == null) {
                return false;
            }

            Integer l = lineFreq.get(w);

            if (l > o) {
                return false;
            }
        }

        return true;
    }
}
