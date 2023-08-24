package Algorithms.C12_DynamicProgramming1;

public class Test87_MaxProductOfCuttingRope {
	/**
	 * Solution 1: DFS
	 * Recursion tree level represents each cut position
	 * #1m, #2m, #3m, etc...
	 * 两个杈：cut/not cut
	 * @param length
	 * @return
	 */
	public int maxProductI(int length) {
		// question demand that there has to be at least one cut
		// 需要一个boolean flag来标记之前是否cut过
		boolean check_cut = false;
		return maxProductI(length, 1, 1, check_cut);
	}

	/**
	 * Recursion method
	 * @param length
	 * @param position, 当前确定cut/不cut的位置
	 * @param reserve, 目前为止没有计入prods的连续长度
	 * @param check_cut
	 * @return
	 */
	private int maxProductI(int length, int position, int reserve, boolean check_cut) {
		if (position == length) return reserve;

		// 如果cut，下一层reserve变为1，当前层将之前累计的reserve乘进prod中
		int cut = reserve * maxProductI(length, position + 1, 1, true);
		// 如果不cut，下一层reserve + 1
		int not_cut = maxProductI(length, position + 1, reserve + 1, check_cut);

		// 如果是最后一个位置，并且之前没有cut，当前位置一定要cut
		if (position == length - 1 && !check_cut) {
			return cut;
		}
		// 否则，返回cut与不cut的较大值
		else {
			return Math.max(cut, not_cut);
		}
	}

	/**
	 * Solution 2: DFS
	 * 一共n层
	 * 每个杈代表保留k米不再切，k范围[1,n-1]，是int
	 * <p>
	 * Example:
	 * rope(8)
	 * |                    \				       \	           \\\	  	\
	 * max(7, rope(7))*1	max(7, rope(6))*2	max(5, rope(5))*3  ...   max(1, rope(1))*7
	 * ...
	 */
	public int maxProductII(int length) {
		// corner case: 1m绳子不能切，return 0
		if (length <= 1) {
			return 0;
		}

		int maxProd = 0;
		// i是保留的不再切的长度
		for (int i=1; i<length; i++) {
			// 右边保留i m
			// 左边两种可能：[子节点返回的prod]和[左边全部保留]取两者最大值
			int prod = i * Math.max(length - i, maxProductII(length - i));
			maxProd = Math.max(maxProd, prod);
		}

		return maxProd;
	}

	/**
	 * Solution 3: DF
	 * 左大端，右小段
	 * 大端是可以继续切的，小段是保留的
	 * M[i]代表i米长的绳子至少切一刀最大乘积
	 * 每次计算M[i]时候
	 *  	将i米分割为左大段（可切）和右小段（不切）
	 *  		左大段 = Math.max(左大段,M[左大段])
	 *  		右小段 = 右小段
	 * @param length
	 * @return
	 */
	public int maxProductIII(int length) {
		int[] M = new int[length + 1];

		// invalid corner case
		// M[2] = Math.max(2, M[1]) * 1，所以实际上M[1]的值会被max所舍弃，只要初始设定小于2即可
		M[0] = -1;
		M[1] = -1;

		for (int i=2; i<M.length; i++) {
			// consider the rope from [0, i]
			int maxPord = 0;
			for (int j=1; j<i; j++) {
				// 左大段
				int leftSegment = j;
				// 右小段
				int rightSegment = i - j;
				// 计算当前左大段与右小段乘积
				int prod = Math.max(leftSegment, M[leftSegment]) * rightSegment;
				// 更新最大乘积
				maxPord = Math.max(maxPord, prod);
			}
			M[i] = maxPord;
		}
		return M[length];
	}

	public static void main(String[] args) {
		Test87_MaxProductOfCuttingRope t = new Test87_MaxProductOfCuttingRope();
		System.out.println(t.maxProductII(10));
	}
}
