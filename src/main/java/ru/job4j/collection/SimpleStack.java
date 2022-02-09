package ru.job4j.collection;

/**
 * 4. Используя контейнер на базе связанного списка создать контейнер Stack
 * Stack - примитивная структура данных, лучше всего реализовать на базе связанного списка.
 * Связанный список умеет быстро вставлять данные и удалять.
 * При реализации стека на основе списка используются операции добавления и удаления с одного и того же конца.
 */

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    /** Возвращает значение и удаляет его из коллекции */
    public T pop() {
        return linked.deleteFirst();
    }
    /** Помещает значение в коллекцию */
    public void push(T value) {
        linked.addFirst(value);
    }
}