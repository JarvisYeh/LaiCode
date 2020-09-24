package Algorithms.C7_HashTableAndString1;

import java.util.HashSet;

public class Test68_MissingNumberI {
	/**
	 * An unsorted array contains from 1 to n
	 * one number is missing
	 * find that number in O(n) times
	 **/

	// Solution 1: 求1~n的和，减去所有值，剩下的即是missing number
	// 如果n太大，求和会overflow
	public int missingI(int[] array) {
		// array.length = n - 1
		// sum = (1+n)*n/2
		int sum = (1 + array.length + 1) * (array.length + 1) / 2;
		for (int i : array) {
			sum -= i;
		}
		return sum;
	}

	// Solution 2: 把每个值都放入hashset，再看1～n是否再hashset中
	public int missingII(int[] array) {
		HashSet<Integer> set = new HashSet<>();
		for (int i : array) {
			set.add(i);
		}
		for (int i=1; i<=array.length+1; i++) {
			if (!set.contains(i)) {
				return i;
			}
		}
		return -1;
	}

	// Solution 3: bitwise XOR
	// 1 xor 1 = 0, 2 xor 2 = 0,..., n xor n = 0
	// xor every number is array, and xor every number from 1~n
	// result = missing number xor 0 = missing number
	// 1~n中出了缺失数以外的数字都被1~n中相同的数xor为0
	public int missingIII(int[] array) {
		int result = 0;
		for (int i : array) {
			result ^= i;
		}
		for (int i=1; i<=array.length+1; i++) {
			result ^= i;
		}
		return result;
	}


}
