package C13_DynamicProgramming2;

public class Test89_ArrayHopperII {
	/**
	 * 从起点最少需要跳多少步到终点
	 * M[i]代表需要从i跳到终点的最少步数
	 **/
	public int minJump(int[] array) {
		int[] M = new int[array.length];

		// 终点不需要跳已经在终点
		M[array.length - 1] = 0;

		for (int i = array.length - 2; i >= 0; i--) {
			int minSteps = Integer.MAX_VALUE;
			for (int j = i + 1; j <= i + array[i]; j++) {
				if (j < M.length && M[j] >= 0) {
					minSteps = Math.min(minSteps, 1 + M[j]);
				}
			}
			M[i] = minSteps == Integer.MAX_VALUE ? -1 : minSteps;
		}
		return M[0];
	}

}
