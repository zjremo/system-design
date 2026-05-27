package net.jrz.dataStructure.stack;

public class ArrayDeque<E> implements Deque<E> {

    transient Object[] elements;

    transient int head;

    transient int tail;

    public ArrayDeque() {
        elements = new Object[4]; // 调整为4方便测试
    }

    @Override
    public void push(E e) {
        if (e == null)
            throw new NullPointerException();
        elements[head = (head - 1) & (elements.length - 1)] = e;
        if (head == tail)
            doubleCapacity();
    }

    private void doubleCapacity() {
        assert head == tail;
        int p = head;
        int n = elements.length;
        int r = n - p; // 头插法插入了这么多元素

        int newCapacity = n << 1; // 每次扩容的容量都要保证是2的次幂，因为使用了与来代替取模的方法
        if (newCapacity < 0) {
            throw new IllegalStateException("deque is too big");
        }

        Object[] a = new Object[newCapacity];
        System.arraycopy(elements, p, a, 0, r);
        System.arraycopy(elements, 0, a, r, p);
        elements = a;
        head = 0;
        tail = n;
    }

    @Override
    public E pop() {
        int h = head;
        @SuppressWarnings("unchecked")
        E result = (E) elements[h];

        if (result == null)
            return null;
        head = (h + 1) & (elements.length - 1);
        return result;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return (E) elements[head];
    }
}
