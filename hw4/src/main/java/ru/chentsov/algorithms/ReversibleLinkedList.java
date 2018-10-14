package ru.chentsov.algorithms;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

public class ReversibleLinkedList implements Iterable<Cat> {

    private class Node {
        Cat c;
        Node next;
        Node previous;

        public Node(Cat c) {
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return c.equals(node.c);
        }

        @Override
        public String toString() {
            return c.toString();
        }
    }

    private Node head;
    private Node tail;

    public ReversibleLinkedList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insertHead(Cat c) {
        Node n = new Node(c);
        n.next = head;
        if (!isEmpty()) head.previous = n;
        else tail = n;

        head = n;
    }

    public void insertTail(Cat c) {
        Node n = new Node(c);
        n.previous = tail;
        if (!isEmpty()) tail.next = n;
        else head = n;

        tail = n;
    }

    public Cat removeHead() {
        if (isEmpty()) return null;
        Cat c = head.c;
        head = head.next;
        if (!isEmpty()) head.previous = null;
        else tail = null;

        return c;
    }

    public Cat removeTail() {
        if (isEmpty()) return null;
        Cat c = tail.c;
        tail = tail.previous;
        if (tail != null) tail.next = null;
        else head = null;

        return c;
    }

    @Override
    public String toString() {
        Node current = head;
        StringBuilder sb = new StringBuilder("[ ");
        while (current != null) {
            sb.append(current);
            current = current.next;
            sb.append((current == null) ? " ]" : ", ");
        }
        return sb.toString();
    }

    public boolean contains(Cat c) {
        if (isEmpty())
            return false;
        Node current = head;
        while (!current.c.equals(c)) {
            if (current.next == null)
                return false;
            else
                current = current.next;
        }
        return true;
    }

    public boolean delete(Cat c) {
        Node n = new Node(c);
        Node current = head;
        while (!current.equals(n)) {
            if (current.next == null)
                return false;
            else {
                current = current.next;
            }
        }
        removeCurrent(current);
        return true;
    }

    private void removeCurrent(Node current) {
        if (current == head) removeHead();
        else if (current == tail) removeTail();
        else {
            current.previous.next = current.next;
            current.next.previous = current.previous;
        }
    }

    @Override
    public void forEach(Consumer<? super Cat> action) {
        this.iterator().forEachRemaining(action);
    }

    public ReversibleListIterator iterator() {
        return new ReversibleIterator();
    }

    private class ReversibleIterator implements ReversibleListIterator {

        private Node current;
        private Node next;

        private ReversibleIterator() {
            next = head;
        }

        public boolean hasNext() {
            return next != null;
        }

        public Cat next() {
            if (!hasNext()) throw new NoSuchElementException();
            current = next;
            next = next.next;
            return current.c;
        }

        public void insertBefore(Cat c) {
            if (isEmpty()) {
                insertHead(c);
                current = head;
            } else if (next == head) {
                insertHead(c);
                current = head.next;
                next = current.next;
            } else {
                Node node = new Node(c);
                current.previous.next = node;
                node.previous = current.previous.next;
                current.previous = node;
                node.next = current;
            }
        }

        public void insertAfter(Cat c) {
            if (isEmpty()) {
                insertTail(c);
                current = head;
            } else if (current == tail) {
                insertTail(c);
                current = tail.previous;
                next = tail;
            } else {
                Node node = new Node(c);
                current.next = node;
                node.previous = current;
                next.previous = node;
                node.next = next;
            }
        }

        public void remove() {
            if (current == null) throw new NoSuchElementException("Next has not been called at least once");

            removeCurrent(current);
            current = current.previous;
        }

        public void forEachRemaining(Consumer<? super Cat> action) {
            Objects.requireNonNull(action);
            while (hasNext()) {
                current = next;
                next = next.next;
                action.accept(current.c);
            }
        }

        @Override
        public void reset() {
            current = null;
            next = head;
        }
    }

}
