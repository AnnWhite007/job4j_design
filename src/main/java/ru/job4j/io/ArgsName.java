package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * 5.1. Именованные аргументы
 * Консольные программы требуют передачи разных параметров.
 * Обычно параметры передаются в формате -key=value, где key  и value соответственно ключ (по ключу будет запрошен параметр)
 * и значение (собственно сам параметр).  При обработке таких пар следует учесть, что параметр value внутри себя может содержать символы "=",
 * и это надо учесть при разбиении строки на пару ключ-значение.
 * Запуск виртуальной машины Java может происходить с ключами. Можно указать количество памяти под программу и кодировку.
 * java -Xmx=514 -encoding=UTF-8
 * Можно указывать в произвольном порядке. Можно некоторые заполнять, а некоторые пропускать.
 *
 * Задание:
 * Нужно написать программу, которая принимает массив параметров и разбивает их на пары: ключ, значение.
 */

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments.");
        }
        for (String par : args) {
            String[] cut = par.split("=", 2);
            if (!cut[0].startsWith("-")) {
                throw new IllegalArgumentException(String.format("Invalid parameter: %s", cut[0]));
            }
            if (cut[1] == "") {
                throw new IllegalArgumentException(String.format("Parameter hasn't value: %s = null", cut[0]));
            }
            values.put(cut[0].replace("-", ""), cut[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}