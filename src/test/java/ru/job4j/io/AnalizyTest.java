package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

/**
 * 3.0. Тестирование IO
 * Интеграционные тесты используют внешние ресурсы: файловую системы, базу данных, сеть.
 * Главный недостаток этих тестов - это медленная скорость работы по сравнению с модульными тестами.
 * Во всех операционных системах есть папка для хранения временных файлов. Расположение ее зависит от операционной системы.
 * Junit предоставляет удобный класс для работы с этой папкой.
 * Чтобы создать файлы во временной директории, нужно использовать класс org.unit.rules.TemporaryFolder.
 * После запуска теста файлы созданные через TemporaryFolder будут удалены.
 * - создаем файл и заполняем его содержимое.
 * - выполняем действие программы, а далее читаем полученный.
 * - В конце мы проверяем результаты
 */

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01" + "\n"
                    + "500 10:57:01" + "\n"
                    + "400 10:58:01" + "\n"
                    + "200 10:59:01" + "\n"
                    + "500 11:01:02" + "\n"
                    + "200 11:02:02");
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01; 10:59:01;" + "11:01:02; 11:02:02;"));
    }
}