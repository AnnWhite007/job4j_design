package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

/**
 *2.1.6.1. Создать элементарную структуру дерева
 * Алгоритм обхода дерева в ширину - breadth first search
 * Каждый элемент дерева можем быть сам деревом. По заданию в дереве не могут храниться дубликаты.
 * Метод add - Должен находить узел по значению parent и добавлять в него дочерний узел со значением child.
 * В этом методе нужно проверить, что значения child еще нет в дереве а parent есть.
 * Если child есть, то метод должен вернуть false.
 * 2.1.2. Добавить метод boolean isBinary()
 * Задача отрефакторить код, создав вспомогательный метод.
 * Этот метод уже использовать в методах isBinary() и findBy()
 */

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    /**
     * Метод должен проверять количество дочерних элементов в дереве.
     * Если их > 2 - то дерево не бинарное
     */
    @Override
    public boolean isBinary() {
        Optional<Node<E>> rsl = findByPredicate(s -> s.children.size() > 2);
        return rsl.isEmpty();
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> dataParrent = findBy(parent);
        Optional<Node<E>> dataChild = findBy(child);
        if (!dataParrent.isEmpty() && dataChild.isEmpty()) {
            Node<E> currentParent = dataParrent.get();
            currentParent.children.add(new Node<E>(child));
            rsl = true;
            }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = findByPredicate(s -> s.value.equals(value));
        return rsl;
    }
}