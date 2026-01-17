package com.xcy.solutions.linked_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 21. Merge Two Sorted Lists
 *
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two
 * lists.

 * Return the head of the merged linked list.
 *
 * Example:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 *
 * Constraints:
 *
 *The number of nodes in both lists is in the range [0, 50].
 *-100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 */
public class MergeTwoSortedList {

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(0);
        ListNode current = dummyNode;
        while (list1!=null && list2!=null){
            if (list1.val <= list2.val){
                current.next = list1;
                list1 = list1.next;
            }else{
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
            
        }
        if (list1!=null){
            current.next = list1;
        }
        if (list2!=null){
            current.next = list2;
        }
        return dummyNode.next;
    }
    
    static class ListNode {
        int val;
        ListNode next;
        ListNode (int val){
            this.val = val;
        }
    }
    
    // ========================== Test Utilities =========================
    private static ListNode buildListNodes(int[] nums){
        if (nums == null || nums.length == 0){
            return null;
        }
        ListNode dummyNode = new ListNode(0);
        ListNode current = dummyNode;
        for (int num: nums) {
           current.next = new ListNode(num);
           current = current.next;
        }
        return dummyNode.next;
    }

    private static int[] convertListNodesToArray(ListNode head) {
        List<Integer> resultList = new ArrayList<>();
        while(head!=null){
            resultList.add(head.val);
            head = head.next;
        }
        int[] nums = new int[resultList.size()];
        for (int i=0; i< resultList.size(); i++){
            nums[i] = resultList.get(i);
        }
        return nums;
    }

    public static void main(String[] args) {
        MergeTwoSortedList solution = new MergeTwoSortedList();

        // same length
        ListNode list1 = buildListNodes(new int[] {1,2,4});
        ListNode list2 = buildListNodes(new int[] {1,3,4});
        ListNode actual = solution.mergeTwoLists(list1, list2);
        assert Arrays.equals(new int[]{1,1,2,3,4,4}, convertListNodesToArray(actual));

        //different length
        list1 = buildListNodes(new int[] {1,2,4});
        list2 = buildListNodes(new int[] {1,3});
        actual = solution.mergeTwoLists(list1, list2);
        assert Arrays.equals(new int[]{1,1,2,3,4}, convertListNodesToArray(actual));

        //different length
        list1 = buildListNodes(new int[] {1,2,4});
        list2 = buildListNodes(new int[] {1,5});
        actual = solution.mergeTwoLists(list1, list2);
        assert Arrays.equals(new int[]{1,1,2,4,5}, convertListNodesToArray(actual));

    }

}
