package com.xcy.solutions.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * 102. Binary Tree Level Order Traversal
 *
 * Given the root of a binary tree, return the level order traversal
 * of its nodes' values. (i.e., from left to right, level by level).
 *
 * Example:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 *
 * Constraints:
 * - The number of nodes is in the range [0, 2000]
 * - -1000 <= Node.val <= 1000
 */
public class BinaryTreeLevelOrderTraversal {

    /**
     * Performs level order traversal (BFS) of binary tree.
     *
     * Approach: Breadth-First Search using queue
     * 1. Use queue to track nodes at current level
     * 2. For each level:
     *    - Record current queue size (nodes at this level)
     *    - Process all nodes at this level, adding children to queue
     *    - Collect level results and add to final result
     *
     * Key Insight: The queue size at the start of each while loop
     * represents exactly the number of nodes at the current level.
     * By processing exactly that many nodes, we maintain level separation.
     *
     * Visualization for [3,9,20,null,null,15,7]:
     * Level 0: queue = [3], size=1 → process 3 → add 9,20
     * Level 1: queue = [9,20], size=2 → process 9,20 → add 15,7
     * Level 2: queue = [15,7], size=2 → process 15,7 → done
     *
     * Time Complexity: O(n) where n = number of nodes
     *   - Each node visited exactly once
     *
     * Space Complexity: O(n) in worst case
     *   - Queue stores at most width of tree (worst case O(n))
     *   - Result storage O(n)
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                // Add children to queue for next level
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(currentLevel);
        }
        return result;
    }

    /**
     * Alternative: Recursive DFS approach
     * Uses depth parameter to track level for result accumulation.
     *
     * Time: O(n), Space: O(n) for recursion stack
     */
    public List<List<Integer>> levelOrderDFS(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        levelOrderDFSHelper(root, 0, result);
        return result;
    }

    private void levelOrderDFSHelper(TreeNode node, int level, List<List<Integer>> result){
        if (node == null){
            return;
        }
        // Create new level list if needed
        if (level>=result.size()){
            List<Integer> levelResult = new ArrayList<>();
            result.add(levelResult);
        }
        // Add current node to its level
        result.get(level).add(node.val);
        // Recursively process children
        levelOrderDFSHelper(node.left, level+1, result);
        levelOrderDFSHelper(node.right, level+1, result);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // ==================== TESTING UTILITIES ====================

    /**
     * Builds binary tree from level-order array representation.
     * Example: [3,9,20,null,null,15,7] represents:
     *        3
     *       / \
     *      9  20
     *        /  \
     *       15   7
     */
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

    static boolean compareResults(List<List<Integer>> expected, List<List<Integer>> actual) {
        if (expected == null && actual == null) return true;
        if (expected == null || actual == null) return false;
        if (expected.size() != actual.size()) return false;
        for (int i = 0; i < expected.size(); i++) {
            List<Integer> expectedLevel = expected.get(i);
            List<Integer> actualLevel = actual.get(i);
            if (actualLevel == null && expectedLevel == null) continue;
            if (actualLevel == null || expectedLevel == null) return false;
            if (actualLevel.size() != expectedLevel.size()) return false;
            for (int j = 0; j < expectedLevel.size(); j++) {
                if (!expectedLevel.get(j).equals(actualLevel.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal();

        List<List<Integer>> actual = solution.levelOrder(buildTree(new Integer[]{1, 2, null, 3, null, 4}));
        List<List<Integer>> expected = List.of(List.of(1), List.of(2), List.of(3), List.of(4));
        assert compareResults(expected, actual) : "Test1: Failed: Expected: " + expected + "Actual: " + actual;

        // balanced tree
        actual = solution.levelOrder(buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7}));
        expected = List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6, 7));
        assert compareResults(expected, actual) : "Test2: Failed: Expected: " + expected + "Actual: " + actual;
    }
}
