package ru.job4j.searchfile;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * 2. Поиск файлов по критерию
 * <p>
 * 1. Создать программу для поиска файла. Все классы, относящиеся к этому заданию должны быть в отдельном пакете
 * 2. Программа должна искать данные в заданном каталоге и подкаталогах.
 * 3. Имя файла может задаваться, целиком, по маске, по регулярному выражению(не обязательно).
 * 4. Программа должна собираться в jar и запускаться через java -jar find.jar -d=c:/ -n=*.?xt -t=mask -o=log.txt
 * Ключи
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
 * -o - результат записать в файл.
 * 5. Программа должна записывать результат в файл.
 * 6. В программе должна быть валидация ключей и подсказка.
 */


public class Find {
    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Usage java -jar find.jar ROOT_FOLDER file_name type_of_search file_to_write");
        }
        ArgsCut argsCut = ArgsCut.of(args);
        Path start = Paths.get(argsCut.get("d"));
        check(argsCut, start);
        save(search(start, argsCut), Path.of(argsCut.get("o")));
    }

    public static List<Path> search(Path root, ArgsCut argsCut) throws IOException {
        Predicate<Path> condition = null;
        if ("mask".equals(argsCut.get("t"))) {
            condition = p -> p.toFile().getName().endsWith(argsCut.get("n"));
        } else if ("name".equals(argsCut.get("t"))) {
            condition = p -> p.toFile().getName().equals(argsCut.get("n"));
        } else if ("regex".equals(argsCut.get("t"))) {
            condition = p -> p.toFile().getName().matches("n");
        }
        SearchFile searcher = new SearchFile(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    static void save(List<Path> rsl, Path target) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(String.valueOf(target)))) {
            rsl.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void check(ArgsCut argsCut, Path start) {
        if (!Files.exists(start)) {
            throw new IllegalArgumentException(String.format("Not exist %s", start.toFile().getAbsoluteFile()));
        }
        if (!Files.isDirectory(start)) {
            throw new IllegalArgumentException(String.format("Not directory %s", start.toFile().getAbsoluteFile()));
        }
        if (!"mask".equals(argsCut.get("t")) && !"name".equals(argsCut.get("t")) && !"regex".equals(argsCut.get("t"))) {
            throw new IllegalArgumentException(String.format("Invalid parameter: %s", argsCut.get("t")));
        }
    }
}