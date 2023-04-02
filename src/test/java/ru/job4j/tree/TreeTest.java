package ru.job4j.tree;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class TreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6).isPresent()).isTrue();
    }

    @Test
    public void whenErrorAddThenFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6).isPresent()).isTrue();
        assertThat(tree.add(5, 6)).isFalse();
        assertThat(tree.add(55, 6)).isFalse();
    }

    @Test
    public void when1ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7).isPresent()).isFalse();
    }

    @Test
    public void when1ElNotRootThenFalse() {
        Tree<Integer> tree = new Tree<>(1);
        assertThat(tree.add(2, 3)).isFalse();
    }

    @Test
    public void when1ElFindLastThen1() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(1).isPresent()).isTrue();
        assertThat(tree.findBy(2).isPresent()).isTrue();
    }

    @Test
    public void whenIsBinaryTreeThenTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.isBinary()).isTrue();
    }

    @Test
    public void whenIsNotBinaryTreeThenFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(2, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(5, 6);
        assertThat(tree.isBinary()).isFalse();
    }
}