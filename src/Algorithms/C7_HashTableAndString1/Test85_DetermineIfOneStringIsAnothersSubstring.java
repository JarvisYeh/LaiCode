package Algorithms.C7_HashTableAndString1;

public class Test85_DetermineIfOneStringIsAnothersSubstring {
	/**
	 * Solution 1:
	 * 嵌套for loop
	 * 外层寻找subString[0]
	 * 里层判断找到的index = 0后续字母是否符合subString
	 **/
	public int strstrI(String large, String small) {
		// corner cases
		if (small == null || large == null) {
			return -1;
		}
		if (large.length() < small.length()) {
			return -1;
		}
		if (small.length() == 0) {
			return 0;
		}

		for (int i = 0; i <= large.length() - small.length(); i++) {
			if (large.charAt(i) == small.charAt(0)) {
				if (checkEquals(large, small, i)) {
					return i;
				}
			}
		}
		return -1;
	}

	private boolean checkEquals(String large, String small, int index) {
		for (int i=0; i<small.length(); i++) {
			if (large.charAt(index + i) != small.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Solution 2:
	 * Karb-Robin
	 * 如果都是小写字母，即a-z，则可以使用26进制来计算hash code
	 * 利用一个sliding windows，size = small.length()
	 * 每次计算一个window的hash code，检查其是否和small的hash code相同
	 * 	sliding window向后行进一位时
	 * 		只有两个元素变化，计算新window的hash code复杂度为O(1)
	 * Exmple:
	 * abcd，window size = 3:
	 * 	abc -> bcd
	 * 	1*26^0 + 2*26^1 + 3*26^2 -> 2*26^0 + 3*26^1 + 4*26^2
	 * 	即减去a为RHS的hash code，整体/26，再加上d为LHS的hash code
	 * Time Complexity: O(n)
	 * 该hashcode的缺点是int很容易溢出，只要small稍微长一点就会溢出
	 **/
	public int strstrII(String large, String small) {
		// corner cases
		if (small == null || large == null) {
			return -1;
		}
		if (large.length() < small.length()) {
			return -1;
		}
		if (small.length() == 0) {
			return 0;
		}

		int smallHashCode = getHashCode(small, 0, small.length());
		int windowHashCode = getHashCode(large, 0, small.length());
		if (smallHashCode == windowHashCode) {
			return 0;
		}
		for (int i=1; i<=large.length() - small.length(); i++) {
			windowHashCode -= large.charAt(i - 1) - 'a';
			windowHashCode /= 26;
			windowHashCode += (large.charAt(i + small.length() - 1) - 'a') * Math.pow(26, small.length() - 1);
			if (smallHashCode == windowHashCode) {
				return i;
			}
		}
		return -1;
	}


	private int getHashCode(String s, int start, int end) {
		int hashcode = 0;
		for (int i = start; i < end; i++) {
			hashcode += (s.charAt(i) - 'a')*Math.pow(26, i - start);
		}
		return hashcode;
	}

	/**
	 * Solution 3: use prime to solve overflow problem
	 * there might be collision
	 * to prevent collision, if hash equals, still need to check each char whether equals
	 * the time complexity to check hash equals is O(1), if not equal, just moves windows
	 * @param large
	 * @param small
	 * @return
	 */
	public int strstrIII(String large, String small) {
		// corner cases
		if (small == null || large == null) {
			return -1;
		}

		if (small.length() > large.length()) {
			return -1;
		}

		if (small.length() == 0) {
			return 0;
		}

		// large prime is chosen randomly
		// prime is chosen randomly
		int largePrime = 101;
		int prime = 31;
		int seed = 1;

		// n is the length of small String
		int n = small.length();

		int targetHash = small.charAt(0) % largePrime;
		for (int i = 1; i < n; i++) {
			// h(large[i + 1, i + 1 + (n - 1)]) = h(large[i, i + (n - 1)]) - h(large[i])*seed + h(large[i + (n - 1)])*1
			// seed = [ 31^(n - 1) ] mod 101
			// seed = ((((31 % 101)*31)%101.....)*31) % 101, do multiple and modulo operations (n - 1) times
			seed = moduleHash(seed, 0, prime, largePrime);
			// Calculate for the hash value of small String
			// equivalent to (((small[0]*31) + small[1])*31+...+small[n-1])*31
			//    difference is that mod 101 has to be done every time after multiple 31 and add the previous sum
			//    i = 0 => hash = (small[0]*31 % 101 + 0) % 101
			//    i = 1 => (hash*31 % 101 + small[1]) % 101
			//  hash =
			targetHash = moduleHash(targetHash, small.charAt(i), prime, largePrime);
		}

		// initialize the hash of large to 0
		int hash = 0;

		// use for loop to calculate the hash of first n length substring
		// detail is the same as the previous way to calculate the hash of small string
		for (int i = 0; i < n; i++) {
			hash = moduleHash(hash, large.charAt(i), prime, largePrime);
		}
		// check whether the first n length substring's hash is same as the small string
		// if hash equals, we still have to check whether it is just a collision or not
		// but in average, the Collision happens O(1) times
		if (hash == targetHash && equals(large, 0, small)) {
			return 0;
		}


		// move the slide window one position after another
		// [abcd]e => a[bcde]
		// h(bcde) = [(h(abcd) - h(a)*seed%LP)*seed%LP + h(e)*1] % LP
		// each time check for whether the hash of windows are the same as the hash of small string
		// if hash equals, to prevent collision of hash, we still have to check for whether they are equal indeed
		for (int i = 1; i <= large.length() - small.length(); i++) {
			// h(abcd)- h(a)*seed%LP could be negative, therefore, add a LP to maintain the positive middle value
			hash = nonNegative(hash - seed * large.charAt(i - 1) % largePrime, largePrime);
			// do [(h(abcd) - h(a)*seed%LP)*seed%LP + h(e)*1] % LP
			// temp = (h(abcd) - h(a)*seed%LP)
			// hash = [temp*seed%LP + h(e)*1] % LP
			hash = moduleHash(hash, large.charAt(i + small.length() - 1), prime, largePrime);

			if (hash == targetHash && equals(large, i, small)) {
				return i;
			}
		}
		return -1;
	}

	// get the mod hash
	private int moduleHash(int hash, int addition, int prime, int largePrime) {
		return (hash * prime % largePrime + addition) % largePrime;
	}

	/**
	 * check whether the small is the sub string of large start at 'start'
	 * check one character after another
	 * therefore, O(n)
	 * @param large
	 * @param start
	 * @param small
	 * @return true/false
	 */
	private boolean equals(String large, int start, String small) {
		for (int i = 0; i < small.length(); i++) {
			if (large.charAt(i + start) != small.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Transfer the hash to positive number by adding largePrime
	 * @param hash
	 * @param largePrime
	 * @return
	 */
	private int nonNegative(int hash, int largePrime) {
		if (hash < 0) {
			return hash += largePrime;
		}
		return hash;
	}



	public static void main(String[] args) {
		Test85_DetermineIfOneStringIsAnothersSubstring test = new Test85_DetermineIfOneStringIsAnothersSubstring();
		System.out.println(test.strstrII("aabbaabbabcbccaabbaabbaabbaabbcc", "abc"));
	}

}
