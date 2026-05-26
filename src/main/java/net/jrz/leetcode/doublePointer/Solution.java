package net.jrz.leetcode.doublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /* https://leetcode.cn/problems/move-zeroes/description/?envType=study-plan-v2&envId=top-100-liked */
    public void moveZeroes(int[] nums) {
        // nums = [0,1,0,3,12]
        int n = nums.length;

        if (n == 0 || n == 1)
            return;

        int l = 0, r = 0;
        /*
        l 之前的代表是非0, l -> r 之间的为0, r之后的不确定
        */
        while (r < n) {
            if (nums[r] != 0) {
                swap(nums, l++, r);
            }

            ++r;
        }

    }

    public void swap(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }

        arr[l] ^= arr[r];
        arr[r] ^= arr[l];
        arr[l] ^= arr[r];
    }

    /* https://leetcode.cn/problems/container-with-most-water/?envType=study-plan-v2&envId=top-100-liked */
    public int maxArea(int[] height) {
        int n = height.length;
        int l = 0, r = n - 1;

        int size = 0;

        while (l < r) {
            int minEdge = height[l] < height[r] ? l : r;

            size = Math.max(size, (r - l) * height[minEdge]);

            if (minEdge == l) {
                ++l;
            } else {
                --r;
            }
        }

        return size;
    }

    /* https://leetcode.cn/problems/3sum/?envType=study-plan-v2&envId=top-100-liked */
    public List<List<Integer>> threeSum(int[] nums) {
        // nums = [-1,0,1,2,-1,-4]
        // res : [[-1,-1,2],[-1,0,1]]
        assert nums.length >= 3;

        int n = nums.length;
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue; // 此时已经试过了
            }

            if (nums[i] > 0) { // 递增顺序，此时必定不满足
                continue;
            }

            // 转为两数之和 ==== > num1 + num2 = target
            int target = -nums[i], l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[l] + nums[r];
                if (sum == target) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r])));
                    ++l;
                    --r;
                    for (; l < n && nums[l] == nums[l - 1]; ++l) ;
                    for (; r >= 0 && nums[r] == nums[r + 1]; --r) ;
                } else if (sum < target) {
                    ++l;
                } else {
                    --r;
                }
            }
        }
        return res;
    }

    /* https://leetcode.cn/problems/trapping-rain-water/description/?envType=study-plan-v2&envId=top-100-liked */
    public int trap(int[] height) {
        // 获取左右最大的高度
        int n = height.length;

        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];

        for (int i = 1; i < n - 1; ++i) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
        }

        for (int i = n - 2; i >= 0; --i) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
        }

        int ans = 0;

        for (int i = 1; i < n - 1; ++i) {
            int minHeight = Math.min(maxLeft[i], maxRight[i]);

            if (minHeight > height[i]) {
                ans += (minHeight - height[i]);
            }
        }

        return ans;
    }
}
