package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC
 * – это API, т.е. набор вспомогательных классов, которое позволяет работать с базами данных.
 * Причем JDBC предоставляет единый интерфейс
 * Для работы с той или иной базой данных существует понятие драйвера.
 * Драйвер – это то, что позволяет работать с бд (поддерживать подключения, выполнять запросы и т.д.).
 * Для каждой БД есть свой драйвер. Чтобы добавить драйвер в проект необходимо добавить зависимость на этот самый драйвер.
 * Мы используем PostgreSQL, поэтому нам необходимо добавить драйвер именно для этой БД.
 * После добавления зависимости на драйвер, нам необходимо его зарегистрировать в системе "org.postgresql.Driver".
 * Теперь можно подключаться к БД. Для подключения нам нужны url, логин (имя пользователя) и пароль.
 * Чтобы получить подключение нужно воспользоваться классом DriverManager, передав ему эти аргументы.
 * В url стоит префикс “jdbc:postgres”. Это указывает, что мы подключаемся к postgres через jdbc "jdbc:postgresql://localhost:5432/idea_db".
 * Далее как обычно идет хост и порт, а за ними уже имя базы данных.
 * Для получения информации о БД и ее внутренней структуре существует класс DatabaseMetaData. Через него мы можем получить нужные данные.
 * Мы использовали Connection с try-with-resources. Вообще некоторые объекты jdbc, реализуют Autocloseable, поэтому нужно использовать их с try-with-resources.
 *
 * Задача:
 * Доработать код программы чтобы чтение драйвера, url, имени пользователя и пароля происходило из файла app.properties.
 * Для чтения используйте класс Config, который Вы писали, когда проходили блок по IO.
 */
public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config("app.properties");
        config.load();
        Class.forName(config.value("driver"));
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}