package net.jrz.leetcode.substr;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    /* https://leetcode.cn/problems/subarray-sum-equals-k/submissions/727450117/?envType=study-plan-v2&envId=top-100-liked */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>() {
            {
                put(0, 1);
            }
        };

        int cur = 0, ans = 0;
        for (int num : nums) {
            cur += num;
            int times = map.getOrDefault(cur - k, 0);
            ans += times;
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }

        return ans;
    }

    private static class Node {
        int idx;
        int val;

        Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    /* https://leetcode.cn/problems/sliding-window-maximum/?envType=study-plan-v2&envId=top-100-liked */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Queue<Node> queue = new PriorityQueue<>((a, b) -> (b.val - a.val)); // 大根堆

        assert k >= 1 && k <= n;
        for (int i = 0; i < k; ++i) {
            queue.offer(new Node(i, nums[i]));
        }

        int[] ans = new int[n - k + 1];
        assert queue.peek() != null;
        ans[0] = queue.peek().val;

        for (int i = k; i < n; ++i) {
            queue.offer(new Node(i, nums[i]));
            while (!queue.isEmpty() && queue.peek().idx <= i - k) {
                queue.poll();
            }
            assert queue.peek() != null;
            ans[i - k + 1] = queue.peek().val;
        }

        return ans;
    }

    /* https://leetcode.cn/problems/minimum-window-substring/?envType=study-plan-v2&envId=top-100-liked */
    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n)
            return "";

        String minStr = "";
        int minLen = Integer.MAX_VALUE;

        Window tw = new Window(t), sw = new Window();
        sw.incrby(s.charAt(0), 1);

        int l = 0, r = 0;
        while (r < m) {
            while (Window.compare(sw, tw)) {
                if (minLen > r - l + 1) {
                    minLen = r - l + 1;
                    minStr = s.substring(l, r + 1);
                }
                sw.incrby(s.charAt(l++), -1);
            }

            ++r;
            if (r < m)
                sw.incrby(s.charAt(r), 1);
        }

        return minStr;
    }

    private static class Window {
        int[] arr;
        static int LEN = 52;

        public Window(String s) {
            arr = new int[52];
            int n = s.length();

            for (char c : s.toCharArray()) {
                incrby(c, 1);
            }
        }

        public Window() {
            arr = new int[52];
        }

        public void incrby(char c, int i) { // add or remove
            if (Character.isLowerCase(c)) {
                arr[c - 'a'] += i;
            } else {
                arr[Character.toLowerCase(c) + 26 - 'a'] += i;
            }
        }

        public static boolean compare(Window w1, Window w2) {
            final int[] arr = w1.arr;
            final int[] brr = w2.arr;

            for (int i = 0; i < LEN; ++i) {
                if (arr[i] < brr[i])
                    return false;
            }
            return true;
        }
    }


}
