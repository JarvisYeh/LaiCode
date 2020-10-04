package Algorithms.C16_DFS2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Test179_AllValidPermutationsOfParenthesesII {
	/**
	 * 使用一个stack
	 * 当需要加右括号时，一定要和stack顶端的左括号匹配
	 **/
	public List<String> validParentheses(int l, int m, int n) {
		int[] left = new int[3];
		int[] right = new int[3];

		StringBuilder sb = new StringBuilder();
		List<String> res = new ArrayList<>();
		Deque<Character> stack = new ArrayDeque<>();

		DFS(left, right, l, m, n, stack, sb, res);
		return res;
	}

	private void DFS(int[] left, int[] right, int l, int m, int n, Deque<Character> stack, StringBuilder sb, List<String> res) {
		// base case
		if (left[0] + right[0] == 2*l && left[1] + right[1] == 2*m && left[2] + right[2] == 2*n) {
			res.add(new String(sb));
			return;
		}

		// for left Parenthesis
		if (left[0] < l) {
			sb.append('(');
			left[0]++;
			stack.offerFirst('(');
			DFS(left, right, l, m, n, stack, sb, res);
			sb.deleteCharAt(sb.length() - 1);
			left[0]--;
			stack.pollFirst();
		}

		if (left[1] < m) {
			sb.append('<');
			left[1]++;
			stack.offerFirst('<');
			DFS(left, right, l, m, n, stack, sb, res);
			sb.deleteCharAt(sb.length() - 1);
			left[1]--;
			stack.pollFirst();
		}

		if (left[2] < n) {
			sb.append('{');
			left[2]++;
			stack.offerFirst('{');
			DFS(left, right, l, m, n, stack, sb, res);
			sb.deleteCharAt(sb.length() - 1);
			left[2]--;
			stack.pollFirst();

		}

		// for right Parenthesis
		if (!stack.isEmpty() && stack.peekFirst() == '(') {
			sb.append(')');
			right[0]++;
			stack.pollFirst();
			DFS(left, right, l, m, n, stack, sb, res);
			sb.deleteCharAt(sb.length() - 1);
			right[0]--;
			stack.offerFirst('(');
		}
		if (!stack.isEmpty() && stack.peekFirst() == '<') {
			sb.append('>');
			right[1]++;
			stack.pollFirst();
			DFS(left, right, l, m, n, stack, sb, res);
			sb.deleteCharAt(sb.length() - 1);
			right[1]--;
			stack.offerFirst('<');
		}
		if (!stack.isEmpty() && stack.peekFirst() == '{') {
			sb.append('}');
			right[2]++;
			stack.pollFirst();
			DFS(left, right, l, m, n, stack, sb, res);
			sb.deleteCharAt(sb.length() - 1);
			right[2]--;
			stack.offerFirst('{');
		}
	}

	public static void main(String[] args) {
		Test179_AllValidPermutationsOfParenthesesII test = new Test179_AllValidPermutationsOfParenthesesII();
		System.out.println(test.validParentheses(3, 0, 0));
	}

}
