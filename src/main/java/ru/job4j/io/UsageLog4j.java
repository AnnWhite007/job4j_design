package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1. Log4j. Логирование системы.
 * Логгирование - это процесс записи в файл полезной информации о работе программы.
 * "Посмотри в лог" - это значит открыть файл логгирования и посмотреть наличие Exception.
 * Log4j - библиотека позволяет осуществить логгирование процессов в приложении.
 * log4j.properties
 *
 * 1. Запись в консоль, файл или базу данных. Log4j позволяет записывать информацию не только в консоль или файл, но так же в базу данных или отправлять по почте.
 * log4j.appender.console=org.apache.log4j.ConsoleAppender
 * 2. Формат записи. В логах удобно получать информацию о времени выполнении в классе и строчке кода, где была сделана запись.
 * Дата: %d{ISO8601}
 * Уровень сообщения: %5p
 * Класс, метод, строчка: %c:%M:%L
 * Текст сообщения: %m%n
 * 3. Уровень логгирования. Чем критичнее сообщение, тем выше должен быть уровень сообщения.
 * log4j.rootLogger=DEBUG, console
 * ERROR - критические ошибки.
 * DEBUG - отладочная информация.
 * LOG.trace("trace message");
 *         LOG.debug("debug message");
 *         LOG.info("info message");
 *         LOG.warn("warn message");
 *         LOG.error("error message");
 *
 * 2. Simple Loggin Facade 4 Java.
 * В Java есть несколько библиотек для логгирования: Logback, log4j, System.out.println.
 * Библиотека slf4j позволяет абстрагироваться от конкретных библиотек. Это позволяет придерживаться единого стиля логгирования для проектов.
 * SLF использует шаблон проектирования - фасад. Шаблон фасад упрощает АПИ логгеров. Делает их понятными.
 *
 * 3. Slf4j - вывод переменных.
 * Оператор плюс (+) для String создает в памяти новую строку. Это плохо, потому что в памяти создаются копии строк.
 * Чтобы избежать сложение строк в slf4j используется шаблон для подстановки переменных.
 * Первый параметр метода - это шаблон. Шаблон содержит текст и отметки, которые заменяются на параметры.
 * Параметры указываются после шаблона.
 * Метки заменяются последовательно. Первая метка замениться первым параметром, вторая - вторым и так далее.
 * Если меток или параметров будет разное количество логгер проигнорирует метку или параметр.
 */

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Anna";
        int age = 30;
        byte tempreture = 36;
        short distanse = 1500;
        long count = 45852524;
        double rating = 4.5;
        float num = 50.123456f;
        boolean actual = true;
        char simbol = 'a';
        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.info("User info count : {}, rating : {}, simbol : {}, distanse : {}", count, rating, simbol, distanse);
        LOG.warn("User info temperature : {}, actual : {}, number : {}", tempreture, actual, num);
    }
}