package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.2. Создать итератор четные числа
 * Создать итератор возвращающий только четные цифры.
 * Итератор должен принимать список произвольных чисел.
 * it.next() - возвращают только четные числа.
 * it.hasNext() - возвращает true, только если в массиве есть четные перед указателем.
 *
 */

public class EvenIterator implements Iterator<Integer> {
    private final int[] numbers;
    private int point = 0;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        for (int i = point; i < numbers.length; i++)
            if (numbers[i] % 2 == 0) {
                return true;
            }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            for (int i = point; i < numbers.length; i++) {
                if (numbers[i] % 2 == 0) {
                    point = i + 1;
                    return numbers[i];
                }
            }

        }
        throw new NoSuchElementException();
    }
}
