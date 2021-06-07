package Algorithms.C11_Recursion2;

import java.util.ArrayList;
import java.util.List;

public class Test233_NQueens {
	/**
	 * 使用一个n size的int array，每个index中的值代表index行的皇后放置的column
	 * DFS
	 * N 层，每层代表对应的row
	 * N 节点，每个节点代表对应的column
	 * 第一层第一个节点代表放置在第一行第一列
	 *
	 * 同时需要剪枝
	 * 如果和之前放置的皇后在同一列或者同一对角线上，不能有该节点
	 * Time Complexity: O(n^n*n)
	 * Space Complexity: O(n)
	 **/
	public List<List<Integer>> nqueens(int n) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> curr = new ArrayList<>();
		helper(res, curr, n);
		return res;
	}

	private void helper(List<List<Integer>> res, List<Integer> curr, int n) {
		// base case
		// curr has n elements, meaning n queens are set correctly
		if (curr.size() == n) {
			res.add(new ArrayList(curr));
		}

// iterate through n columns in current row
		for (int i=0; i<n; i++) {
			if (checkPass(curr, i)) {
				curr.add(i);				//吃
				helper(res, curr, n);
				curr.remove(curr.size() - 1);	//吐
			}
		}
	}

	private boolean checkPass(List<Integer> curr, int col) {
		// current row of which queen to be set
		int row = curr.size();
		for (int i=0; i<curr.size(); i++) {
			// two queens at same column, check not pass
			if (curr.get(i) == col) {
				return false;
			}
			// two queens在同一斜线上，check not pass
			if (Math.abs(curr.get(i) - col) == row - i) {
				return false;
			}
		}
		return true;
	}

}
