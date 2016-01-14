package org.baddev.learning.demos.se_basics.collections.customlinkedlist38;

import java.util.NoSuchElementException;

public class CustomLinkedList<E> {

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> first;
    private Node<E> last;
    private int size = 0;

    public void addLast(E element) {
        final Node<E> lst = last;
        Node<E> node = new Node<>(element, lst, null);
        last = node;
        if (lst == null)
            first = node;
        else
            lst.next = node;
        size++;
    }

    public void addFirst(E element) {
        final Node<E> frs = first;
        Node<E> node = new Node<>(element, null, null);
        if (last == null) {
            first = last = node;
        } else {
            first = node;
            first.next = frs;
            first.next.prev = node;
        }
        size++;
    }

    public void add(final int index, final E element) {
        if (index == size) {
            addLast(element);
        } else {
            Node<E> toShift = findNode(index);
            final Node<E> prev = toShift.prev;
            final Node<E> toInsert = new Node<>(element, prev, toShift);
            toShift.prev = toInsert;
            if (prev == null)
                first = toInsert;
            else
                prev.next = toInsert;
            size++;
        }
    }

    private Node<E> findNode(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node<E> n = null;
        if (index < (size/2)) {
            n = first;
            for (int i = 0; i < index; i++)
                n = n.next;
        } else {
            n = last;
            for (int i = size - 1; i > index; i--)
                n = n.prev;
        }
        return n;
    }

    public E get(int index) {
        return findNode(index).element;
    }

    public E getLast() {
        if (last == null)
            throw new NoSuchElementException();
        return last.element;
    }

    public E getFirst() {
        if (first == null)
            throw new NoSuchElementException();
        return first.element;
    }

    public E remove(int index) {
        Node<E> node = findNode(index);
        final E element = node.element;
        final Node<E> next = node.next;
        final Node<E> prev = node.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.element = null;
        size--;
        return element;
    }

    public E removeLast() {
        if (last == null)
            throw new NoSuchElementException();
        final Node<E> prev = last.prev;
        final E element = last.element;
        last.element = null;
        last.prev = null;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
            last = prev;
        }
        size--;
        return element;
    }

    public E removeFirst() {
        if (first == null)
            throw new NoSuchElementException();
        final E element = first.element;
        final Node<E> next = first.next;
        first.element = null;
        first.next = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> n = first;
        for (int i = 0; i < size; i++) {
            sb.append(n.element + " ");
            n = n.next;
            if (n == null)
                break;
        }

        return sb.toString();
    }
}
