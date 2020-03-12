package ru.job4j.list;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

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

        boolean expected = true;

        assertTrue(hasCircle(first));
    }

    @Test
    public void whenHasCycleTwoToFirst() {

        Node<Integer> first = new Node(1);
        Node two = new Node(2);

        first.next = two;
        two.next = first;


        boolean expected = true;

        assertTrue(hasCircle(first));
    }

    @Test
    public void whenHasCycleThirdToTwo() {

        Node<Integer> first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);

        first.next = two;
        two.next = third;
        third.next = two;

        assertTrue(hasCircle(first));


        Node four = new Node(4);
        third.next = four;
        four.next = null;
        assertFalse(hasCircle(first));
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

        assertFalse(hasCircle(first));
    }

    private static boolean hasCircle(Node first) {
        boolean result = false;      // по умолчанию, нет
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