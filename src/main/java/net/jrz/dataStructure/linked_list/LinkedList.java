package net.jrz.dataStructure.linked_list;

// 双向链表实现
public class LinkedList<E> implements List<E> {

    transient int size = 0; // 链表中的节点个数
    transient Node<E> first;
    transient Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newnode = new Node<>(l, e, null);
        last = newnode;

        if (l == null) {
            first = newnode; // 此时newnode是第一个节点
        } else {
            l.next = newnode;
        }

        ++size;
    }

    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newnode = new Node<>(null, e, f);
        first = newnode;

        if (f == null) {
            last = newnode; // 此时newnode是第一个节点
        } else {
            f.prev = newnode;
        }

        ++size;
    }

    private E unlink(Node<E> x) {
        /*
            Case 1: prev x next
            Case 2: prev x null
            Case 3: null x next
         */
        final E e = x.item;
        final Node<E> prev = x.prev;
        final Node<E> next = x.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        --size;
        return e;
    }

    private Node<E> node(int index) {
        // 依据元素个数来看是从头还是尾遍历
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; ++i) {
                x = x.next;
            }

            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; --i) {
                x = x.prev;
            }

            return x;
        }
    }

    /*
     * 默认插入尾部
     * */
    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /*
     * 尾部添加元素
     * */
    @Override
    public boolean addFirst(E e) {
        linkFirst(e);
        return true;
    }

    @Override
    public boolean addLast(E e) {
        linkLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (Node<E> x = first; x != null; x = x.next) {
            if ((o == null && x.item == null) || (o != null && o.equals(x.item))) {
                unlink(x);
                return true;
            }
        }

        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size)
            return null;
        return node(index).item;
    }

    @Override
    public void printLinkList() {
        if (size == 0) {
            System.out.println("The LinkedList is empty!");
        } else {
            Node<E> p = first;
            while (p != null) {
                System.out.print(p.item + " ");
                p = p.next;
            }
            System.out.println();
        }
    }
}
