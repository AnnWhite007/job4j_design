package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 2. Создать контейнер на базе связанного списка.
 * Необходимо создать связный список с методами.
 * Использовать стандартные коллекции JDK (ArrayList, LinkedList и т.д.) запрещено.
 * Контейнер должен быть динамическим, т.е. увеличиваться по мере добавления элементов.
 */

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> start;
    private Node<E> end;
    private int size = 0;
    private int modCount = 0;

    public SimpleLinkedList() {
        end = new Node<E>(start, null, null);
        start = new Node<E>(null, null, end);
    }

    public static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        Node<E> newItem = end;
        newItem.setItem(value);
        end = new Node<E>(newItem, null, null);
        newItem.setNext(end);
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = start.getNext();
        for (int i = 0; i < index; i++) {
            rsl = rsl.getNext();
        }
        return rsl.getItem();
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> itr = new Iterator<E>() {

            private int point = 0;
            private int expectedModCount = modCount;
            Node<E> value = start.getNext();

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                Node<E> rsl = value;
                value = value.getNext();
                point++;
                return rsl.getItem();
            }
        };
        return itr;
    }
}