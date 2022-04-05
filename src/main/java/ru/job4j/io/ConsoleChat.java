package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 6. Кодировка.
 * кодировка - это однозначное соответствие между подмножеством целых чисел (как правило идущих подряд) и некоторым набором символов.
 * Определяющим для любой кодировки является количество охватываемых ею кодов и, соответственно, символов.
 * Windows-1251 (одной из основоположниц кодирования является кодировка ASCII), UTF-8 (используется в Inteliji IDEA), ISO-8859-1 (не имеют числового представления).
 * Нужно использовать конструктор FileReader, в котором можно указать какую кодировку для чтения/записи.
 * Для передачи экземпляра Charset (он определен в пакете java.nio.charset), можно использовать следующую конструкцию:
 * new FileReader(path, Charset.forName("WINDOWS-1251")) - для чтения
 * new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true)) для записи
 *
 *  Задача:
 *  Необходимо создать программу 'Консольный чат'
 *  класс принимает в конструктор 2 параметра - имя файла, в который будет записан весь диалог между ботом и пользователем,
 *  и имя файла в котором находятся строки с ответами, которые будет использовать бот.
 */

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    /**
     * @param path - имя файла, в который будет записан весь диалог между ботом и пользователем
     * @param botAnswers имя файла в котором находятся строки с ответами, которые будет использовать бот
     */
    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * содержит логику чата:
     * - пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
     * - программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
     * - если пользователь вводит слово «продолжить», программа снова начинает отвечать.
     * - при вводе слова «закончить» программа прекращает работу.
     * - запись диалога, включая слова-команды стоп/продолжить/закончить должны быть записаны в текстовый лог.
     */
    public void run() {
        List<String> dialog = new ArrayList<>();
        List<String> answers = readPhrases();
        Scanner in = new Scanner(System.in);
        boolean isClose = true;
        while (isClose) {
            String i = in.nextLine();
            switch (i) {
                case (STOP):
                    dialog.add(i);
                    break;
                case (OUT):
                    dialog.add(i);
                    isClose = false;
                    break;
                default:
                    if (dialog.size() == 0 || !STOP.equals(dialog.get(dialog.size() - 1)) || CONTINUE.equals(i)) {
                        dialog.add(i);
                        Random rand = new Random();
                        String answer = answers.get(rand.nextInt(answers.size()));
                        dialog.add(answer);
                        System.out.println(answer);
                    }
                    break;
            }

        }
        saveLog(dialog);
    }

    /**
     * читает фразы из файла
     */
    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * сохраняет лог чата в файл
     */
    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./chat.txt", "./answers.txt");
        cc.run();
    }
}