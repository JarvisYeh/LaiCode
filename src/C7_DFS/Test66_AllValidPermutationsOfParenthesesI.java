package C7_DFS;

import java.util.ArrayList;
import java.util.List;

public class Test66_AllValidPermutationsOfParenthesesI {
	/**
	 * Input: n represents the number of pair of parentheses
	 * Each level has states represents add ( or add )
	 * Level amount = total parenthesis amount
	 *
	 * To make parentheses valid
	 * only if left parenthesis < n, we could add ‘(’
	 * only if right parenthesis < left parenthesis, we could add ‘)’
	 * (left + right) represents the level
	 * Time Complexity: O(2^2n)
	 * Space Complexity: O(n)
	 **/
	public List<String> validParentheses(int n) {
		List<String> result = new ArrayList();
		if (n == 0) {
			return result;
		}
		StringBuilder sb = new StringBuilder();
		findValidParentheses(0, 0, n, sb, result);
		return result;
	}

	private void findValidParentheses(int left, int right, int n, StringBuilder sb, List<String> result) {
		if (left + right == 2 * n) {
			result.add(sb.toString());
		}

		// add ( with some premise
		if (left < n) {
			sb.append("(");
			findValidParentheses(left + 1, right, n, sb, result);
			sb.deleteCharAt(sb.length() - 1);
		}
		// add ) with some premise
		if (left > right) {
			sb.append(")");
			findValidParentheses(left, right + 1, n, sb, result);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

}
