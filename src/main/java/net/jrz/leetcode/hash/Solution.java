package net.jrz.leetcode.hash;

import java.util.*;
import java.util.stream.IntStream;

public class Solution {
    /* https://leetcode.cn/problems/two-sum/description/?envType=study-plan-v2&envId=top-100-liked */
    public int[] twoSum(int[] nums, int target) {
        assert nums.length >= 2;

        Map<Integer, Integer> map = new HashMap<>();

        int n = nums.length;

        for (int i = 0; i < n; ++i) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /* https://leetcode.cn/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-100-liked */
    public List<List<String>> groupAnagrams(String[] strs) {
        // strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
        Map<String, List<String>> map = new HashMap<>();

        int n = strs.length;
        IntStream.range(0, n).forEach(i -> {
            String arrStr = getArrayStr(strs[i]);
            List<String> grams = map.getOrDefault(arrStr, new ArrayList<>());
            grams.add(strs[i]);
            map.put(arrStr, grams);
        });

        return new ArrayList<>(map.values());
    }

    public String getArrayStr(String str) {
        int[] arr = new int[26];
        for (int i = 0; i < str.length(); ++i) {
            assert str.charAt(i) >= 'a' && str.charAt(i) <= 'z';
            arr[str.charAt(i) - 'a'] += 1;
        }
        return Arrays.toString(arr);
    }

    /* https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-100-liked */
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;

        Set<Integer> set = new HashSet<>();
        Arrays.stream(nums).forEach(set::add);

        int max = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int cur = num;
                int curLength = 1;
                while (set.contains(cur + 1)) {
                    curLength += 1;
                    cur += 1;
                }
                max = Math.max(curLength, max);
            }
        }
        return max;
    }
}
