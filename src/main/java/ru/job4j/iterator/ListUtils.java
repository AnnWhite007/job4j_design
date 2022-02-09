package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

/**
 * 7. ListIterator
 * ListIterator - обладает fail-safe поведением, это значит, что мы можем менять коллекцию по ходу итерирования,
 * но только с помощью самого итератора.
 */

public class ListUtils {
    /** Вставляет значение перед индексом */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }
    /** Вставляет после индекса */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            i.next();
            if (i.previousIndex() == index) {
                i.add(value);
                break;
            }
        }

    }
    /** Удаляет все элементы, которые удовлетворяют предикату */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            T el = i.next();
            if (filter.test(el)) {
                i.remove();
            }
        }
    }
    /** Заменяет все элементы, которые удовлетворяют предикату */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            T el = i.next();
            if (filter.test(el)) {
                i.set(value);
            }
        }
    }
    /** Удаляет из списка те элементы, которые есть в elements */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            T el = i.next();
            if (elements.contains(el)) {
                i.remove();
            }
        }

    }

}