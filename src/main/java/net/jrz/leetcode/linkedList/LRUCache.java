package net.jrz.leetcode.linkedList;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private static class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public Node() {

        }
    }

    private Map<Integer, Node> map;
    private Node head;
    private Node tail;
    private int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.getOrDefault(key, null);

        if (node == null) {
            return -1;
        }

        removeNode(node);
        addToHead(node);
        return node.val;
    }

    // 头插法插入
    public void put(int key, int value) {
        Node node = map.getOrDefault(key, null);

        if (node == null) {
            node = new Node(key, value);
            addToHead(node);

            if (map.size() >= capacity) {
                Node delNode = removeTail();
                map.remove(delNode.key);
            }
            map.put(key, node);
        } else {
            node.val = value;
            // 最近使用过，调整链表结构
            removeNode(node);
            addToHead(node);
        }
    }

    private Node removeTail() {
        Node delNode = tail.prev;
        delNode.prev.next = delNode.next;
        delNode.next.prev = delNode.prev;
        return delNode;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node) {
        Node prev = head;
        Node next = head.next;

        node.next = next;
        node.prev = prev;
        prev.next = node;
        next.prev = node;
    }
}
