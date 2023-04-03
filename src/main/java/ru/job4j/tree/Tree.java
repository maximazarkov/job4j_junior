package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Tree<E extends  Comparable<E>> implements SimpleTree<E> {
    private final Node<E> root;

    public Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        if (findBy(child).isPresent()) {
            return false;
        }

        Node<E> realParent = findByChild(parent);
        if (realParent != null) {
            realParent.children.add(new Node(child));
            return true;
        }
        return false;
    }

    public Node<E> findByChild(E parent) {
        Node<E> rsl = null;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(parent)) {
                rsl = el;
                break;
            }
            data.addAll(el.children);
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

    /**
     * Метод проверяет количество дочерних элементов в дереве. Если их <= 2 - то дерево бинарное.
     * @return Если дочерних элементов <= 2 - то дерево бинарное (True)
     */
    public boolean isBinary() {

        /** Метод должен циклически пройти по всем элементам дерева.
        * Для этого можно использовать итератор из предыдущего задания.
        */
        boolean rsl = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.children.size() > 2) {
                rsl = false;
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

}