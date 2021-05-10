package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1. Что такое итератор.
 * Шаблон "итератор" позволяет последовательно получить элементы набора данных.
 * Описывается интерфейсом - java.util.Iterator. Используется в коллекциях, базах данных, чтении файлов.
 * Метод hasNext проверяет, если ли следующий элемент. Многократный вызов этого метода должен быть одинаковым.
 * Метод next возвращает первый элемент ячейки. Второй вызов метода next вернет второй элемент и так далее.
 * Метод next сдвигает указатель итератора. Указатель - это ссылка на элемент, который нужно вернуть.
 * Если в итераторе нет элементов и мы вызовем метод next, итератор должен сгенерировать исключение NoSuchElementException.
 */

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[data.length - 1 - point++];
    }
}