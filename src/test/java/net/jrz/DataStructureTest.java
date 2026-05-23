package net.jrz;

import net.jrz.dataStructure.linked_list.LinkedList;
import net.jrz.dataStructure.linked_list.List;
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
}
