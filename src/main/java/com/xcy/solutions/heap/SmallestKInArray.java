package com.xcy.solutions.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Interview 17.14. Smallest K LCCI
 * <p>
 * Design an algorithm to find the smallest K numbers in an array.
 * <p>
 * Example:
 * Input: arr = [1,3,5,7,2,4,6,8], k = 4
 * Output: [1,2,3,4]
 * <p>
 * Constraints:
 * - 0 <= arr.length <= 100000
 * - 0 <= k <= min(100000, arr.length)
 */
public class SmallestKInArray {
    /**
     * Finds the k smallest elements in an array using max-heap.
     *
     * Approach: Max-Heap of size k
     * 1. Use a max-heap (priority queue with reverse ordering)
     * 2. Add first k elements to heap
     * 3. For remaining elements:
     *    - If current element < heap's max (root), replace max with current
     * 4. Extract elements from heap (unordered, but k smallest)
     *
     * Key Insight: A max-heap maintains the largest element at root.
     * By keeping only k elements, the root is the k-th smallest element seen so far.
     * Any element larger than root cannot be among the k smallest.
     *
     * Visualization for arr=[5,6,1,7,4,2,9,3], k=4:
     * Step 0: heap=[], add 5 → [5]
     * Step 1: heap=[5], add 6 → [6,5] (max=6)
     * Step 2: heap=[6,5], add 1 → [6,5,1] (max=6)
     * Step 3: heap=[6,5,1], add 7 → [7,6,5,1] (full, max=7)
     * Step 4: heap=[7,6,5,1], 4<7 → replace 7 with 4 → [6,4,5,1] (max=6)
     * Step 5: heap=[6,4,5,1], 2<6 → replace 6 with 2 → [5,4,2,1] (max=5)
     * Step 6: heap=[5,4,2,1], 9>5 → ignore
     * Step 7: heap=[5,4,2,1], 3<5 → replace 5 with 3 → [4,3,2,1] (max=4)
     * Result: [1,2,3,4] (after sorting)
     *
     * Time Complexity: O(n log k) where n = arr.length
     *   - Building heap of size k: O(k log k)
     *   - Processing n-k elements: O((n-k) log k)
     *   - Extraction: O(k log k)
     *   - Total: O(n log k)
     *
     * Space Complexity: O(k) for the heap
     *   - Only stores at most k elements at any time
     */
    public int[] smallestK(int[] arr, int k) {
        // Edge cases
        if (arr == null || arr.length == 0 || k == 0) {
            return new int[0];
        }

        if (k > arr.length) {
            // Return sorted copy of entire array
            int[] result = arr.clone();
            Arrays.sort(result);
            return result;
        }
        // Max-heap: larger numbers have higher priority (reverse natural order)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Comparator.reverseOrder());
        // Build initial heap with first k elements
        for (int i = 0; i < k; i++) {
            maxHeap.offer(arr[i]);
        }

        // Process remaining elements
        for (int i = k; i < arr.length; i++) {
            int current = arr[i];
            if (current < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(current);
            }
        }

        // Extract k smallest elements from heap
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        SmallestKInArray solution = new SmallestKInArray();
        int[] actual = solution.smallestK(new int[]{5, 6, 1, 7, 4, 2, 9, 3}, 4);
        Arrays.sort(actual);
        assert Arrays.equals(new int[]{1, 2, 3, 4}, actual);

    }
}
