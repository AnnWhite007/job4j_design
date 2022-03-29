package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * 5. Валидация параметров запуска.
 * Валидация - это процесс проверки данных перед выполнением операции.
 * Блок if - это валидация аргументов. Если вы запустите эту программу без указания аргументов, то она упадет с исключением.
 * Откройте пункт Edit configuration. Добавьте путь в пункт Program agruments (параметры поиска вводим через пробел)
 * Если пользователь забыл указать параметры, то в консоли он увидит в чем причина и как ее поправить.
 * Запустить jar файл можно после сборки мавеном из терминала в IDEA, выполнив примерную команду:
 * C:\projects\job4j_design>java -jar target/Search.jar C: .txt
 *
 * Задача:
 * Программа должна запускаться с параметрами. Первый параметр - начальная папка. Второй параметр - расширение файлов,
 * которые нужно искать. Необходимо добавить валидацию данных параметров.
 * Длина массива аргументов должна  быть равно 2, это число и надо проверить.
 * В информационном сообщении надо сообщить не только о папке, но и о расширении файлов.
 * После этого надо произвести валидацию данных параметров - проверить, что путь к каталогу существует
 * и что существует сам каталог, а расширение начинается с символа ".".
 * Только после валидации параметры можно использовать по назначению.
 */


public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage java -jar dir.jar ROOT_FOLDER  .file_extension");
        }
        Path start = Paths.get(args[0]);
        check(args[1], start);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void check(String arg, Path value) {
        if (!Files.exists(value)) {
            throw new IllegalArgumentException(String.format("Not exist %s", value.toFile().getAbsoluteFile()));
        }
        if (!Files.isDirectory(value)) {
            throw new IllegalArgumentException(String.format("Not directory %s", value.toFile().getAbsoluteFile()));
        }
        if (!arg.startsWith(".")) {
            throw new IllegalArgumentException(String.format("Incorrect file extension."));
        }
    }
}