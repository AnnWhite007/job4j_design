package ru.job4j.serialization;

import java.io.*;
import java.nio.file.Files;

/**
 * 1. Что такое Сериализация?
 * Сериализация – процесс преобразования объектов в бинарный (т.е. последовательность битов) или текстовый формат.
 * Десериализация – процесс преобразования сериализованных данных в объекты, т.е. операция обратная сериализации.
 * Обычно механизм сериализации/десериализации используется для сохранения состояния программы между запусками,
 * хранения настроек, передачи данных между программами локально или по сети.
 * В Java существует стандартный механизм сериализации в бинарный формат – Java serialization,
 * из текстовых форматов наиболее популярны JSON, XML, YAML, BSON (binary JSON).
 * Только одна вещь отличается от создания нормального класса, это реализация интерфейса java.io.Serializable.
 * Интерфейс Serializable это интерфейс-маркер; в нём не задекларировано ни одного метода.
 * Но говорит сериализующему механизму, что класс может быть сериализован.
 * Для сериализации объектов в поток используется метод writeObject, для чтения из потока readObject класса ObjectOutputStream.
 * SerialVersionUID — идентификатор класса в языке Java, используемый при сериализации с использованием стадартного алгоритма.
 * Хранится как числовое значение типа long.
 * В каждый класс, реализующий интерфейс Serializable, на стадии компиляции добавляется еще одно поле — static final long serialVersionUID.
 * Это поле содержит уникальный идентификатор версии сериализованного класса.
 * Оно вычисляется по содержимому класса — полям, их порядку объявления, методам, их порядку объявления.
 * Соответственно, при любом изменении в классе это поле поменяет свое значение.
 * Это поле записывается в поток при сериализации класса. При десериализации значение этого поля сравнивается со значением такого же поля у класса,
 * загруженного в виртуальную машину. Если значения не совпадают — инициируется исключение java.io.InvalidClassException,
 * в противном случае выполняется десериализация объекта.
 * При сериализации объекта сериализуются все объекты, на которые он ссылается в своих полях, поэтому вложенные объекты тоже должны быть Serializable.
 * Для исключения полей из сериализации используется ключевое слово transient.
 * С помощью интерфейса Externalizable можно реализовать собственный алгоритм сериализации/десериализации,
 * для этого нужно переопределить два обязательных метода — writeExternal() и readExternal().
 *
 *
 *
 *
 */

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");

        /* Запись объекта во временный файл, который удалится системой */
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }

        /* Чтение объекта из файла */
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}