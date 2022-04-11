package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 7. Scanner
 * Используется для «сканирования» источника данных - нахождение последовательности символов среди данных источника.
 * Последовательность символов называется токеном или лексемой
 * Для выделения последовательности символов необходимо знать шаблон, по которому их нужно выделять (регулярное выражение).
 * В Scanner в качестве шаблона задается разделитель между токенами, а не сам шаблон токенов, как при работе с обычными регулярными выражениями.
 * Сам класс работает как Iterator, т.к. поддерживает данный интерфейс.
 * В качестве источника данных Scanner принимает любой вид данных.
 * Большинство методов можно разделить на hasTYPE и nextTYPE, где TYPE - это тип по шаблону которого будет
 * происходить отделение токенов друг от друга. Например, hasInt(), nextInt()
 * CharArrayReader - позволяет читать данные из массива символов, т.е. из временной памяти.
 * С помощью метода userDelimiter() указывается нужный разделитель.
 * Метод useRadix() указывает в какой системе счисления находятся входные числа.
 * Если Scanner работает с внешними источниками его нужно использовать с try-with-resources.
 * <p>
 * Задание:
 * Создать класс CSVReader. Задача класса прочитать данные из CSV файла и вывести их.
 * В качестве входных данных задается путь к файлу path, разделитель delimiter, приемник данных out и фильтр по столбцам filter.
 * Ключ -out имеет только два допустимых значения stdout или путь к файлу, куда нужно вывести.
 * Программа должна запускаться с консоли в виде jar файла как показано в примере.
 */

public class CSVReader {
    public static void handle(ArgsName argsName) {
        File in = new File(argsName.get("path"));
        if (!Files.exists(Paths.get(String.valueOf(in)))) {
            throw new IllegalArgumentException(String.format("Not exist %s", in.getAbsoluteFile()));
        }
        List<String> filter = Arrays.asList(argsName.get("filter").split(","));

        try {
            var scanner = new Scanner(in);
            String firstLine = scanner.nextLine();
            List<String> cutFirstLine = Arrays.asList(firstLine.split(argsName.get("delimiter")));
            StringBuffer sb = new StringBuffer();
            sb.append(filter.stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining(";", "", "")) + System.lineSeparator());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                List<String> values = Arrays.asList(line.split(argsName.get("delimiter")));
                for (int i = 0; i < cutFirstLine.size(); i++) {
                    if (filter.contains(cutFirstLine.get(i))) {
                        sb.append(values.get(i));
                        if (i < cutFirstLine.size() - 3) {
                            sb.append(";");
                        }
                    }
                }
                sb.append(System.lineSeparator());

            }
            if (!"stdout".equals(argsName.get("out"))) {
                try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))))) {
                    out.print(sb);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(sb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Usage java -jar dir.jar ROOT_FOLDER DELIMITER RECORDING_FOLDER FILTER");
        }
        ArgsName argsmap = ArgsName.of(args);
        handle(argsmap);
    }

}