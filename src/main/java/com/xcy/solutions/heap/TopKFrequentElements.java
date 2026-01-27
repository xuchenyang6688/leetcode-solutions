package com.xcy.solutions.heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347. Top K Frequent Elements
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 *
 * Example:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 */
public class TopKFrequentElements {
    /**
     * Finds the k most frequent elements using a min-heap approach.
     *
     * Approach:
     * 1. Count frequencies of all elements
     * 2. Maintain a min-heap of size k to track the top k frequent elements
     * 3. For each element, compare with the heap's minimum frequency
     *    - If heap is full and current frequency > heap minimum, replace it
     * 4. Extract results from the heap
     *
     * Time Complexity: O(N + U*log k) where N = nums.length, U = unique elements
     *   - Build frequency map: O(N)
     *   - Process U elements in heap of size k: O(U * log k)
     *   - Worst case (all elements unique): O(N * log k)
     *   - Best case (few unique elements): O(N)
     *
     * Space Complexity: O(U + k) where U = number of unique elements
     *   - Frequency map: O(U)
     *   - Min-heap: O(k)
     * Syntax:
     *  - Priority Queue comparator:
     *  - Variable Name: pq -> minHeap
     *  - Extract Variable Name for Readability: int element, int frequency
     *  - pq methods: offer, poll, peek not throw exception
     * @param nums array of integers
     * @param k number of most frequent elements to return
     * @return array containing the k most frequent elements
     */
    public int[] getTopKFrequent(int[] nums, int k){
        // Step 1: Count frequencies of all elements
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            frequencyMap.put(nums[i], frequencyMap.getOrDefault(nums[i], 0) + 1);
        }
        // Step 2: Use min-heap to track top k frequent elements
        // Heap stores [element, frequency] pairs sorted by frequency ascending
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((array1, array2) -> Integer.compare(array1[1], array2[1]));
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()){
            int element = entry.getKey();
            int frequency = entry.getValue();
            if (minHeap.size() == k){
                if(minHeap.peek()[1] < frequency){
                    minHeap.poll();
                    minHeap.offer(new int[]{element, frequency});
                }
            }else{
                minHeap.offer(new int[]{element, frequency});
            }
        }
        // Step 3: Extract results from heap
        int[] ans = new int[k];
        for (int i=0; i<k; i++){
            ans[i] = minHeap.poll()[0]; //Attention: index 0
        }
        return ans;
    }

    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();

        assert Arrays.equals(new int[]{1}, solution.getTopKFrequent(new int[]{1}, 1));

        int[] ans = solution.getTopKFrequent(new int[] {1,1,1,2,2,3}, 2);
        Arrays.sort(ans);
        assert Arrays.equals(new int[]{1,2}, ans);

        assert Arrays.equals(new int[]{3}, solution.getTopKFrequent(new int[]{1,2,2, 4, 4, 3,3,3}, 1));
    }
}
