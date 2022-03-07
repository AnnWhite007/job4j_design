package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * 1. Читаем файл конфигурациию.
 * Файл настроек должен содержать пары ключ-знание. Пары должны быть разделены символом равно.
 * 1. Комментарии начинаются с #, а  не с //
 * 2. Файл имеет расширение .properties
 *
 * Задание.
 * Реализуйте метод load() по аналогии с методом toString(). Метод load должен загружать пару ключ-значение в Map values.
 * Реализуйте метод value(String key) он должен возвращать значение ключа.
 */

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }
    /**
     * Метод должен считать все ключи в карту values.
     * Важно в файле могут быть пустые строки и комментарии их нужно пропускать.
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String s;
            String[] data;
            while ((s = read.readLine()) != null) {
                if (!s.startsWith("#") && !s.isEmpty()) {
                    data = s.split("=");
                    if (data.length == 2) {
                        values.put(data[0], data[1]);
                    } else {
                        values.put(data[0], null);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Don't impl this method yet!");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}