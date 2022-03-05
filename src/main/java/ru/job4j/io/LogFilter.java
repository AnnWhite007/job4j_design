package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 0.3. BufferedReader.
 * В потоках ввода вывода есть буферизированные обертки. Это пример использование шаблона декоратор (Один поток оборачивается в другой.)
 * Базовый поток - это поток байтов. Его можно обернуть в символьный поток.
 * Так же у буферизированного символьного потока есть методы чтения целой строки.
 * Утечка памяти.
 * Конструкция try-with-resources закрывает только BufferedReader.
 * Когда конструкция try-with-resources вызывает метод close у BufferedReader
 * внутри метода close происходит вызов внутреннего потока, то есть потока FileReader. Поэтому все ресурсы будут закрыты.
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
 *
 * 0.4. BufferedOutputStream
 * Чтобы убрать задержку в выполнении программы по байтам, нужно использовать буферизированные потоки.
 * Исходный поток ввода - это файл FileOutputStream. Он записывает данные по байтам.
 * Блокирует всю программу, пока запись не закончится.
 * Первая обертка - это BufferedOutputStream. Это буфер, который собираем переданные в него байты.
 * Аккумулирует их и постепенно отдает их в FileOutputStream. В этом случае программа не блокируется до тех пока в буфере есть место.
 * Вторая обертка над буфером - это PrintWriter. Мы знаем, что будем записывать текст.
 * В Java есть удобное АПИ для этого, например, PrintWriter поддерживает метод println()
 * для записи данных с последующим переходом на новую строку. Запись можно производить целыми строками.
 *
 * Форматированный вывод
 * PrintWriter (также как и PrintStream) поддерживают форматированный вывод
 *
 * Задание.
 * Нужно добавить метод save(String log). Метод должен записывать результат фильтрации в файл.
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

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            log.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        save(log, "404.txt");
        log.forEach(System.out::println);
    }
}
