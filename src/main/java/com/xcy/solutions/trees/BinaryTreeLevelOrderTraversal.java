package com.xcy.solutions.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * 102. Binary Tree Level Order Traversal
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right,
 * level by level).
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * <p>
 * Approach:
 * 1. Use FIFO queue to push a level of nodes, and the poll level nodes to offer next level of nodes, every time
 * poll, need to know how many times to poll to store the result of this level of nodes.
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int queueSize = queue.size();
            List<Integer> levelResult = new ArrayList<>(queueSize);

            for (int i = 0; i < queueSize; i++) {
                TreeNode node = queue.poll();
                levelResult.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(levelResult);
        }
        return result;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    static TreeNode buildTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(values[0]);
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();
            if (values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.offer(current.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.offer(current.right);
            }
            i++;
        }
        return root;
    }

    static boolean compare(List<List<Integer>> list1, List<List<Integer>> list2) {
        if (list1 != null && list2 != null) {
            if (list1.size() != list2.size()) {
                return false;
            }
            for (int i = 0; i < list1.size(); i++) {
                if (!compareLists(list1.get(i), list2.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return list1 == null && list2 == null;
    }

    static boolean compareLists(List<Integer> list1, List<Integer> list2) {
        if (list1 != null && list2 != null) {
            if (list1.size() != list2.size()) {
                return false;
            }
            for (int i = 0; i < list1.size(); i++) {
                if ((list1.get(i) != null && !list1.get(i).equals(list2.get(i))) || (list1.get(i) == null && list2.get(i) != null)) {
                    return false;
                }
            }
            return true;
        }
        return list1 == null && list2 == null;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal();

        List<List<Integer>> actual = solution.levelOrder(buildTree(new Integer[]{1, 2, null, 3, null, 4}));
        List<List<Integer>> expected = List.of(List.of(1), List.of(2), List.of(3), List.of(4));
        assert compare(expected, actual) : "Test1: Failed: Expected: " + expected + "Actual: " + actual;

        // balanced tree
        actual = solution.levelOrder(buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7}));
        expected = List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6, 7));
        assert compare(expected, actual) : "Test2: Failed: Expected: " + expected + "Actual: " + actual;
    }
}
