package ru.job4j.searcher;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static ru.job4j.io.Search.search;

/**
 * Предикат регулярного выражения для проверки совпадения имен фалов при поиске.
 */
public class FindPredicateFactory {
    private static class RegexSearchCondition implements Predicate<Path> {

        private final Pattern pattern;

        /**
         * Создает шаблон, маску для сравнения названий файлов через регулярное выражение.
         * @param name - текст регулярного выражения.
         */
        public RegexSearchCondition(String name) {
            this.pattern = Pattern.compile(name);
        }

        /**
         * Переопределенный метод, сравнивающий через регулярное выражение имя файла, переданного через
         * параметр path.
         * @param path - путь к файлу.
         */
        @Override
        public boolean test(Path path) {
            return pattern
                    .matcher(                   //Создает Matcher, который будет сопоставлять заданные
                                                //входные данные с этим шаблоном.
                            path                //выделяем имя файла из полного пути...
                                    .toFile()
                                    .getName()
                    ).matches();                //На этом этапе пытаемся сравнить полученный Matcher с шаблоном
        }
    }


    /**
     * Создает предикат на основе переданных ключей. Может быть использован как параметр для ru.job4j.io.Search.search.
     * @return
     * @throws IOException
     */
    public Predicate<Path> checksEqualityOfNames(String typeMask, String fileNameMask) {
        Predicate<Path> result = p -> true;     // интересная лямбда, когда просто передается true...
        switch (typeMask) {
            case "full":
                result = new RegexSearchCondition(fileNameMask);
                System.out.println("FOOL");
                break;
            case "mask":
                result = path -> true;
                System.out.println("MASK");
                break;
            case "regular":
                result = p -> p.toFile().getName().matches(fileNameMask);
                System.out.println("REGULAR");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + typeMask);
        }
        return result;
    }



}
