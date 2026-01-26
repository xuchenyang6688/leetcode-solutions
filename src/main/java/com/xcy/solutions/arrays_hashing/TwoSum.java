package com.xcy.solutions.arrays_hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 1. Two Sum
 *
 * Approach:
 * - Iterate over array once, treating each element as the "second" number of the pair.
 * - For current number x, check whether (target - x) has been seen before (stored in map).
 * - If found, return indices of the pair. Otherwise, store current number with its index.
 *
 * Time complexity: O(n)
 * Space complexity: O(n)
 */
public class TwoSum {

    /**
     * Finds indices of the two numbers that add up to target.
     * Returns an empty array if no valid pair exists.
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        final Map<Integer, Integer> numToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numToIndex.containsKey(complement)) {
                return new int[] { numToIndex.get(complement), i };
            }
            numToIndex.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        TwoSum solver = new TwoSum();

        // helper for testing
        BiConsumer<int[], int[]> assertPair = (actual, expected) -> {
            if (expected.length == 0) {
                assert actual.length == 0 : "Expected empty result but got: " + Arrays.toString(actual);
            } else {
                Arrays.sort(actual);
                Arrays.sort(expected);
                assert Arrays.equals(actual, expected) : "Expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual);
            }
        };

        int[] nums;
        int[] result;

        nums = new int[] {};
        result = solver.twoSum(nums, 1);
        assertPair.accept(result, new int[] {});

        nums = new int[] {1};
        result = solver.twoSum(nums, 1);
        assertPair.accept(result, new int[] {});

        nums = new int[] {1, 2};
        result = solver.twoSum(nums, 1);
        assertPair.accept(result, new int[] {});

        nums = new int[] {1, 2};
        result = solver.twoSum(nums, 3);
        assertPair.accept(result, new int[] {0, 1});

        nums = new int[] {2, 2};
        result = solver.twoSum(nums, 4);
        assertPair.accept(result, new int[] {0, 1});

        nums = new int[] {3, 3, 3};
        result = solver.twoSum(nums, 6);
        assertPair.accept(result, new int[] {0, 1});
    }

}
