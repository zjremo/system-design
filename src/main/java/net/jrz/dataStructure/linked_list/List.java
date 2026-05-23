package net.jrz.dataStructure.linked_list;

public interface List<E> {
    boolean add(E e);

    boolean addFirst(E e);

    boolean addLast(E e);

    boolean remove(Object o);

    E get(int index);

    void printLinkList();
}
