package net.jrz.dataStructure.queue.queue;

// 队列接口
public interface Queue<E> {
    boolean add(E e);

    boolean offer(E e);

    E poll();

    E peek();

    int size();
}
