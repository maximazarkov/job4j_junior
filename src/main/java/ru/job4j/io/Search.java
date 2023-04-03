package ru.job4j.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Search {

    public static FilenameFilter searchFilter(final  String regex) {
        /**
         * создадим анонимный внутренний класс
         */
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }

    public static void main(String[] args) throws IOException {
        String dirname = "c:\\projects\\job4j_junior";
        String ext = "txt";
        Path start = Paths.get(dirname);
/**
*  метод по заданию
*  По итогу данную задачу я не решил даже с помощью ментора, попросил помощи разжевать его.
*  Для тех, кто читает этот код (и для себя на будущее), но не понимает, попробую разжевать
*  свои ошибки:
*  1. По непонятной причине я вбил себе в голову, что сигнатуру метода менять нельзя!!!
*  почему я это сделал, не знаю. Я не смог позволить заменить переменную ext на пресловутый
*  предикат! Крутится вокруг указанных сигнатур из задания.
*  2. Я почему-то думал, что вызов статического метода println() является достаточным условием для вывода
*  в методе forEach(...). И тем более, опять же по непонятной причине, я не позволил прописать
*  "повторно" лямбда в методе forEach(...). Хотя, если присмотреться, то это не "повторная" запись
*  лямбда - вторя лямбда уже прописывается за пределами метода search(), соответственно
*  применять ее никто и не запрещал. Не внимательность...
*  По итогу, попробую прописать то, что я понял (прежде всего для себя, а уж потов для Вас)...
*  ТУТ ВНИМАНИЕ!!! ТО ЧТО Я ПРОПИШУ - ЭТО РЕЗУЛЬТАТ РАБОТЫ МОЕГО ВОСПАЛЕННОГО МЫШЛЕНИЯ
*  НЕ ВЕРИТЬ МНЕ НА СЛОВО!!! Причем помните, это всего лишь одно решение из множества!
*  И ПОМНИТЕ, ЧТО ЗДЕСЬ УКЗАН ПРЕДИКАТ ПОИСКА ОКОНЧАНИЯ НАЗВАНИЯ ФАЙЛА - ЕГО РАСШИРЕНИЯ
*  В ИНЫХ СЛУЧАЯХ, ПРЕДИКАТ БУДЕТ ПОСТРОЕН ПО НОВОМУ и передан функции search(...)
*/
        search(start, p -> p
                /**
                 * задаем все в Path, чтобы потом преобразовать в File
                 * далее передаем File, тут просто...
                 */
                .toFile()
                /**  Не понятно, зачем */
                .getAbsolutePath()  /** Вытаскиваем абсолютный путь к файлу (директориям), и тут просто.
                /** далее String */
                .endsWith("." + ext)) /** Подтягиваем/ФИЛЬТРУЕМ файлы по их окончанию этого "абсолютного пути"
                /** в данном случае - расширения файла
                * далее boolean... тут так же просто...
                * который по сути и уходит в Predicate как результат сравнения
                * метод forEach просто выводи полученной содержимое List<Path> по завершению работы
                * метода search(...) */
            .forEach(p -> System.out.println(p.toAbsolutePath()));

        System.out.println(System.lineSeparator() +  "==========");
        System.out.println("Список директорий");
        search(start, p -> p.toFile().isDirectory())
                .forEach(p -> System.out.println(p.toAbsolutePath()));
    }

/**    Public static List<Path> search(Path root, String ext) throws IOException {}
*      Как мы видим, изменилась сигнатура метода. Взамен переменной String ext, мы получаем
*      предикат, т.е. отложенную функцию, для проверки на условие сравнения окончания название файла,
*      например, совпадения расширения.
*      Еще раз повторюсь, что практически, в предикат "вписать" новое условие поиска файлов,
*      например, поиск директорий (а почему нет, ведь директория - то же файл по сути).
*/
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
 /**      здесь создаем объект, имплементирующий интерфейс FileVisitor и пробрасываем ему
 *        тот самый предикат, т.е. то самое условие, которое нам нужно проверить
 *        пусть этот объект напряжется
*/
        SearchFiles searcher = new SearchFiles(condition);
/**         Вызовем метод (интерфейса), который реагирует на "посещение" файла.
*         другие методы нас не интересуют
*/
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}

/**
 * Реализуем интерфейс FilenameFilter
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
/**
*  Если у вас лямбда вызывают затруднения, то вы можете создать классы,
* которые реализуют Predicate<Path> и использовать их при передаче аргумента
* в SearchFiles, по идеи у вас их должно будет получить три: один для поиска
* по имени, второй по маске, третий по регулярному выражению.
 */