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

    public void check () {
        while (point < numbers.length - 1) {
            if (numbers[point] % 2 == 0) {
            break;
            }
            point++;
        }
    }

    @Override
    public boolean hasNext() {
        check();
        return numbers[point] % 2 == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[point++];
    }
}
