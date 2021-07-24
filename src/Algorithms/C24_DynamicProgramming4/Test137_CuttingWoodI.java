package Algorithms.C24_DynamicProgramming4;

public class Test137_CuttingWoodI {
	public int minCost(int[] cuts, int length) {
		int[] helper = new int[cuts.length + 2];
		helper[0] = 0;
		helper[helper.length - 1] = length;
		for (int i = 0; i < cuts.length; i++) {
			helper[i + 1] = cuts[i];
		}

		// minCost[i][j] means the minimal cost of cutting wood piece {helper[i], helper[j]}
		int[][] minCost = new int[helper.length][helper.length];
		// i < j, from left to right, from bottom to up
		for (int i = minCost.length - 2; i >= 0; i--) {
			for (int j = i + 1; j < minCost.length; j++) {
				// for the wood piece that can not be cut anymore, cost = 0
				if (j == i + 1) {
					minCost[i][j] = 0;
				} else {
					// one cut of wood piece [i, j] into [i, t], [t, j]
					// min is the cost of cutting wood piece [i, t] and [t, j] among all t
					int min = Integer.MAX_VALUE;
					for (int t = i + 1; t < j; t++) {
						min = Math.min(min, minCost[i][t] + minCost[t][j]);
					}
					// add the cost of cut wood [i, j]
					minCost[i][j] = min + helper[j] - helper[i];
				}
			}
		}
		return minCost[0][helper.length - 1];
	}

	public static void main(String[] args) {
		Test137_CuttingWoodI t = new Test137_CuttingWoodI();
		System.out.println(t.minCost(new int[]{2,4,7}, 10));
	}
}
