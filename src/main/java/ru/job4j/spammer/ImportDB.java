package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 0.2. PrepareStatement
 * - используется для выполнения параметризованных SQL-запросов.
 * Можно вставлять аргументы в запрос:
 * - удобным образом, т.к. для вставки предназначены специальные методы.
 * - безопасным способом, т.е. без возможности возникновения SQL injection (это когда наш код декомпилируют и меняют его, тем самым появляется возможность потери и утечки данных).
 * Параметры, т.е. места куда будут подставляться аргументы обозначаются «?».
 * Для подстановки аргументов используются методы вида “setТип(позиция, аргумент)”.
 * Позиция аргумента считается как его порядковый номер, а не как индекс, т.е. позиции аргументов начинаются с 1.
 * Чтобы узнать произошло само обновление мы используем метод executeUpdate(), если это метод возвращает 0,
 * то значит оно не произошло, поэтому мы проверяем, что результат больше 0.
 * Методы execute(), executeUpdate() и executeQuery() интерфейса PrepareStatement не принимают никаких аргументов, в отличие от одноименных методов Statement.
 * Они выполняют указанный при создании объекта SQL-запрос с подставленными аргументами.
 * ResultSet используется вместе с try-with-resources.
 * Чтобы получить доступ к элементу записи используем метод «getТип(имя_столбца)».
 * Чтобы сдвинуть курсор используется метод next(), если он возвращает true, то сдвиг произошел и мы можем получить данные.
 * Для того чтобы получить id. Нужно при создании PrepareStatement вторым аргументом передать Statement.RETURNING_GENERATED_KEYS.
 * После как обычно выполнить запрос. Наконец, чтобы получить ключ нужно вызвать метод getGeneratedKeys().
 *
 * Задание: У нас появился клиент, который хочет создать базу данных для спамеров.
 * На рынке ему продали диск, в котором находятся txt файлы. Формат данных dump.txt
 * Клиент просит перевести эти файлы в базу данных PostgreSQL.
 * Реализуйте класс ImportDB.
 */


public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        String e;
        String[] ee;
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            while ((e = rd.readLine()) != null) {
                ee = e.split(";");
                if (ee[0] != null && ee[1] != null) {
                    users.add(new User(ee[0], ee[1]));
                }
            }
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users(name, email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app2.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "dump.txt");
        db.save(db.load());
    }
}