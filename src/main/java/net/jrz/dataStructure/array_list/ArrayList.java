package net.jrz.dataStructure.array_list;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {

    /**
     * 默认初始化空间
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 默认构造函数使用的数组 空
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * 空数组 -> 构造函数指定容量时使用
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * ArrayList 元素数组缓存区
     */
    transient Object[] elementData;

    /**
     * List 集合元素数量
     */
    private int size;

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else { // 此时初始化容量小于0,不合法
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) elementData[index];
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public boolean add(E e) {
        // 检查内部容量
        int minCapacity = size + 1;
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) { // 如果初始时没有指定容量，使用默认容量
            minCapacity = Math.max(minCapacity, DEFAULT_CAPACITY);
        }

        if (minCapacity - elementData.length > 0) { // 此时已经超过容量了
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity - minCapacity < 0) {
                newCapacity = minCapacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }

        // add element
        elementData[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        E oldValue = elementData(index);
        int numMoved = size - 1 - index;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null; // for gc
        return oldValue;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "elementData=" + Arrays.toString(elementData) +
                ", size=" + size +
                '}';
    }
}
