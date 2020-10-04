package Algorithms.C16_DFS2;

import java.util.ArrayList;
import java.util.List;

public class Test640_AllSubsetsofSizeK {
	/**
	 * no duplication
	 **/
	public List<String> subSetsOfSizeK(String set, int k) {
		StringBuilder sb = new StringBuilder();
		List<String> res = new ArrayList<>();
		DFS(0, 0, set, sb, res, k);
		return res;
	}

	public void DFS(int index, int pick, String set, StringBuilder sb, List<String> res, int k) {
		// base case
		if (pick == k) {
			res.add(new String(sb));
			return;
		}
		// 提前剪枝
		if (index == set.length()) {
			return;
		}

		sb.append(set.charAt(index));
		DFS(index + 1, pick + 1, set, sb, res, k);
		sb.deleteCharAt(sb.length() - 1);
		DFS(index + 1, pick, set, sb, res, k);
	}

	public static void main(String[] args) {
		Test640_AllSubsetsofSizeK test = new Test640_AllSubsetsofSizeK();
		test.subSetsOfSizeK("apebc", 1);
	}

}
