package net.jrz;

import net.jrz.dataStructure.array_list.ArrayList;
import net.jrz.dataStructure.linked_list.LinkedList;
import net.jrz.dataStructure.linked_list.List;

import net.jrz.dataStructure.queue.queue.PriorityQueue;
import net.jrz.dataStructure.queue.queue.Queue;
import net.jrz.dataStructure.queue.stack2queue.Stack2Queue;
import net.jrz.dataStructure.stack.ArrayDeque;
import net.jrz.dataStructure.stack.Deque;
import org.junit.Test;

public class DataStructureTest {
    @Test
    public void tesLinked_list() {
        List<String> list = new LinkedList<>();

        // add elements
        list.add("a");
        list.addFirst("b");
        list.addLast("c");

        // printList
        list.printLinkList();
        // add element to head
        list.addFirst("d");
        // remove element
        list.remove("b");
        // printList
        list.printLinkList();
        // get
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
    }

    @Test
    public void testArray_List() {
        net.jrz.dataStructure.array_list.List<String> list = new ArrayList<>();
        list.add("01");
        list.add("02");
        list.add("03");
        list.add("04");
        list.add("05");
        list.add("06");
        list.add("07");
        list.add("08");
        list.add("09");
        list.add("10");
        list.add("11");
        list.add("12");

        System.out.println(list);
        list.remove(9);
        System.out.println(list);
    }

    @Test
    public void testStack2Queue() {
        Stack2Queue<String> queue = new Stack2Queue<>();
        queue.offer("01");
        queue.offer("02");
        queue.offer("03");
        queue.offer("04");
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.peek());
    }

    @Test
    public void testPriorityQueue() {
        Queue<String> pq = new PriorityQueue<>(2);
        pq.offer("01");
        pq.offer("02");
        pq.offer("03");
        System.out.println(pq.peek());
        System.out.println(pq.size());
        System.out.println(pq.poll());
        System.out.println(pq.size());
        System.out.println(pq.peek());
    }

    private String trim(String s) {
        // " hello world   "
        int len = s.length();
        int st = 0;

        while ((st < len) && (s.charAt(st) <= ' ')) {
            ++st;
        }

        while ((st < len) && (s.charAt(len - 1) <= ' ')) {
            --len;
        }

        return ((st != 0) || (len != s.length())) ? s.substring(st, len) : s;
    }

    @Test
    public void testString() {
        String s = "   hello world    ";
        System.out.println("Before trim operation, the s is : \"" + s + "\"");
        System.out.println("After trim operation, the s is : \"" + trim(s) + "\"");
    }

    @Test
    public void testArrayDeque(){
        Deque<Integer> deque = new ArrayDeque<>();
        System.out.println(deque.isEmpty());
        deque.push(0);
        deque.push(1);
        deque.push(2);
        deque.push(3);
        System.out.println(deque.peek());
        deque.push(4);
        deque.push(5);
        deque.push(6);
        System.out.println(deque.peek());
        System.out.println(deque.pop());
        System.out.println(deque.peek());
    }
}
