package com.xcy.solutions.dynamic_programming;

import java.util.Arrays;

/**
 * 322. Coin Change
 *
 * You are given an integer array coins representing coins of different denominations and an integer amount
 * representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up
 * by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Constraints:
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
public class CoinChange {
    /**
     * Returns the minimum number of coins needed to make up the amount.
     *
     * Approach: Dynamic Programming (Bottom-Up)
     * 1. dp[i] represents minimum coins needed for amount i
     * 2. Initialize dp[0] = 0, others to amount+1 (impossible value)
     * 3. For each amount i from 1 to target:
     *    - For each coin value c:
     *      - If c <= i: dp[i] = min(dp[i], dp[i-c] + 1)
     * 4. If dp[amount] > amount, return -1 (no solution), else return dp[amount]
     *
     * Key Insight: The optimal solution for amount i can be constructed
     * from optimal solutions for smaller amounts (i - coin) + 1 coin.
     * This exhibits optimal substructure property for DP.
     *
     * Time Complexity: O(amount * n) where n = coins.length
     *   - Outer loop: amount iterations
     *   - Inner loop: up to n iterations
     *
     * Space Complexity: O(amount)
     *   - DP array of size amount+1
     */
    public int coinChange(int[] coins, int amount) {
        // Use amount+1 as "infinity" since max coins needed is amount (if coin=1 exists)
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        // for each amount, it is the min of (amount - coins[0], amount-coins[1]... amount-coins[n-1])+1, and make
        // sure coins[i]<=amount
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * Alternative: Optimized version that sorts coins first.
     * Can break early in inner loop when coin > current amount.
     *
     * Time Complexity: O(amount * n) but with early termination
     * Space Complexity: O(amount)
     */
    public int coinChangeOptimized(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;

        // Sort coins to enable early termination
        Arrays.sort(coins);

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin > i) break; // Early termination - remaining coins are larger
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange solution = new CoinChange();
        assert 3 == solution.coinChange(new int[]{1, 2, 5}, 11);
        assert 2 == solution.coinChange(new int[]{1, 2, 3}, 6);
        assert 0 == solution.coinChange(new int[]{1, 2, 3}, 0);
        assert -1 == solution.coinChange(new int[]{2}, 3);
        assert -1 == solution.coinChange(new int[]{2}, 1);
        assert 3 == solution.coinChange(new int[]{2, 5, 10, 11}, 27);
    }
}
