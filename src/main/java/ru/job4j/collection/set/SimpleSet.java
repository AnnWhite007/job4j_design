package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;

/**
 * 1. Реализовать коллекцию Set на массиве
 * Коллекция не должна хранить дубликаты.
 * Для хранения использовать SimpleArray из прошлого задания.
 */

public class SimpleSet<T> implements Set<T> {

    private SimpleArray<T> set = new SimpleArray<>();

    @Override
    public boolean add(T value) {
        for (T el : set) {
            if (equals(el, value)) {
                return false;
            }
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        for (T el : set) {
            if (equals(el, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

    public static boolean equals(Object a, Object b) {
        if (a == null && b == null) {
            return true;
        } else if (a == null || b == null) {
               return false;
        } else {
                return a.equals(b);
            }
        }
}