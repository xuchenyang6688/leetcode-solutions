package com.xcy.solutions.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * 20. Valid Parentheses
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 */
public class ValidParentheses {
    /**
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     * Edge Case: all opening brackets - "((("; all closing brackets - ")))"
     * Tips:
     * 1. Early Return: s.length % 2 !=0
     * 2. Java Stack: Deque, peek, push, pop
     * 3. Map key is right bracket, value is left bracket, then we can compare left bracket in the queue with the Map.get(key)
     * @param s
     * @return
     */
    public boolean isValidParentheses(String s){
        int length = s.length();
        // Valid parenthese string' length should be multiple of 2. If not, early return
        if (length % 2 != 0){
            return false;
        }
        Map<Character, Character> parenthesesMap = Map.of(')', '(', ']', '[', '}', '{');

        Deque<Character> stack = new ArrayDeque<>(length);
        for (int i=0; i<length; i++){
            char character = s.charAt(i);
            if (parenthesesMap.containsKey(character)){
                // Attention: stack.isEmpty condition
                if (stack.isEmpty() || !stack.peek().equals(parenthesesMap.get(character))){
                    return false;
                }
                stack.pop();
            }else {
                stack.push(character);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses solution = new ValidParentheses();
        // s = "}", return false; covering stack is empty when meeting with closing bracket
        // s = "{{{", return false; covering at last if the stack is not empty, also return invalid.
        // s = "([{}])", return true; covering normal case
        // s = "()[)", return false; covering stack top element is not matched with current closing bracket
        assert !solution.isValidParentheses("{");
        assert !solution.isValidParentheses("{{{");
        assert solution.isValidParentheses("([{}])");
        assert !solution.isValidParentheses("()[)");
    }
}
