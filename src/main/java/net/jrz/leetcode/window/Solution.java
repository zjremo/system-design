package net.jrz.leetcode.window;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    /* https://leetcode.cn/problems/longest-substring-without-repeating-characters/?envType=study-plan-v2&envId=top-100-liked */
    public int lengthOfLongestSubstring(String s) {
        assert s != null;
        int len = s.length();

        if (len == 0) {
            return 0;
        }

        Set<Character> set = new HashSet<>();
        int l = 0, r = 0;
        int max = 0;
        while (r < len) {
            while (r < len && !set.contains(s.charAt(r))) { // 只要不重复我就可以一直扩大窗口
                set.add(s.charAt(r));
                max = Math.max(max, r - l + 1);
                ++r;
            }
            set.remove(s.charAt(l++));
        }

        return max;
    }

    /* https://leetcode.cn/problems/find-all-anagrams-in-a-string/?envType=study-plan-v2&envId=top-100-liked */
    public List<Integer> findAnagrams(String s, String p) {
        // s = "cbaebabacd", p = "abc"
        int m = s.length(), n = p.length();

        if (m < n)
            return new ArrayList<>();

        int[] wp = new int[26], ws = new int[26];

        for (int i = 0; i < n; ++i) {
            wp[p.charAt(i) - 'a'] += 1;
        }

        for (int i = 0; i < n; ++i) {
            ws[s.charAt(i) - 'a'] += 1;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = n; i <= m; ++i) {
            if (compareWindow(ws, wp, 26)) {
                res.add(i - n);
            }

            // update
            if (i != m) {
                ws[s.charAt(i) - 'a'] += 1;
                ws[s.charAt(i - n) - 'a'] -= 1;
            }
        }

        return res;
    }

    public boolean compareWindow(int[] arr, int[] arr2, int len) {
        for (int i = 0; i < len; ++i) {
            if (arr[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

}
