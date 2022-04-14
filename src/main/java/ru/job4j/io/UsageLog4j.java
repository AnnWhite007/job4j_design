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
 *
 * 2. Simple Loggin Facade 4 Java.
 * В Java есть несколько библиотек для логгирования: Logback, log4j, System.out.println.
 * Библиотека slf4j позволяет абстрагироваться от конкретных библиотек. Это позволяет придерживаться единого стиля логгирования для проектов.
 * SLF использует шаблон проектирования - фасад. Шаблон фасад упрощает АПИ логгеров. Делает их понятными.
 */

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}