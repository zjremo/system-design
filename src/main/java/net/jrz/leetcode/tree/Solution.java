package net.jrz.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /* https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int leftCnt = maxDepth(root.left);
        int rightCnt = maxDepth(root.right);
        return Math.max(leftCnt, rightCnt) + 1; // 自己
    }

    /* https://leetcode.cn/problems/invert-binary-tree/?envType=study-plan-v2&envId=top-100-liked */
    public TreeNode invertTree_v1(TreeNode root) {
        if (root == null)
            return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode node = queue.poll();
                assert node != null;
                TreeNode left = node.left;
                TreeNode right = node.right;
                node.right = left;
                node.left = right;

                if (left != null)
                    queue.offer(left);
                if (right != null)
                    queue.offer(right);
            }
        }

        return root;
    }

    public TreeNode invertTree_v2(TreeNode root) {
        if (root == null)
            return null;

        TreeNode left = invertTree_v2(root.left);
        TreeNode right = invertTree_v2(root.right);
        root.left = right;
        root.right = left;

        return root;
    }

    /* https://leetcode.cn/problems/symmetric-tree/?envType=study-plan-v2&envId=top-100-liked */
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}
