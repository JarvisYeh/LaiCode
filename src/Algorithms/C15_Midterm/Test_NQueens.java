package Algorithms.C15_Midterm;

import java.util.ArrayList;
import java.util.List;

public class Test_NQueens {
	public List<List<Integer>> nQueens(int n) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> curr = new ArrayList<>();

		nQueens(n ,curr, result);
		return result;
	}

	private void nQueens(int n, List<Integer> curr, List<List<Integer>> result) {
		// base case
		if (n == curr.size()) {
			result.add(new ArrayList<>(curr));
			return;
		}

		// check当前行的[0, n-1]列
		for (int col = 0; col < n; col++) {
			if (checkPass(col, curr)) {
				curr.add(col);
				nQueens(n, curr, result);
				curr.remove(curr.size() - 1);
			}
		}
	}


	private boolean checkPass(int col, List<Integer> curr) {
		// 当前位置还没有加入curr List
		// 所以当前list.size() = 需要加入的col的index
		int row = curr.size();
		for (int i = 0; i < curr.size(); i++) {
			if (curr.get(i) == col || Math.abs(curr.get(i) - col) == Math.abs(row - i)) {
				return false;
			}
		}
		return true;
	}

}
