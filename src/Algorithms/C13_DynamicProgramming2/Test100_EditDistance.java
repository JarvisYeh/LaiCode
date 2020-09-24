package Algorithms.C13_DynamicProgramming2;

public class Test100_EditDistance {
	/**
	 * input两个string，通过replace，insert，delete，使得两个string完全相同
	 * 最少需要多少步
	 **/

	/**
	 * Solution 1: DFS
	 * Time Complexity: O(n*3^(2n))
	 * 	worst case 2n层，每个节点3个children，每个节点都call substring
	 **/
	public int editDistanceI(String one, String two) {
		// base case
		if (one.length() == 0) {
			return two.length();
		}
		if (two.length() == 0) {
			return one.length();
		}

		// one: sfgh ; two: asth

		// replace: afgh -> asth == fgh -> sth
		// replace是同时删除，如果当前两字符相同可以直接同时删除不需要insert步骤
		int replace = (one.charAt(0) == two.charAt(0) ? 0 : 1) + editDistanceI(one.substring(1), two.substring(1));

		// insert: asfgh -> asth == sfgh -> sth
		int insert = 1 + editDistanceI(one, two.substring(1));

		// delete: fgh -> asth
		int delete = 1 + editDistanceI(one.substring(1), two);

		return Math.min(replace, Math.min(insert, delete));
	}

	/**
	 * Solution 2: DP
	 * M[i][j]代表s1的前i个字母转换为s2的前j个字母最少需要多少步
	 * Time Complexity: O(m*n)
	 * Space Complexity: O(m*n)
	 **/
	public int editDistanceII(String one, String two) {
		int[][] M = new int[one.length() + 1][two.length() + 1];

		// base cases
		// s1 的前i个字母转为 s2 的前0个字母最少需要i步
		for (int i = 0; i <= one.length(); i++) {
			M[i][0] = i;
		}
		// s1 的前0个字母转为 s2 的前j个字母最少需要j步
		for (int j = 0; j <= two.length(); j++) {
			M[0][j] = j;
		}

		// induction rule
		for (int i = 1; i <= one.length(); i++) {
			for (int j = 1; j <= two.length(); j++) {
				// one: [0, i) to two: [0, j)
				if (one.charAt(i - 1) == two.charAt(j - 1)) {
					// one: "xxxxxxxxxx a"
					// two: "yyyyyy a"
					M[i][j] = M[i - 1][j - 1];
				} else {
					// one: "xxxxxxxxxx a"
					// two: "yyyyyy b"

					// replace -> "xxxxxxxxxx b" "yyyyyy b"
					int replace = 1 + M[i - 1][j - 1];

					// delete -> "xxxxxxxxxx " "yyyyyy b"
					int delete = 1 + M[i - 1][j];

					// delete -> "xxxxxxxxxx ab" "yyyyyy b"
					int insert = 1 + M[i][j - 1];

					M[i][j] = Math.min(replace, Math.min(delete, insert));
				}
			}
		}

		return M[one.length()][two.length()];
	}

	public static void main(String[] args) {
		Test100_EditDistance test = new Test100_EditDistance();
		test.editDistanceII("a", "baaaaa");
	}

}
