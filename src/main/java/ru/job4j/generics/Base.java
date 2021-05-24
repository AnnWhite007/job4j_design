package ru.job4j.generics;

/**
 * 5.2.2. Реализовать Store<T extends Base>
 * Необходимо реализовать контейнеры для хранения объектов.
 * Структура контейнеров будет одинаковая.
 * Все ограничения хранимых типов мы должны задать с помощью Generics.
 *
 * Все модели наследуются от базовой модели
 */
public class Base {
    private final String id;

    public Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
