package Algorithms.C07_DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Test73_CombinationsOfCoins {
	/**
	 * 给一个target钱数，给一个coins type array e.g.[99, 25, 5, 2]
	 * 返回每个coins数量的组合，使得总和正好等于target
	 *
	 *  这里在谈论时空复杂度的时候，target = 99, coins = [99, 25, 5, 2]
	 * 	Each level has states representing picking different types of coin. e.g. pick 99, pick 25, pick 5, pick 2
	 * 	Level amount = [target/minCoin] = 99/2 = 49
	 * Time Complexity: O(4^99)
	 * 	4叉，99层
	 * Space Complexity: O(99)
	 **/
	public List<List<Integer>> combinationsI(int target, int[] coins) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> comb = new ArrayList<>();
		// comb[i] means amount of coins[i] picked, init all of them to 0
		for (int coin: coins) comb.add(0);
		helper(coins, target, comb, res);
		HashSet<List<Integer>> set = new HashSet<>(res);
 		return new ArrayList<>(set);
	}

	private void helper(int[] coins, int remaining, List<Integer> comb, List<List<Integer>> res) {
		// base case
		if (remaining == 0) {
			res.add(new ArrayList<>(comb));
			return;
		}

		// each state meaning adding different type of coins
		for (int i = 0; i < coins.length; i++) {
			if (remaining >= coins[i]) {
				comb.set(i, comb.get(i) + 1);
				helper(coins, remaining - coins[i], comb, res);
				comb.set(i, comb.get(i) - 1);
			}
		}
	}

	/**
	 * 给一个target钱数，给一个coins type array e.g.[99, 25, 5, 2]
	 * 返回每个coins数量的组合，使得总和正好等于target
	 *
	 *  这里在谈论时空复杂度的时候，target = 99, coins = [99, 25, 5, 2]
	 * 	Each level has states representing how many coins for that certain type we want, e.g. one 25, two 25, two 25, ...
	 * 	Level amount = Coin type amount = 4
	 * Time Complexity: O(99^4)
	 * 	最多99个叉，四层
	 * Space Complexity: O(4)
	 **/
	public List<List<Integer>> combinationsII(int target, int[] coins) {
		List<List<Integer>> result = new ArrayList<>();
		if (target == 0) {
			return result;
		}
		List<Integer> comb = new ArrayList<>();
		findCombinations(target, 0, coins, result, comb);
		return result;
	}

	private void findCombinations(int moneyLeft, int index, int[] coins, List<List<Integer>> result, List<Integer> combination) {
		// base case
		// the smallest value coin level
		// if the moneyLeft can not be
		if (index == coins.length - 1) {
			// 检查最后的coin是否能正好凑成target
			// 凑不成则放弃这个组合
			if (moneyLeft % coins[index] == 0) {
				combination.add(moneyLeft);
				result.add(new ArrayList<>(combination));
				combination.remove(index);
			}
			return;
		}

		for (int i = 0; i * coins[index] <= moneyLeft; i++) {
			combination.add(i);
			findCombinations(moneyLeft - coins[index]*i, index + 1, coins, result, combination);
			combination.remove(index);
		}
	}

}
