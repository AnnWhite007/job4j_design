package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.Predicate;

/**
 * 4.1. Сканирование файловой системы.
 * Файловая система представляет собой древовидную структуру. Есть алгоритмы, которые позволяет пройти по всем элементам дерева.
 * В Java есть встроенный механизм - FileVisitor (Java последовательно передает в него файлы, а программист их обрабатывает).
 * Интерфейс FileVisitor имеет 4 метода.
 * Path - это усовершенствованная модель File.
 *
 * Задание:
 * Разработайте программу Search, которая будет искать файлы только по определенному предикату.
 * Программа должна вернуть файлы с расширением js.
 */

public class SearchFiles extends SimpleFileVisitor<Path> {
    Predicate<Path> condition;
    List<String> list;
    List<Path> rsl;

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<Path> getPaths() {
        for (String s : list) {
            rsl.add(Path.of(s));
        }
        return rsl;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        List<String> lines = Files.readAllLines(file);
        for (String s: lines) {
            if (condition.test(Path.of(s))) {
                list.add(s);
            }
        }
        return FileVisitResult.CONTINUE;
    }
}
