package ru.job4j.it;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Collections;

/**
 * 5.1.4. FlatMap для Iterator<Iterator>
 * Класс FlatMap принимает объект вложенных итераторов.
 * В классе нужно реализовать два метода: next и hasNext.
 * Метод next должен последовательно вернуть числа из вложенных итераторов.
 */

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (data.hasNext() && !cursor.hasNext()) {
            Iterator<T> nextData = data.next();
            if (nextData.hasNext()) {
                cursor = nextData;
            }
        }
        return cursor != null && cursor.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}