package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;

/**
 * 2. Анализ доступности сервера.
 * Задача преобразования одного файла в другой.
 * Сервер не работал, если status = 400 или 500.
 * Диапазоном считается последовательность статусов 400 и 500
 */


public class Analizy {
    /**
     * Метод unavailable() должен находить диапазоны, когда сервер не работал.
     * @param source - путь к файлу лога
     * @param target - имя путь к файлу результата анализа
     */
    public static void unavailable(String source, String target) {
        String start;
        String finish;
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String s;
            ArrayList<String> array = new ArrayList<>();
            while ((s = in.readLine()) != null) {
                array.add(s);
            }
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i).startsWith("4") || array.get(i).startsWith("5")) {
                    start = array.get(i).split(" ")[1];
                    while (array.get(i).startsWith("4") || array.get(i).startsWith("5")) {
                        i++;
                    }
                    finish = array.get(i).split(" ")[1];
                    out.println(start + "; " + finish + ";");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод main - записывает текст в файл "unavailable.csv"
     */
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}