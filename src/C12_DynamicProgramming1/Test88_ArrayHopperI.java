package C12_DynamicProgramming1;

public class Test88_ArrayHopperI {
	/**
	 * M[i]代表可以从i位置跳到终点
	 * @param array
	 * @return M[0]
	 */
	public boolean canJumpI(int[] array) {
		boolean[] M = new boolean[array.length];

		// 最后位置已经在重点，为true
		M[array.length - 1] = true;

		for (int i = array.length - 2; i >= 0; i--) {
			int steps = array[i];
			// 检查从j = i + 1到j = i + 1 + array[i]中
			// 是否有M[j] = true
			for (int j = i + 1; j <= i + steps; j++) {
				if (j < M.length && M[j]) {
					M[i] = true;
					break;
				}
			}
		}

		return M[0];
	}

	/**
	 * M[i]代表可以从0跳到i
	 * @param array
	 * @return M[array.length - 1]
	 */
	public boolean canJumpII(int[] array) {
		boolean[] M = new boolean[array.length];

		M[0] = true;
		for (int i = 1; i < array.length; i++) {
			M[i] = false;
			for (int j = 0; j < i; j++) {
				if (M[j] && j + array[j] >= i) {
					M[i] = true;
					break;
				}
			}
		}

		return M[array.length - 1];
	}

}
