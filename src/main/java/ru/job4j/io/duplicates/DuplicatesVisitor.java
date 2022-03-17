package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 4.2. Поиск дубликатов
 * SimpleFileVisitor уже реализует FileVisitor, переопределяя все методы только с указанием на дальнейший обход CONTINUE.
 * Это означает, что унаследовавшись от него мы можем переопределить только нужный нам метод.
 * Сборка в JAR. Особое внимание здесь стоит уделить тегу finalName. В нем мы указываем конечное название jar файла, который появится в папке target.
 * Также же стоит обратить внимание на mainClass. В нем мы указываем, какой класс будет стартовым для нашей программы.
 * Соберем программу: mvn package. Появился file duplicatesFinder.jar
 * Запускаем: java -jar target/duplicatesFinder.jar
 *
 * Задача:
 * Нужно написать программу, которая принимает на вход папку, просматривает все файлы в ней (и всех подпапках и под-под-...папках)
 * и сообщает, если находит дубликаты. Дубликаты – это два файла с одинаковым именем и размером.
 * Например, если у вас есть один и тот же файл, который находится в разных папках таким образом:
 * d:/a/файл
 * d:/a/b/файл
 * d:/a/c/файл
 * то программа должна найти все три файла и вывести полный путь к каждому из них.
 *
 */

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    HashMap<FileProperty, List<Path>> collect = new HashMap<>();

    public void printResult() {
        for (FileProperty key : collect.keySet()) {
            if (collect.get(key).size() > 1) {
                collect.get(key).forEach(System.out::println);
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty value = new FileProperty(Files.size(file), file.getFileName().toString());
        if (!collect.containsKey(value)) {
            collect.put(value, new ArrayList<Path>());
        }
        collect.get(value).add(file);
        return super.visitFile(file, attrs);
    }
}