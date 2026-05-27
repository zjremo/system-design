package net.jrz.leetcode.arr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /* https://leetcode.cn/problems/maximum-subarray/?envType=study-plan-v2&envId=top-100-liked */
    public int maxSubArray(int[] nums) {
        // 动态规划：定义dp[i] 为包含第i个位置元素的最大数组和
        int n = nums.length;
        assert n >= 1;

        if (n == 1) {
            return nums[0];
        }

        int[] dp = new int[n];
        dp[0] = nums[0];
        int maxSum = dp[0];

        for (int i = 1; i < n; ++i) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxSum = Math.max(dp[i], maxSum);
        }

        return maxSum;
    }

    /* https://leetcode.cn/problems/merge-intervals/?envType=study-plan-v2&envId=top-100-liked */
    /* intervals = [[1,3],[2,6],[8,10],[15,18]] */
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        assert n >= 1;

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = Arrays.asList(intervals[0][0], intervals[0][1]);

        for (int i = 1; i < n; ++i) {
            if (cur.get(1) >= intervals[i][0]) { // merge
                cur = Arrays.asList(cur.get(0), Math.max(cur.get(1), intervals[i][1]));
            } else {
                res.add(cur); // add to res
                cur = Arrays.asList(intervals[i][0], intervals[i][1]);
            }
        }

        res.add(cur); // add the last cur
        int[][] arr = new int[res.size()][2];
        for (int i = 0; i < res.size(); ++i) {
            arr[i] = new int[2];
            arr[i][0] = res.get(i).get(0);
            arr[i][1] = res.get(i).get(1);
        }

        return arr;
    }

    /* https://leetcode.cn/problems/rotate-array/?envType=study-plan-v2&envId=top-100-liked */
    public void rotate(int[] nums, int k) {
        if (nums.length == 1)
            return;
        k %= nums.length;
        // 5 6 7 1 2 3 4
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l++, r--);
        }
    }

    private void swap(int[] arr, int l, int r) {
        if (l == r)
            return;
        arr[l] ^= arr[r];
        arr[r] ^= arr[l];
        arr[l] ^= arr[r];
    }

    /* https://leetcode.cn/problems/product-of-array-except-self/?envType=study-plan-v2&envId=top-100-liked */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        assert n >= 2;

        int[] leftProduct = new int[n];
        int[] rightProduct = new int[n];

        int cur = 1;
        for (int i = 0; i < n; ++i) {
            leftProduct[i] = cur;
            cur *= nums[i];
        }

        cur = 1;
        for (int i = n - 1; i >= 0; --i) {
            rightProduct[i] = cur;
            cur *= nums[i];
        }

        int[] ans = new int[n];

        for (int i = 0; i < n; ++i) {
            ans[i] = leftProduct[i] * rightProduct[i];
        }
        return ans;
    }

    /* https://leetcode.cn/problems/first-missing-positive/?envType=study-plan-v2&envId=top-100-liked */
    public int firstMissingPositive(int[] nums) {
        // nums = [3,4,-1,1]
        int n = nums.length;

        for (int i = 0; i < n; ++i) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        int tmp = 1;
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
            tmp = i + 1;
        }
        return tmp + 1;
    }
}

