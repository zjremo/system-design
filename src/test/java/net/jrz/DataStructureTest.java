package net.jrz;

import net.jrz.dataStructure.array_list.ArrayList;
import net.jrz.dataStructure.linked_list.LinkedList;
import net.jrz.dataStructure.linked_list.List;

import net.jrz.dataStructure.queue.queue.PriorityQueue;
import net.jrz.dataStructure.queue.queue.Queue;
import net.jrz.dataStructure.queue.stack2queue.Stack2Queue;
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
    public void testStack2Queue(){
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
    public void testPriorityQueue(){
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


}
