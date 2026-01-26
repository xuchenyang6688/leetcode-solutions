package com.xcy.solutions.dynamic_programming;

/**
 * 70. Climbing Stairs
 * You are climbing a staircase. It takes n steps to reach the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * Example:
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * <p>
 * Constraints:
 * - 1 <= n <= 45
 */
public class ClimbingStairs {
    /**
     * Returns the number of distinct ways to climb n steps
     * taking 1 or 2 steps at a time.
     * <p>
     * Approach: Dynamic Programming with Space Optimization
     * 1. Base cases:
     * - ways(1) = 1 way (1 step)
     * - ways(2) = 2 ways (1+1 or 2)
     * 2. Recurrence: ways(n) = ways(n-1) + ways(n-2)
     * - To reach step n, you can come from step n-1 (take 1 step)
     * - or from step n-2 (take 2 steps)
     * 3. Use two variables to store previous two results (Fibonacci sequence)
     * <p>
     * Key Insight: This is essentially the Fibonacci sequence.
     * The number of ways to reach step n equals the nth Fibonacci number
     * if we define Fibonacci as: F(1)=1, F(2)=2, F(n)=F(n-1)+F(n-2)
     * <p>
     * Time Complexity: O(n) - single loop from 3 to n
     * Space Complexity: O(1) - only two variables needed
     *
     * @param n total number of steps
     * @return number of distinct ways to climb to the top
     */
    public int climbStairs(int n) {

        if (n == 1) {
            return n;
        }
        int waysToPrePre = 1; // dp[0] = 1
        int waysToPre = 1;  //dp[1] = 1
        for (int i = 2; i <= n; i++) {
            int currentWays = waysToPrePre + waysToPre;
            waysToPrePre = waysToPre;
            waysToPre = currentWays;
        }
        return waysToPre;
    }

    public static void main(String[] args) {
        ClimbingStairs solution = new ClimbingStairs();
        assert 8 == solution.climbStairs(5);
    }

}
