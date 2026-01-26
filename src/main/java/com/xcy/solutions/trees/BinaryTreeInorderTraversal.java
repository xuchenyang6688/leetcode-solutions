package com.xcy.solutions.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 94. Binary Tree Inorder Traversal
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * Inorder traversal: left → root → right
 * <p>
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 * <p>
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal {

    /**
     * Main entry point - uses iterative traversal by default.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        return inorderTraversalIterative(root);
        // return inorderTraversalRecursive(root, result);
    }

    /**
     * Iterative inorder traversal using stack.
     * <p>
     * Approach: Simulate recursion using explicit stack
     * 1. Push all left nodes to stack (going deep left)
     * 2. Pop node, process it (add to result)
     * 3. Move to right subtree and repeat
     * <p>
     * Key Insight: The stack preserves the "call stack" of recursion.
     * We process nodes in LIFO order as we would in recursion.
     * <p>
     * Time Complexity: O(n) - each node visited once
     * Space Complexity: O(n) - stack stores up to height of tree
     * (O(h) where h is height, worst case O(n) for skewed tree)
     */
    private List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        // Attention: This while condition
        while (current != null || !stack.isEmpty()) {
            // Go as deep left as possible
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Process node
            current = stack.pop();
            result.add(current.val);

            // Move to right subtree
            current = current.right;
        }
        return result;
    }

    /**
     * Recursive inorder traversal.
     * <p>
     * Approach: Depth-first search
     * 1. Traverse left subtree recursively
     * 2. Visit current node
     * 3. Traverse right subtree recursively
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n) for recursion stack (O(h) height)
     */
    private List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversalRecursiveHelper(root, result);
        return result;
    }

    private void inorderTraversalRecursiveHelper(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorderTraversalRecursiveHelper(root.left, result);
        result.add(root.val);
        inorderTraversalRecursiveHelper(root.right, result);
    }

    // ==================== TREE NODE DEFINITION ====================
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // ======================= Test Cases =========================

    static TreeNode buildTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(values[0]);
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode node = queue.poll();
            if (values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.offer(node.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                node.right = new TreeNode(values[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }

    static boolean compareLists(List<Integer> list1, List<Integer> list2) {
        if (list1 != null && list2 != null) {
            if (list1.size() != list2.size()) {
                return false;
            }
            for (int i = 0; i < list1.size(); i++) {
                if (!list1.get(i).equals(list2.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return list1 == null && list2 == null;
    }

    public static void main(String[] args) {
        BinaryTreeInorderTraversal solution = new BinaryTreeInorderTraversal();
        List<Integer> actualResult = solution.inorderTraversal(buildTree(new Integer[]{1, 2, null, 3, 4}));
        List<Integer> expectedResult = List.of(3, 2, 4, 1);
        assert compareLists(expectedResult, actualResult) : "Test 1 failed: Expected: " + expectedResult + ", Actual:" +
                " " + actualResult;

        //left-skewed tree
        actualResult = solution.inorderTraversal(buildTree(new Integer[]{1, 2, null, 3, null, 4}));
        expectedResult = List.of(4, 3, 2, 1);
        assert compareLists(expectedResult, actualResult) : "Test 2 failed: Expected: " + expectedResult + ", Actual:" +
                " " + actualResult;

        //balanced tree
        actualResult = solution.inorderTraversal(buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                14, 15}));
        expectedResult = List.of(8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15);
        assert compareLists(expectedResult, actualResult) : "Test 2 failed: Expected: " + expectedResult + ", Actual:" +
                " " + actualResult;

    }
}
