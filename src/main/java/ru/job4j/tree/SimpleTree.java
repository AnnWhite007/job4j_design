package ru.job4j.tree;

import java.util.*;

/**
 *2.1.6.1. Создать элементарную структуру дерева
 * Алгоритм обхода дерева в ширину - breadth first search
 * Каждый элемент дерева можем быть сам деревом. По заданию в дереве не могут храниться дубликаты.
 * Метод add - Должен находить узел по значению parent и добавлять в него дочерний узел со значением child.
 * В этом методе нужно проверить, что значения child еще нет в дереве а parent есть.
 * Если child есть, то метод должен вернуть false.
 */

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> dataParrent = findBy(parent);
        Optional<Node<E>> dataChild = findBy(child);
        if (!dataParrent.isEmpty() && dataChild.isEmpty()) {
            Node<E> currentParent = dataParrent.get();
            currentParent.children.add(new Node<E>(child));
            return true;
            }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}