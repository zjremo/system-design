package net.jrz.dataStructure.queue.queue;

import java.util.Arrays;
import java.util.Comparator;

/*
1. Constructor:
1.1 PriorityQueue();
1.2 PriorityQueue(int initialCapacity);
1.3 PriorityQueue(Comparator<E> comparator);
1.4 PriorityQueue(int initialCapacity, Comparator<E> comparator); // 最终调用

2. siftUp : 相当于heapInsert，插入元素时向上调整

3. siftDown : 相当于heapify时向下调整

4. poll : 吐出堆顶元素
 */
public class PriorityQueue<E> implements Queue<E> {

    // 默认初始化容量
    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    transient Object[] queue;

    private int size = 0;

    private final Comparator<? super E> comparator;

    public PriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY, null);
    }

    public PriorityQueue(int initialCapacity) {
        this(initialCapacity, null);
    }

    public PriorityQueue(Comparator<? super E> comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

    public PriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException("initialCapacity is illegal: " + initialCapacity);
        this.queue = new Object[initialCapacity];
        this.comparator = comparator;
    }

    /* Increases the capacity of the array. */
    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        int newCapacity = oldCapacity + ((oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1));

        if (newCapacity - MAX_ARRAY_SIZE > 0) { // 此时已经到达int最大值附近
            newCapacity = hugeCapacity(minCapacity);
        }

        queue = Arrays.copyOf(queue, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) { // 明显就是溢出了
            throw new OutOfMemoryError();
        }

        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    @Override
    public boolean add(E e) {
        return offer(e);
    }

    @Override
    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        final int i = size;
        if (i >= queue.length) { // 此时需要扩容
            grow(i + 1);
        }

        size = i + 1; // 更新size
        if (i == 0) {
            queue[0] = e; // 此时代表是添加的第一个元素，直接添加即可
        } else {
            siftUp(i, e);
        }
        return true;
    }

    private void siftUp(int k, E x) {
        // 分为两类: 1. 不传入Comparator，需要E实现Comparable接口; 2. 使用传入的Comparator
        if (comparator == null) {
            siftUpComparable(k, x);
        } else {
            siftUpUsingComparator(k, x);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftUpUsingComparator(int k, E x) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (comparator.compare(x, (E) e) >= 0) {
                break;
            }
            queue[k] = e;
            k = parent;
        }
        queue[k] = x;
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparable(int k, E x) {
        // 必须自己要实现Comparable接口
        Comparable<? super E> key = (Comparable<? super E>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E) e) >= 0) {
                break;
            }
            queue[k] = e;
            k = parent;
        }
        queue[k] = x;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E poll() {
        /*
            1. 第0个元素就是要poll出去的元素；
            2. 最后一个元素放到堆顶heapify
        */
        if (size == 0) { // 此时没有元素
            return null;
        }

        int s = --size;
        E result = (E) queue[0];
        E x = (E) queue[s];
        queue[s] = null;
        if (s != 0) {
            siftDown(0, x);
        }
        return result;
    }

    private void siftDown(int k, E x) {
        // 分为两类: 1. 使用默认Comparator; 2. 使用传入的Comparator
        if (comparator == null) {
            siftDownComparable(k, x);
        } else {
            siftDownUsingComparator(k, x);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftDownUsingComparator(int k, E x) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size && comparator.compare((E) c, (E) queue[right]) > 0)
                c = queue[child = right];
            if (comparator.compare(x, (E) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = x;
    }

    @SuppressWarnings("unchecked")
    private void siftDownComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size && ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0) {
                c = queue[child = right];
            }

            if (key.compareTo((E) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = x;
    }

    @SuppressWarnings("unchecked")
    private void heapify() {
        for (int i = (size >>> 1) - 1; i >= 0; --i) {
            siftDown(i, (E) queue[i]);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        return (size == 0) ? null : (E) queue[0];
    }

    @Override
    public int size() {
        return size;
    }

    public Comparator<? super E> comparator(){
        return comparator;
    }
}
