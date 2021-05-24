package ru.job4j.generics;

import java.util.*;

/**
 * 5.2.1. Реализовать SimpleArray<T> [#156]
 * Необходимо сделать универсальную обертку над массивом.
 */

public class SimpleArray <T> implements Iterable<T> {
    private T[] models;
    private int size = 0;

    public SimpleArray(T[] models) {
        this.models = models;
    }

    // добавляет указанный элемент (model) в первую свободную ячейку
    public void add(T model) {
        models[size++] = model;
    }

    // заменяет указанным элементом (model) элемент, находящийся по индексу index
    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        models[index] = model;
    }

    // удаляет элемент по указанному индексу, все находящиеся справа элементы
    // при этом необходимо сдвинуть на единицу влево (в середине массива не должно быть пустых ячеек)
    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(models, index + 1, models, index, size - 1);
        size--;
    }

    // возвращает элемент, расположенный по указанному индексу
    public T get(int index) {
        Objects.checkIndex(index, size);
        return models[index];
    }

    //интерфейс Iterable<T> - метод iterator() возвращает итератор, предназначенный для обхода данной структуры
    public Iterator<T> iterator() {
        return new Itr();
    }

    class Itr implements Iterator<T> {
        private int point = 0;

        @Override
        public boolean hasNext() {
            return point < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return models[point++];
        }
    }
}