package com.xcy.solutions.binary_search;

/**
 * 33. Search in Rotated Sorted Array
 *
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k
 * (1 <= k < nums.length) such that the resulting array is:
 * [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 *
 * Example: [0,1,2,4,5,6,7] rotated at pivot 3 becomes [4,5,6,7,0,1,2]
 *
 * Given the array nums after the possible rotation and an integer target,
 * return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * Constraints:
 * - You must write an algorithm with O(log n) runtime complexity.
 * - All values in nums are unique.
 */
public class SearchInRotatedSortedArray {
    /**
     * Searches for a target value in a rotated sorted array using modified binary search.
     *
     * Approach: Modified Binary Search
     * 1. At each step, we check which half of the array is properly sorted
     * 2. Determine if the target lies within the sorted half
     * 3. Adjust search boundaries accordingly
     *
     * Key Insight: In a rotated sorted array, at least one half (left or right of mid)
     * will always be sorted. We can use this property to decide where to search.
     * (Move left or right to mid + 1 or mid -1)
     *
     * Time Complexity: O(log n) - standard binary search with constant-time decisions
     * Space Complexity: O(1) - only using pointers, no extra data structures
     *
     * Tips: Avoid Potential Overflow: Use int mid = left + (right - left) / 2 instead of (left+ right)/2
     *
     * @param nums    rotated sorted array with distinct integers
     * @param target  value to search for
     * @return        index of target if found, -1 otherwise
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // Check if left half is sorted
            if (nums[left] <= nums[mid]) {
                // Check if target is within the sorted left half
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Right half must be sorted
            else {
                // Check if target is within the sorted right half
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();
        assert 0 == solution.search(new int[]{1}, 1);

        assert 0 == solution.search(new int[]{2, 3, 4, 5, 1}, 2);
        assert 2 == solution.search(new int[]{2, 3, 4, 5, 1}, 4);
        assert 3 == solution.search(new int[]{2, 3, 4, 5, 1}, 5);
        assert 4 == solution.search(new int[]{2, 3, 4, 5, 1}, 1);

        assert 2 == solution.search(new int[]{5, 1, 2, 3, 4}, 2);
        assert 0 == solution.search(new int[]{5, 1, 2, 3, 4}, 5);
        assert 1 == solution.search(new int[]{5, 1, 2, 3, 4}, 1);

        //Edge case
        assert -1 == solution.search(new int[]{2, 3, 4, 5, 1}, 0);
    }
}
