package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 2.1.6.1. Создать элементарную структуру дерева
 * интерфейс описывающий наше дерево
 * Класс Node описывает узел дерева.
 * Узел содержит хранимое значение и ссылки на дочерние узлы.
 */

public interface Tree<E> {

    boolean add(E parent, E child);

    public boolean isBinary();

    Optional<Node<E>> findBy(E value);

    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return value == null ? 0 : value.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Node that = (Node) o;

            return value.equals(that.value);
        }
    }
}