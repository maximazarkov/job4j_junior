package ru.job4j.searcher;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Предикат регулярного выражения для проверки совпадения имен фалов при поиске.
 */
class FindPredicateFactory {
    private static class RegexSearchCondition implements Predicate<Path> {

        private final Pattern pattern;

        /**
         * Создает шаблон, маску для сравнения названий файлов через регулярное выражение.
         * @param name - текст регулярного выражения.
         */
        RegexSearchCondition(String name) {
            this.pattern = Pattern.compile(name);
        }

        /**
         * Переопределенный метод, сравнивающий через регулярное выражение имя файла, переданного через
         * параметр path.
         * В тесте создается Matcher, который будет сопоставлять заданные входные данные с этим шаблоном.
         * выделяем имя файла из полного пути...
         * @param path - путь к файлу.
         */
        @Override
        public boolean test(Path path) {
            return pattern

                    .matcher(path
                            .toFile()
                            .getName()
                    ).matches();
        }
    }


    /**
     * Создает предикат на основе переданных ключей. Может быть использован как параметр для ru.job4j.io.Search.search.
     * @return - возвращает предикат - правило поиска, в зависимости от типа маски
     * @throws IllegalStateException - формируется исключение, при неизвестном типе маски
     */
    Predicate<Path> checksEqualityOfNames(String typeMask, String fileNameMask) {
        Predicate<Path> result;
        switch (typeMask) {
            case "full":
                result = new RegexSearchCondition(fileNameMask);
                System.out.println("FOOL");
                break;
            case "mask":
                result = new RegexSearchCondition(parseMask(fileNameMask));
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

    private String parseMask(String fileNameMask) {

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fileNameMask.length(); i++) {
            char c = fileNameMask.charAt(i);
            switch (c) {
                case '?':
                    result.append(".{1}");
                    break;
                case '*':
                    result.append(".*");
                    break;
                case '.':
                case '(':
                case ')':
                    result.append("\\");
                default:
                    result.append(c);
            }
        }

        return  result.toString();
    }
}
