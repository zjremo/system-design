package net.jrz.leetcode.linkedList;

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
}
