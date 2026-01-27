package com.xcy.solutions.heap;


/**
 * 1985. Find the Kth Largest Integer in the Array
 *
 * You are given an array of strings nums and an integer k. Each string in nums represents an integer without leading
 * zeros.
 * Return the string that represents the kth largest integer in nums.
 * Note: Duplicate numbers should be counted distinctly. For example, if nums is ["1","2","2"], "2" is the first
 * largest integer, "2" is the second-largest integer, and "1" is the third-largest integer.
 */
public class FindKthLargestInteger {

    public String KthLargestNumber(String[] nums, int k){
        return "";
    }

    /**
     * input: [1,3,5,2,2],5,3
     * output: 2
     * @param a array
     * @param n size of array
     * @param K the Kth maximum number (duplicated number considered distinctly)
     * @return
     */
    public int findKth (int[] a, int n, int K) {
        return findKth(a, 0, n-1, K);

    }

    private int findKth(int[] a, int start, int end, int K){
//        if(start == end && K == 1){
//            return a[start];
//        }
//
//        int pivot = -1;
//        while (start<end){
//            if (a[start]>= a[pivot]){
//                start++;
//            }else{
//
//                while (end>start && a[end]<a[pivot]){
//                    end --;
//                }
//
//                if(start != end) {
//                    int temp = a[start];
//                    a[start] = a[end];
//                    a[end] = temp;
//                    start ++;
//                }
//            }
//
//
//        }
//
//        // start == end
//        int temp = a [start];
//        a[start] = a[pivot];
//        a[pivot] = temp;
//        if (start +1 <=K){
//            findKth(a, 0, start, K);
//        }else{
//            findKth(a, start+1, end-1, K-(start+1));
//        }
//
    }


}
