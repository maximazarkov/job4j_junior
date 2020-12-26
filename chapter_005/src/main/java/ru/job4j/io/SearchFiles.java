package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles implements FileVisitor<Path> {

    // и тут промахи замечены. По непонятным причинам не решился инициировать объект
    private final List<Path> paths = new LinkedList<>();

    // т.к. мы расширили возможности нашего поисковика, пробросив отложенное условие проверки
    // прямод до самого последнего метода, то ext нам уже не нужен
    private Predicate<Path> condition;

    // конечно же получаем условия проверки файлов и сохраняем для работы
    SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        // и везде в методе я возвращал null!
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        // тут без комментарием...
        // одно хочу заменить, что вызов visitFile(...) происходит в результате работы цикла
        // в методе Files.walkFileTree(root, searcher) - см.метод search(...)
        // здесь только исполняем отложенное задание, переданное в предикате
        if (condition.test(file)) {
            paths.add(file);
        }
        // и тут null!
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        // и опять null!
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        // и снова null! КАРЛ!!!
        return FileVisitResult.CONTINUE;
    }

}
