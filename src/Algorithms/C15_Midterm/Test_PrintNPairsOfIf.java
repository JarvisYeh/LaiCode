package Algorithms.C15_Midterm;

import java.util.ArrayList;
import java.util.List;

public class Test_PrintNPairsOfIf {
	/**
	 * N个if{}
	 * 打印可能的嵌套组合
	 * step 1:
	 *  求出可能的组合,for N = 2: [{}{}, {{}}]
	 * step 2:
	 * 	打印该组合
	 * 	if {
	 * 	}
	 * 	if {
	 * 	}
	 * 	if {
	 * 	    if{
	 * 	    }
	 * 	}
	 * Time Complexity: O(2^2n*n)
	 * Space Complexity: O(2n) = O(n)
	 * @param N
	 */
	public void printNPairOfIf(int N) {
		List<Character> res = new ArrayList<>();
		getNPairCombination(0, 0, N, res);
	}

	private void getNPairCombination(int left, int right, int N, List<Character> res) {
		// base case
		if (left + right == 2*N) {
			printPairs(res);
			return;
		}

		if (left < N) {
			res.add('{');
			getNPairCombination(left + 1, right, N, res);
			res.remove(res.size() - 1);
		}

		if (right < left) {
			res.add('}');
			getNPairCombination(left, right + 1, N, res);
			res.remove(res.size() - 1);
		}

	}

	// 打印时候
	// 如果是{, 先打印indent再index++
	// 如果是}, 先indent--再打印indent
	private void printPairs(List<Character> res) {
		int indent = 0;
		for (int i = 0; i < res.size(); i++) {
			if (res.get(i) == '{') {
				for (int ind = 0; ind < indent; ind++) {
					System.out.print("  ");
				}
				System.out.println("if {");
				indent++;
			}

			if (res.get(i) == '}') {
				indent--;
				for (int ind = 0; ind < indent; ind++) {
					System.out.print("  ");
				}
				System.out.println("}");

			}
		}
		// 每个可能性之间空行
		System.out.println("");
	}

	public static void main(String[] args) {
		Test_PrintNPairsOfIf test = new Test_PrintNPairsOfIf();
		test.printNPairOfIf(2);
	}
}
