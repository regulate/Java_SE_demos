package org.baddev.learning.demos.practical_tasks.cyclicqueue;

import java.util.*;

public final class CyclicQueue<E> extends AbstractQueue<E> {

    private Object[] elements;
    private int takeIdx;
    private int putIdx;
    private int count;

    public CyclicQueue(final int capacity) {
        this.elements = new Object[capacity];
    }

    public CyclicQueue(final List<E> elements) {
        this.count = elements.size();
        this.elements = elements.toArray();
    }

    @Override
    public E peek() {
        if (count == 0)
            return null;
        return elementAt(takeIdx);
    }

    @Override
    public E element() {
        if (count == 0)
            throw new NullPointerException("Nothing to get. Queue is empty");
        return elementAt(takeIdx);
    }

    private E elementAt(int index) {
        return (E) elements[index];
    }

    @Override
    public E poll() {
        if (count == 0)
            return null;
        return retrieveAndRemove(takeIdx);
    }

    @Override
    public E remove() {
        if (count == 0)
            throw new NullPointerException("Nothing to get. Queue is empty");
        return retrieveAndRemove(takeIdx);
    }

    private E retrieveAndRemove(final int index) {
        final E element = elementAt(takeIdx);
        elements[takeIdx] = null;
        takeIdx = incrementIdx(takeIdx);
        count--;
        return element;
    }

    @Override
    public boolean offer(final E e) {
        checkNotNull(e);
        if (count == elements.length) {
            return false;
        } else {
            elements[putIdx] = e;
            count++;
        }
        putIdx = incrementIdx(putIdx);
        return true;
    }

    @Override
    public boolean add(final E e) {
        if (Arrays.asList(elements).contains(e))
            return false;
        else if (offer(e))
            return true;
        else throw new IllegalStateException("Queue is full");
    }

    private void checkNotNull(final E e) {
        if (e == null) {
            throw new NullPointerException("Element is null");
        }
    }

    private int incrementIdx(int index) {
        if (++index >= elements.length)
            index = 0;
        return index;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null)
                elements[i] = null;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int cursor = takeIdx;
            private final int end = putIdx;
            private boolean isFirst = (count == elements.length);

            @Override
            public boolean hasNext() {
                if (count < 1)
                    return false;
                return isFirst || cursor != end;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException("Can't find next element");
                isFirst = false;
                E element = elementAt(cursor);
                cursor = incrementIdx(cursor);
                return element;
            }
        };
    }
}
