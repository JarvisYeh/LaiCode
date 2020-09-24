package Algorithms.C10_BitRepresentation;

public class Test77_AllUniqueCharactersII {
	/**
	 * Unicode一共256个字符
	 * 每个int有32位，每位表示一个字符是否存在
	 * 一共需要8个int
	 * Space complexity比用hashmap少
	 * 	O(4)
	 **/
	public boolean allUnique(String word) {
		int[] map = new int[8];
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int row = c / 32;
			int col = c % 32;
			if ((map[row] >> col & 1) == 1) {
				return false;
			} else {
				map[row] |= 1 << col;
			}
		}
		return true;
	}

}
