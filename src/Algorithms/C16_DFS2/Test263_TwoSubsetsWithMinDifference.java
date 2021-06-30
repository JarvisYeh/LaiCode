package Algorithms.C16_DFS2;

public class Test263_TwoSubsetsWithMinDifference {
	public int minDifference(int[] array) {
		int sum = 0;
		for (int i : array) {
			sum += i;
		}
		int[] minDiff = new int[]{Integer.MAX_VALUE};
		DFS(0, 0, array, 0, minDiff, sum);
		return minDiff[0];
	}

	private void DFS(int index, int pick, int[] array, int subArraySum, int[] min, int sum) {
		// base case
		if (pick == array.length / 2) {
			min[0] = Math.min(Math.abs(sum - 2 * subArraySum), min[0]);
			return;
		}

		// 剪枝
		// pick的数量比n/2要小的时候，可以pick
		if (pick < array.length / 2) {
			DFS(index + 1, pick + 1, array, subArraySum + array[index], min, sum);
		}

		// 剩下可以pick的次数 < 剩余数字的数量
		// 可以不pick
		int leftToPick = array.length / 2 - pick;
		int leftItems = array.length - index - 1;
		if (leftItems > leftToPick) {
			DFS(index + 1, pick, array, subArraySum, min, sum);
		}
	}

}
