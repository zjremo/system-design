package net.jrz.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class Traverse {

    public void preOrderTraverse(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.val + " ");
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }

    public void stackPreOrderTraverse(TreeNode root) {
        if (root == null)
            return;

        Deque<TreeNode> s = new ArrayDeque<>();
        while (root != null || !s.isEmpty()) {
            while (root != null) {
                System.out.print(root.val + " ");
                s.push(root);
                root = root.left;
            }

            root = s.pop();
            root = root.right;
        }
    }

    public void inOrderTraverse(TreeNode root) {
        if (root == null)
            return;
        inOrderTraverse(root.left);
        System.out.print(root.val + " ");
        inOrderTraverse(root.right);
    }

    public void stackInOrderTraverse(TreeNode root) {
        if (root == null)
            return;

        Deque<TreeNode> s = new ArrayDeque<>();
        while (!s.isEmpty() || root != null) {
            while (root != null) {
                s.push(root);
                root = root.left;
            }

            root = s.pop();
            System.out.print(root.val + " ");
            root = root.right;
        }
    }

    public void postOrderTraverse(TreeNode root) {
        if (root == null)
            return ;

        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.print(root.val + " ");
    }

    public void stackPostOrderTraverse(TreeNode root) {
        if (root == null)
            return;

        Deque<TreeNode> s = new ArrayDeque<>();
        TreeNode prev = null;

        while (!s.isEmpty() || root != null){
            while (root != null){
                s.push(root);
                root = root.left;
            }

            TreeNode peek = s.peek();
            assert peek != null;
            if (peek.right == null || peek.right == prev) {
                // 说明这个的左右已经遍历完毕
                System.out.println(peek.val + " ");
                s.pop();
                prev = peek;
            } else {
                root = peek.right;
            }
        }
    }
}
