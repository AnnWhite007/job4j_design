package ru.job4j.searchfile;

import java.util.HashMap;
import java.util.Map;

/**
 * Принимает массив параметров и разбивает их на пары: ключ, значение.
 */

public class ArgsCut {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("Not exist parameter %s", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments.");
        }
        for (String par : args) {
            String[] cut = par.split("=", 2);
            check(cut);
            values.put(cut[0].replace("-", ""), cut[1]);
        }
    }

    public static ArgsCut of(String[] args) {
        ArgsCut names = new ArgsCut();
        names.parse(args);
        return names;
    }

    private static void check(String[] value) {
        if (!value[0].startsWith("-")) {
            throw new IllegalArgumentException(String.format("Invalid parameter: %s", value[0]));
        }
        if ("".equals(value[1])) {
            throw new IllegalArgumentException(String.format("Parameter hasn't value: %s = null", value[0]));
        }
    }
}