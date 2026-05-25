package net.jrz.dataStructure.queue.stack2queue;

import java.util.ArrayDeque;
import java.util.Deque;

// 栈模拟队列
public class Stack2Queue<E> {
    private final Deque<E> s1;
    private final Deque<E> s2;

    public Stack2Queue() {
        s1 = new ArrayDeque<>();
        s2 = new ArrayDeque<>();
    }

    public void offer(E e) {
        s1.push(e);
    }

    public E poll() {
        if (!s2.isEmpty()) {
            return s2.pop();
        }

        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }

        return s2.pop();
    }

    public E peek() {
        if (s1.isEmpty() && s2.isEmpty())
            return null;

        if (!s2.isEmpty()) {
            return s2.peek();
        }

        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        return s2.peek();
    }

    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
