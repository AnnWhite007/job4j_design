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
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String s;
            String start = null;
            boolean flag = true;
            while ((s = in.readLine()) != null) {
                if ((s.startsWith("4") || s.startsWith("5")) && flag == true) {
                    start = s.split(" ")[1];
                    flag = false;
                }
                if ((s.startsWith("2") || s.startsWith("3")) && flag == false) {
                    out.println(start + "; " + s.split(" ")[1] + ";");
                    flag = true;
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