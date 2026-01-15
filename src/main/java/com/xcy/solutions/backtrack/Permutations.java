package com.xcy.solutions.backtrack;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 46. Permutations
 *
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 */
public class Permutations {

    public List<List<Integer>> permutate (int[] nums) {
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> permutation = new ArrayList<>();
        for (int num: nums){
            permutation.add(num);
        }
        backtrack(0, length, permutation, result);
        return result;
    }

    private void backtrack (int index, int length, List<Integer> permutation, List<List<Integer>> result) {
        if (index == length) {
            result.add(new ArrayList<>(permutation));
            return;
        }
        for (int i=index; i<length; i++){
            // current we choose the position i as the position index via swapping
            Collections.swap(permutation, index, i);
            backtrack(index+1, length, permutation, result);
            // swapping back
            Collections.swap(permutation, index, i);
        }
    }

    // How to use generic type T, T is collection
    private static boolean compare(List<List<Integer>> expected, List<List<Integer>> actual){
        if (expected == null && actual == null) return true;
        if (expected == null || actual == null) return false;
        if (expected.size() != actual.size()) return false;
        for (int i=0; i<expected.size(); i++){
            List<Integer> permutationExpected = expected.get(i);
            List<Integer> permutationActual = actual.get(i);
            if (permutationExpected == null && permutationActual == null) return true;
            if (permutationExpected == null || permutationActual == null) return false;
            if (permutationExpected.size() != permutationActual.size()) return false;
            for (int j=0; j<permutationExpected.size(); j++){
                if (!permutationExpected.get(j).equals(permutationActual.get(j))){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Permutations solution = new Permutations();
        List<List<Integer>> result = solution.permutate(new int[] {1,2,3});
        result.sort((list1, list2) ->{
            for (int i=0; i<list1.size(); i++){
                if(list1.get(i).equals(list2.get(i))){
                    continue;
                }
                return Integer.compare(list1.get(i), list2.get(i));
            }
            return 0;
        });

        assert compare(List.of(List.of(1,2,3), List.of(1,3,2), List.of(2,1,3), List.of(2,3,1), List.of(3,1,2), List.of(3,2,1)), result);
    }

// ========================== Not suggested (Every time I maintain a available num List to select, need more spaces =========================
//   How to use visited array to resolve this issue?
//    public List<List<Integer>> permutate(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
//        int length = nums.length;
//        List<Integer> permutation = new ArrayList<>(length);
//        List<Integer> uniqueNumList = new ArrayList<>(length);
//        for (int num: nums){
//            uniqueNumList.add(num);
//        }
//        backtrack(uniqueNumList, 0, length, permutation, result);
//        return result;
//    }
//
//    private void backtrack(List<Integer> uniqueNumList, int index, int length, List<Integer> permutation, List<List<Integer>> result) {
//        if (uniqueNumList.isEmpty() || index >= length){
//            List<Integer> permutationResult = new ArrayList<>(permutation);
//            result.add(permutationResult);
//            return;
//        }
//        for (int i=0; i<uniqueNumList.size(); i++){
//            permutation.add(uniqueNumList.get(i));
//            List<Integer> nextUniqueNumList = new ArrayList<>(uniqueNumList);
//            nextUniqueNumList.remove(uniqueNumList.get(i));
//            backtrack(nextUniqueNumList, index+1, length, permutation, result);
//            permutation.remove(uniqueNumList.get(i));
//        }
//    }
}
