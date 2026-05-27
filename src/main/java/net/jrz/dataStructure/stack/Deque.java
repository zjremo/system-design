package net.jrz.dataStructure.stack;

public interface Deque<E> {
    void push(E e);

    E pop();

    boolean isEmpty();

    E peek();
}
