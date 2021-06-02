package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 3. Удалить head в односвязном списке.
 * В этом задании необходимо реализовать метод delete для односвязного списка.
 * Объекты - это ссылочные типы.
 * Значит, можно создать цепочку ссылок и таким образом организовать контейнер для данных.
 * Head - всегда указывает на первый элемент.
 * Процесс удаления в такой структуре сводится к обнулению ссылки на следующий узел.
 *
 * 6. Перевернуть связанный список
 * Задан односвязный список. В нем есть метод revert. Этот метод должен переставить элементы в обратном порядке.
 */

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> node = head;
        head = head.next;
        node.next = null;
        return node.value;
    }

    public void addFirst(T value) {
        Node<T> node = new Node<T>(value, head);
        head = node;
    }
// Переставляет элементы в обратном порядке
    public boolean revert() {
        if (head == null || head.next == null) {
            return false;
        }
        Node<T> nodeX = head;
        Node<T> nodeY;
        Node<T> nodeZ = null;
        while (nodeX != null) {
            nodeY = nodeX.next;
            nodeX.next = nodeZ;
            nodeZ = nodeX;
            nodeX = nodeY;
        }
        head = nodeZ;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}