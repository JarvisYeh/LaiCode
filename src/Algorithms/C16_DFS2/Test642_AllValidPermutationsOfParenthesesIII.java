package Algorithms.C16_DFS2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Test642_AllValidPermutationsOfParenthesesIII {
	/**
	 * 在不同类型的括号基础上，加上priority
	 * 嵌套的优先级'{}' > '<>' > '()'
	 **/
	public List<String> validParenthesesIII(int l, int m, int n) {
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
		if (left[0] + right[0] == 2 * l && left[1] + right[1] == 2 * m && left[2] + right[2] == 2 * n) {
			res.add(new String(sb));
			return;
		}

		// for left Parenthesis
		// ( 优先级最低，可以加在任意括号里面
		// 不能在自己里面
		if (left[0] < l && (stack.isEmpty() || stack.peekFirst() != '(')) {
			sb.append('(');
			left[0]++;
			stack.offerFirst('(');
			DFS(left, right, l, m, n, stack, sb, res);
			sb.deleteCharAt(sb.length() - 1);
			left[0]--;
			stack.pollFirst();
		}
		// < 优先级比 ( 高，只能在 {} 里面
		// 不能在自己里面
		if (left[1] < m && (stack.isEmpty() || stack.peekFirst() == '{')) {
			sb.append('<');
			left[1]++;
			stack.offerFirst('<');
			DFS(left, right, l, m, n, stack, sb, res);
			sb.deleteCharAt(sb.length() - 1);
			left[1]--;
			stack.pollFirst();
		}
		// { 优先级最高，只能独立存在
		// 不能在自己里面
		if (left[2] < n && stack.isEmpty()) {
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
}
