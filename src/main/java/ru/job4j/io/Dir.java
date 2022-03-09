package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * 4.0. File
 * Главным элементом файловой системы является объект java.io.File.
 * File может быть и текстовым документом и директорией.
 *
 * Напишем программу, которая проверяет, что директория projects - это директория и выведем ее содержимое.
 * Программа выводит только имя файла и его размер.
 * Размер файла измеряется методом length(), данный метод работает корректно только с файлами.
 * Для каталогов он возвращает 0, либо размер метаданных каталога, но не суммарный размер файлов в нем.
 * Чтобы узнать действительный размер каталога, следует пользоваться методом WalkFileTree класса Files.
 *
 */

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));

        for (File subfile : file.listFiles()) {

            Path path = Paths.get(subfile.getPath());
            long size = 0;
            try (Stream<Path> walk = Files.walk(path)) {
                size = walk
                        .filter(Files::isRegularFile)
                        .mapToLong(p -> {
                            try {
                                return Files.size(p);
                            } catch (IOException e) {
                                System.out.printf("Невозможно получить размер файла %s%n%s", p, e);
                                return 0L;
                            }
                        })
                        .sum();
            } catch (IOException e) {
                System.out.printf("Ошибка при подсчёте размера директории %s", e);
            }

            System.out.println(subfile.getName() + " " + size);
        }
    }
}