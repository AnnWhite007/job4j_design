package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 0.3. BufferedReader.
 * В потоках ввода вывода есть буферизированные обертки.
 * Это наглядный пример использование шаблона декоратор. Один поток оборачивается в другой.
 * Базовый поток - это поток байтов.
 * Его можно обернуть в символьный поток, если мы знаем, что нам нужно читать текст.
 * Символьные потоки позволяют читать сразу символы, а не байты.
 * Так же у буферизированного символьного потока есть методы чтения целой строки.
 * Утечка памяти.
 * Конструкция try-with-resources закрывает только BufferedReader.
 * Когда конструкция try-with-resources вызывает метод close у BufferedReader
 * внутри метода close происходит вызов внутреннего потока, то есть потока FileReader.
 * Поэтому все ресурсы будут закрыты.
 *
 * Задание.
 * Метод filter должен прочитать файл и вернуть строки, где предпоследнее значение - это 404.
 *
 * Через while:
 * String s;
 * while ((s = in.readLine()) != null) {
 * if (s.contains(" 404 ")) {6
 * }
 * }
 * */

public class LogFilter {

    public List<String> filter(String file) {
        List<String> list = new ArrayList<String>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().
                    filter(x -> x.contains(" 404 ")).
                    forEach(х -> list.add(х));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}
