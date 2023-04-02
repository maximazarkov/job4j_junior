package ru.job4j.list;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class NodeTest {

    @Test
    public void whenHasCycleFourToFirst() {
        Node<Integer> first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        assertThat(hasCircle(first)).isTrue();
    }

    @Test
    public void whenHasCycleTwoToFirst() {
        Node<Integer> first = new Node(1);
        Node two = new Node(2);
        first.next = two;
        two.next = first;
        assertThat(hasCircle(first)).isTrue();
    }

    @Test
    public void whenHasCycleThirdToTwo() {
        Node<Integer> first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        first.next = two;
        two.next = third;
        third.next = two;
        assertThat(hasCircle(first)).isTrue();
        Node four = new Node(4);
        third.next = four;
        four.next = null;
        assertThat(hasCircle(first)).isFalse();
    }

    @Test
    public void whenHasCycleIsFalse() {
        Node<Integer> first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;
        assertThat(hasCircle(first)).isFalse();
    }

    private static boolean hasCircle(Node first) {
        boolean result = false;
        Node nextNode = first;
        Node nextNextNode = first;

        while (nextNextNode != null) {
            nextNode = nextNode.next;
            nextNextNode = nextNextNode.next.next;
            if (nextNextNode == nextNode) {
                result = true;
                break;
            }
        }
        return result;
    }
}