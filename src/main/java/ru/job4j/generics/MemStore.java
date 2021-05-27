package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Универсальное хранилище.
 */

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = mem.indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            mem.set(index, model);
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        int index = mem.indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            mem.remove(index);
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        int index = mem.indexOf(id);
        return index != -1 ? mem.get(index) : null;
    }
}