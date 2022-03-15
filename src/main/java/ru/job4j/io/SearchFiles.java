package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

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

public class SearchFiles implements FileVisitor<Path> {
    Predicate<Path> condition;
    List<Path> rsl = new ArrayList<>();

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<Path> getPaths() {
        return rsl;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            rsl.add(file.toAbsolutePath());
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }
}
