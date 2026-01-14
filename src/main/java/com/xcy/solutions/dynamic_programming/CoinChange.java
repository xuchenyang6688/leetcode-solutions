package com.xcy.solutions.dynamic_programming;

import java.util.Arrays;

/**
 * 322. Coin Change
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
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
     * Time Complexity: O(amount* count of coins)
     * Space Complexity: O(amount)
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange (int[] coins, int amount) {
        // if the coin value is 1, for the given amount, the max coins count is amount, so set the max to amount+1;
        int max = amount+1;
        int[] dp = new int[amount+1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        // for each amount, it is the min of (amount - coins[0], amount-coins[1]... amount-coins[n-1])+1, and make sure coins[i]<=amount
        for (int i=1; i<=amount; i++){
            for (int j=0;j<coins.length; j++){
                if(coins[j]<=i){
                    dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amount] > amount? -1: dp[amount];
    }

    public static void main(String[] args) {
        CoinChange solution = new CoinChange();
        assert 3 == solution.coinChange(new int[]{1,2,5}, 11);
        assert 2 == solution.coinChange(new int[]{1,2,3}, 6);
        assert 0 == solution.coinChange(new int[]{1,2,3}, 0);
        assert -1 == solution.coinChange(new int[]{2}, 3);
        assert -1 == solution.coinChange(new int[]{2}, 1);
        assert 3 == solution.coinChange(new int[]{2,5,10,11}, 27);
    }
}
