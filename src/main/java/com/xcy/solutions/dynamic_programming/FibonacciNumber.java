package com.xcy.solutions.dynamic_programming;

/**
 * 509. Fibonacci Number
 *
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 *
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 * Fibonacci: 0,1,1,2,3,5,8,13,21,34...
 *
 * Attention: Some else description is:
 * F(1) = 1, F(2) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 2.
 * Given n, calculate F(n).
 */
public class FibonacciNumber {

    /**
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n ==0 || n==1){
            return n;
        }
        int pre1 = 0;
        int pre2 = 1;
        for (int i=2; i<=n; i++){
            int current = pre1 + pre2;
            pre1 = pre2;
            pre2 = current;
        }
        return pre2;
    }

    public static void main(String[] args) {
        FibonacciNumber solution = new FibonacciNumber();
        assert 3 == solution.fib(4);
        assert 1 == solution.fib(1);
        assert 1 == solution.fib(2);
        assert 34 == solution.fib(9);

    }
}
