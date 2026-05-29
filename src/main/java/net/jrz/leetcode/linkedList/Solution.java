package net.jrz.leetcode.linkedList;

import java.util.Arrays;

public class Solution {
    /* https://leetcode.cn/problems/intersection-of-two-linked-lists/?envType=study-plan-v2&envId=top-100-liked */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode p = headA, q = headB;
        while (p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }

    /* https://leetcode.cn/problems/reverse-linked-list/description/?envType=study-plan-v2&envId=top-100-liked */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null, p = head;
        while (p != null) {
            ListNode next = p.next;
            p.next = prev;
            prev = p;
            p = next;
        }

        return prev;
    }

    /* https://leetcode.cn/problems/reverse-linked-list-ii/ */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // d 1 2 3 4 5 6 7
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy_head = new ListNode();
        dummy_head.next = head;
        ListNode p = dummy_head;

        // 1. 定位反转序列头的前继节点；
        int step = 0;
        while (p != null && step < left - 1) {
            p = p.next;
            ++step;
        }

        if (p == null)
            return null;

        // 2. 定位反转序列尾节点；
        ListNode prev = p;
        ListNode seqHead = p.next;
        p = p.next;

        // 3. 反转序列；
        step = 0;
        while (p != null && step < right - left) {
            p = p.next;
            ++step;
        }

        // 4. 拼接序列
        if (p == null) {
            // 后面的等于全部反转
            prev.next = reverseList(seqHead);
            return dummy_head.next;
        }

        ListNode next = p.next, seqTail = p;
        seqTail.next = null;

        reverseList(seqHead);
        prev.next = seqTail;
        seqHead.next = next;
        return dummy_head.next;
    }

    /* https://leetcode.cn/problems/palindrome-linked-list/?envType=study-plan-v2&envId=top-100-liked */
    public boolean isPalindrome(ListNode head) {
        assert head != null;
        if (head.next == null)
            return true;

        ListNode midNode = getMidNode(head);

        ListNode tail = reverseList(midNode), p = head, p1 = tail;

        // check palindrome
        while (p != null && p1 != null) {
            if (p.val != p1.val) {
                return false;
            }
            p = p.next;
            p1 = p1.next;
        }

        // 此时必定为true
        reverseList(tail);
        return true;
    }

    private ListNode getMidNode(ListNode head) {
        // 1 2 3 4 5
        if (head == null)
            return null;

        ListNode f = head, s = head;
        while (f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
        }

        return s;
    }

    /* https://leetcode.cn/problems/linked-list-cycle/?envType=study-plan-v2&envId=top-100-liked */
    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;

        ListNode f = head, s = head;
        while (f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
            if (f == s)
                return true;
        }

        return false;
    }

    /* https://leetcode.cn/problems/linked-list-cycle-ii/description/?envType=study-plan-v2&envId=top-100-liked */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        // 1. 环是否存在
        ListNode f = head, s = head;
        boolean hasCycle = false;
        while (f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
            if (f == s) {
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle)
            return null;

        // 2. 定位节点
        s = head;
        while (f != s) {
            s = s.next;
            f = f.next;
        }

        return f;
    }

    /* https://leetcode.cn/problems/merge-two-sorted-lists/?envType=study-plan-v2&envId=top-100-liked */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }

        if (list1 == null)
            return list2;

        if (list2 == null)
            return list1;

        ListNode p1 = list1, p2 = list2;
        ListNode dummy_head = new ListNode(), prev = dummy_head;

        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                prev.next = p1;
                p1 = p1.next;
            } else {
                prev.next = p2;
                p2 = p2.next;
            }

            prev = prev.next;
        }

        if (p1 != null)
            prev.next = p1;

        if (p2 != null)
            prev.next = p2;

        return dummy_head.next;
    }

    /* https://leetcode.cn/problems/add-two-numbers/?envType=study-plan-v2&envId=top-100-liked */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode prev_head = new ListNode(), prev = prev_head;
        ListNode p = l1, q = l2;
        int col = 0;

        while (p != null && q != null) {
            int sum = p.val + q.val + col;

            prev.next = new ListNode(sum % 10);
            col = sum >= 10 ? 1 : 0;

            prev = prev.next;
            p = p.next;
            q = q.next;
        }

        while (p != null) {
            int sum = p.val + col;

            prev.next = new ListNode(sum % 10);
            col = sum >= 10 ? 1 : 0;

            prev = prev.next;
            p = p.next;
        }

        while (q != null) {
            int sum = q.val + col;

            prev.next = new ListNode(sum % 10);
            col = sum >= 10 ? 1 : 0;

            prev = prev.next;
            q = q.next;
        }

        if (col == 1) {
            prev.next = new ListNode(1);
        }

        return prev_head.next;
    }

    /* https://leetcode.cn/problems/remove-nth-node-from-end-of-list/?envType=study-plan-v2&envId=top-100-liked */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || (head.next == null && n == 1))
            return null;

        ListNode dummy_head = new ListNode();
        dummy_head.next = head;

        ListNode f = dummy_head, s = dummy_head;
        int step = 0;

        while (f != null && f.next != null && step < n) {
            f = f.next;
            ++step;
        }

        while (f != null && f.next != null) {
            f = f.next;
            s = s.next;
        }

        s.next = s.next.next;
        return dummy_head.next;
    }

    /* https://leetcode.cn/problems/reverse-nodes-in-k-group/?envType=study-plan-v2&envId=top-100-liked */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = head;
        ListNode dummy_head = new ListNode(), prev = dummy_head;

        while (p != null) {
            int step = 0;
            ListNode seqHead = p; // 记录序列头
            while (p != null && step < k - 1) {
                p = p.next;
                ++step;
            }

            if (p == null) { // 此时不够k个节点
                prev.next = seqHead;
                break;
            }

            ListNode next = p.next, seqTail = p;
            seqTail.next = null;

            reverseList(seqHead);
            prev.next = seqTail;

            // update prev and p
            prev = seqHead;
            p = next;
        }

        return dummy_head.next;
    }

    /* https://leetcode.cn/problems/copy-list-with-random-pointer/?envType=study-plan-v2&envId=top-100-liked */
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;

        // 1. 先将节点跟在每一个节点之后
        Node p = head;
        while (p != null) {
            Node n = new Node(p.val);
            n.next = p.next;
            p.next = n;
            p = p.next.next;
        }

        // 2. 赋值random节点
        p = head;
        while (p != null) {
            Node n = p.next;
            n.random = p.random == null ? null : p.random.next;
            p = p.next.next;
        }

        // 3. 分离链表
        p = head;
        Node nHead = p.next, np = nHead;

        while (p != null) {
            p.next = np.next;
            np.next = p.next == null ? null : p.next.next;

            // update p and np
            p = p.next;
            np = np.next;
        }

        return nHead;
    }

    /* https://leetcode.cn/problems/sort-list/?envType=study-plan-v2&envId=top-100-liked */
    public ListNode sortList(ListNode head) {
        // head = [4,2,1,3] 输出：[1,2,3,4]
        // mergeSort ====> LinkedList
        if (head == null || head.next == null)
            return head; // 注意这里的递归条件不能只写head == null，因为一个节点会引发无限递归

        ListNode mid = getMidNode(head);

        ListNode r = mid.next;
        mid.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(r);
        return mergeNodes(left, right);
    }

    /* 归并排序链表合并 */
    private ListNode mergeNodes(ListNode left, ListNode right) {
        ListNode p1 = left, p2 = right;

        ListNode dummy_head = new ListNode(), prev = dummy_head;

        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                prev.next = p1;
                p1 = p1.next;
            } else {
                prev.next = p2;
                p2 = p2.next;
            }

            prev = prev.next;
        }

        if (p1 != null)
            prev.next = p1;


        if (p2 != null)
            prev.next = p2;

        return dummy_head.next;
    }

    /* https://leetcode.cn/problems/merge-k-sorted-lists/?envType=study-plan-v2&envId=top-100-liked */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode dummy_head = new ListNode(), prev = dummy_head;

        ListNode[] copy_lists = Arrays.copyOf(lists, lists.length);

        while (!isAllNull(copy_lists)) {
            // 获取最小的节点
            int idx = indexOfMin(copy_lists);
            assert idx != -1;

            prev.next = copy_lists[idx];
            copy_lists[idx] = copy_lists[idx].next;

            // update prev
            prev = prev.next;
        }

        return dummy_head.next;
    }

    /* 数组中的链表节点是否都为空 */
    private boolean isAllNull(ListNode[] lists) {
        for (ListNode node : lists) {
            if (node != null)
                return false;
        }

        return true;
    }

    private int indexOfMin(ListNode[] lists) {
        assert lists != null && lists.length != 0;

        int minIdx = -1;
        ListNode minNode = null;

        for (int i = 0; i < lists.length; ++i) {
            ListNode node;

            if ((node = lists[i]) != null) {
                boolean flag = minNode == null || minNode.val >= node.val;
                minNode = flag ? node : minNode;
                minIdx = flag ? i : minIdx;
            }
        }

        return minIdx;
    }
}
