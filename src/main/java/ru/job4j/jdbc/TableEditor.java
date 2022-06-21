package ru.job4j.jdbc;


import java.io.FileReader;
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
 */

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
            Class.forName(properties.getProperty("driver"));
            String url = properties.getProperty("url");
            String login = properties.getProperty("login");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url,login,password);
    }

    /** Создает пустую таблицу без столбцов с указанным именем */
    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE " + tableName);
            System.out.println(getTableScheme(connection, "newtable"));
        }
    }

    /** Удаляет таблицу по указанному имени */
    public void dropTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE " + tableName);
            System.out.println(getTableScheme(connection, "newtable"));
        }
    }
    /** Добавляет столбец в таблицу */
    public void addColumn(String tableName, String columnName, String type) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute("ALTER TABLE '" + tableName + "' ADD '" + columnName + " " + type);
            System.out.println(getTableScheme(connection, "newtable"));
        }
    }
    /** Удаляет столбец из таблицы */
    public void dropColumn(String tableName, String columnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute("ALTER TABLE " + tableName + " DROP COLUMN " + columnName);
            System.out.println(getTableScheme(connection, "newtable"));
        }
    }
    /** Переименовывает столбец */
    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute("sp_rename '" + tableName + "'.'" + columnName + "', '" + newColumnName + "' 'COLUMN'");
            System.out.println(getTableScheme(connection, "newtable"));
        }
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
        Properties properties = new Properties();
        properties.load(new FileReader("./app.properties"));
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable("newtable");
        tableEditor.addColumn("newtable", "first", "int");
        tableEditor.renameColumn("newtable", "first", "second");
        tableEditor.dropColumn("newtable", "second");
        tableEditor.dropTable("newtable");
    }
}