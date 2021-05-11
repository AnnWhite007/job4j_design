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
        for (int i = row; i < data.length; i++) {
            for (int j = column; j < data[i].length; j++) {
                if (data[i][j] != 0) {
                    return true;
                }
            }
        }
    return false;
    }

    @Override
    public Integer next() {
            for (int i = row; i < data.length; i++) {
                for (int j = column; j < data[i].length; j++) {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    } else {
                    if (data[i][j] != 0) {
                        if (i + 1 < data.length) {
                            row = i + 1;
                            column = j;
                        } else {
                            row = 0;
                            column = j + 1;
                        }
                        return data[i][j];
                    }
                }
            }
        }
        throw new NoSuchElementException();
    }
}