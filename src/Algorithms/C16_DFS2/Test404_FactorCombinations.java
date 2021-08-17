package Algorithms.C16_DFS2;

import java.util.ArrayList;
import java.util.List;

public class Test404_FactorCombinations {
	public List<List<Integer>> combinations(int target) {
		// get all factors of target
		List<Integer> factors = getFactors(target);

		List<List<Integer>> res = new ArrayList<>();
		List<Integer> curr = new ArrayList<>();
		if (factors.size() == 0) {
			return res;
		}
		DFS(0, target, factors, curr, res);
		return res;
	}

	private void DFS(int index, int remaining, List<Integer> factors, List<Integer> curr, List<List<Integer>> res) {
		// base case
		if (index == factors.size()) {
			if (remaining == 1) {
				res.add(new ArrayList<>(curr));
			}
			return;
		}

		int factor = factors.get(index);

		// not consider current factor
		DFS(index + 1, remaining, factors, curr, res);

		// consider current factor
		int size = curr.size();
		while (remaining % factor == 0) {
			remaining /= factor;
			curr.add(factor);									//吃
			DFS(index + 1, remaining, factors, curr, res);
		}
		// 删除新加入的这些factor
		curr.subList(size, curr.size()).clear();				//吐
	}

	private List<Integer> getFactors(int target) {
		List<Integer> factors = new ArrayList<>();
		for (int i = 2; i*i <= target; i++) {
			if (target % i != 0) continue;
			if (i*i == target) {
				factors.add(i);
			} else {
				factors.add(i);
				factors.add(target/i);
			}
		}
		return factors;
	}

	public static void main(String[] args) {
		Test404_FactorCombinations test = new Test404_FactorCombinations();
		System.out.println(test.combinations(354));
	}

}
