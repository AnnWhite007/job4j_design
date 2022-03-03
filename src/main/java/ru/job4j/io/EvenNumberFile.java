package ru.job4j.io;

import java.io.FileInputStream;

/**
 * 0.2. FileInputStream
 * java.io.FileInputStream - позволяет прочитать данные из файла.
 * Данные считываются по байтам.
 * Здесь используется конструкция try-with-resources, чтобы закрыть поток ввода.
 * Получившийся текст можно разбить на строчки через метод String.split.
 *
 * В классе нужно прочитать файл even.txt.
 * Для каждого числа проверить является ли оно четным числом. Ответ вывести на консоль.
 */

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                    text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println(line + " Четное");
                } else {
                    System.out.println(line + " Нечетное");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
