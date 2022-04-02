package ru.job4j.io;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 5.2. Архивировать проект
 * Необходимо написать утилиту для архивации папки.
 * При запуске указывается папка, которую мы хотим архивировать.
 * В качестве ключа передаётся расширение файлов, которые не нужно включать в архив.
 * Архив должен сохранять структуру проекта. То есть содержать подпапки.
 * Запуск проекта: java -jar pack.jar -d=c:\project\job4j\ -e=.class -o=project.zip
 * -d - directory - которую мы хотим архивировать.
 * -e - exclude - исключить файлы с расширением class.
 * -o - output - во что мы архивируем.
 */

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        {
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
                for (Path source : sources) {
                    zip.putNextEntry(new ZipEntry(String.valueOf(source)));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(String.valueOf(source)))) {
                        zip.write(out.readAllBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param source - путь к считываемому файлу
     * @param target - документ куда архивируем (путь к файлу для записи)
     */
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );

        Zip zippack = new Zip();
        ArgsName argsmap = ArgsName.of(args);
        File in = new File(argsmap.get("d"));
        File out = new File(argsmap.get("o"));
        if (!in.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", in.getAbsoluteFile()));
        }
        if (!in.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", in.getAbsoluteFile()));
        }
        if (!argsmap.get("e").startsWith(".")) {
            throw new IllegalArgumentException(String.format("Incorrect file extension."));
        }
        List<Path> list = Search.search(Paths.get(argsmap.get("d")), p -> !p.toFile().getName().endsWith(argsmap.get("e")));
        zippack.packFiles(list, out);
    }
}