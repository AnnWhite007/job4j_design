package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Statement
 * Для исполнения операций существуют специальные интерфейсы: Statement, PrepareStatement.
 * Для выполнения запроса для объекта существуют 3 метода: execute(), executeUpdate(), executeQuery().
 * При их вызове мы должны передать в качестве аргумента SQL - запрос.
 *
 * executeUpdate() используется как для выполнения операторов управления данными (DML - операторы),
 * например INSERT, UPDATE или DELETE, так и для операторов определения структуры базы данных (DDL - операторы),
 * например CREATE TABLE, DROP TABLE. Возвращает int – количество affected строк, т.е. количество строк на которые оказал влияние запрос.
 * Для операторов, которые не манипулируют строками, таких как CREATE TABLE или DROP TABLE, возвращаемое значение executeUpdate всегда равно нулю.
 *
 * executeQuery() используется для выполнения операции SELECT и возвращает объект ResultSet, который позволяет пройтись по результатам запроса.
 *
 * execute() используется для выполнения любых команд. Возвращает true, если результатом выполнения является ResultSet (то есть был выполнен SELECT запрос),
 * или false, если результатом является int (количество изменённых строк). Получить ResultSet
 * или количество строк мы можем с помощью последующего вызова getUpdateCount() или getResultSet().
 *
 * Задание:
 * 1. Дан каркас класса. Реализовать его методы. Чтение настроек должно идти из файла *.properties
 * " https://www.postgresqltutorial.com/ "
 */

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        try {
            initConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void initAction(String command) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(command);
            System.out.println(getTableScheme(connection, "newtable"));
        }
    }

    /** Создает пустую таблицу без столбцов с указанным именем */
    public void createTable(String tableName) throws Exception {
        initAction("CREATE TABLE " + tableName + " ();");
    }

    /** Удаляет таблицу по указанному имени */
    public void dropTable(String tableName) throws Exception {
        initAction("DROP TABLE " + tableName + ";");
    }

    /** Добавляет столбец в таблицу */
    public void addColumn(String tableName, String columnName, String type) throws Exception {
        initAction("ALTER TABLE " + tableName + " ADD COLUMN " + columnName + " " + type + ";");
    }

    /** Удаляет столбец из таблицы */
    public void dropColumn(String tableName, String columnName) throws Exception {
        initAction("ALTER TABLE " + tableName + " DROP COLUMN " + columnName + ";");
    }

    /** Переименовывает столбец */
    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        initAction("ALTER TABLE " + tableName + " RENAME COLUMN " + columnName + " TO " + newColumnName + ";");
    }

    /** Вывод схемы таблицы */
    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        TableEditor tableEditor = new TableEditor(config);
        tableEditor.createTable("newtable");
        tableEditor.addColumn("newtable", "first", "int");
        tableEditor.renameColumn("newtable", "first", "second");
        tableEditor.dropColumn("newtable", "second");
        tableEditor.dropTable("newtable");
    }
}