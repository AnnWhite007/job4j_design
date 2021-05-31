package ru.job4j.collection;

import java.util.*;

/**
 * 1. Динамический список на массиве.
 * В этом задании мы создадим реализацию ArrayList. ArrayList - это массив.
 * Когда элементов становится больше чем ячеек в массиве ArrayList создает новый массив с большим размером.
 */

public class SimpleArray<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private T[] array;

    public SimpleArray() {
        array = (T[]) new Object[2];
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return array[index];
    }

    public void add(T model) {
        if (size == array.length){
            array = Arrays.copyOf(array, array.length *2);
        }
        array[size++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> itr = new Iterator<T>() {

            private int point = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return array[point++];
            }
        };
        return itr;
    }
}