package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.1. Итератор для двухмерного массива int[][]
 * Напишем для него итератор, который последовательно вернет элементы.
 * Цель итератора переместить указатель на нужную ячейку. Итератор не копирует элементы в новую коллекцию, а переводит указатель.
 */

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length && data[row].length == column) {
            row++;
            column = 0;
        }
        return row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}