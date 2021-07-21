package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 8. Реализовать собственную структуру данных - HashMap.
 */

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int i = indexFor(hash(key.hashCode()));
        if (table[i] != null) {
                return false;
        }
        table[i] = new MapEntry(key, value);

        if (capacity * LOAD_FACTOR <= count) {
            expand();
        }
        count++;
        modCount++;
        return true;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> capacity);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> item : oldTable) {
            if (item == null) {
                table[indexFor(hash(item.key.hashCode()))] = item;
            }
        }
    }

    @Override
    public V get(K key) {
        int i = indexFor(hash(key.hashCode()));
        if (table[i] != null && key.equals(table[i].key)) {
            return table[i].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        V value = get(key);
        if (value != null) {
            int i = indexFor(hash(key.hashCode()));
            if (table[i].key.equals(key)) {
                table[i] = null;
                modCount++;
                count--;
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        Iterator<K> itr = new Iterator<K>() {

        private int point = 0;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return point < capacity && count != 0;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            while (table[point] == null && point < capacity) {
                point++;
            }
            return table[point++].key;
        }
    };
        return itr;
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}