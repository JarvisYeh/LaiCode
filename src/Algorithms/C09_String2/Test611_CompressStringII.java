package Algorithms.C09_String2;

public class Test611_CompressStringII {
	/**
	 * 因为String的长度会变小或者变长不确定
	 * Step 1:
	 * 	处理字符长度大于1的字符，使得string一定是缩小
	 * 	同时记录长度为1的字符的数量
	 *  [0, slow)要被保留的
	 *  [0, begin)检查过的，begin指向正在检查的相同字符串的开头
	 *  [begin, fast)相同字符区间， fast指向下一个字符串的开头
	 * Step 2:
	 * 	根据记录的字符为1的数量确定新array的长度
	 * 	处理长度为1的字符数量
	 * 	从后往前写，本意是防止在同一个array里改写的时候把还没处理的信息覆盖
	 * 		abc___ -> a1b1c1，写入a1时，b被覆盖
	 * 		这里开辟了新的array，但是依旧使用从后往前写的方法
	 * (slow, length - 1]要被保留的
	 **/
	public String compress(String input) {
		// corner cases
		if (input == null || input.length() == 0) {
			return input;
		}

		char[] charArray = input.toCharArray();
		// step 1:
		int slow = 0, fast = 0;
		int newLength = 0;
		while (fast < charArray.length) {
			int begin = fast;
			while (fast < charArray.length && charArray[fast] == charArray[begin]) {
				fast++;
			}
			// 此时fast指向下一串字符串的开头

			charArray[slow++] = charArray[begin];
			// 一个字符不做处理，更新newLength
			if (fast - begin == 1) {
				newLength += 2;
			}
			// 多个相同字符，压缩写入数量，更新newLength
			else {
				int len = copyDigits(charArray, slow, fast - begin);
				// 写了len位的数字，[0,slow)位保留的区间，使slow前进保留数字
				slow += len;
				newLength += len + 1;
			}
		}


		// step 2: 从后往前写，压缩单个字符
		char[] newArray = new char[newLength];
		// 旧array中被保留的是[0, slow)，所以fast指向旧array的有效末尾即slow - 1
		fast = slow - 1;
		// slow指向新array的末尾
		slow = newArray.length - 1;
		while (fast >= 0) {
			// case 1: 数字，直接保留
			if (Character.isDigit(charArray[fast])) {
				newArray[slow--] = charArray[fast--];
			}
			// case 2.1 字符，后面一位是字母，写入1和该字符
			// 同时考虑该字符就是最后一位，没有后面一位的情况（corner case）
			else if (slow == newArray.length - 1 || Character.isAlphabetic(charArray[slow+1])) {
				newArray[slow--] = '1';
				newArray[slow--] = charArray[fast--];
			}
			// case 2.2 字符，后面一位是数字，直接保留
			else {
				newArray[slow--] = charArray[fast--];
			}
		}
		return new String(newArray);
	}

	/**
	 * transform count to digit characters to input char array
	 * from index = start
	 * @param input
	 * @param start
	 * @param count
	 * @return
	 */
	private int copyDigits(char[] input, int start, int count) {
		int len = 0;
		for (int i=count; i>0; i/=10) {
			len++;
		}

		for (int i = len - 1; i>=0; i--) {
			input[start + i] = (char)(count % 10 + '0');
			count /= 10;
		}

		return len;
	}
}
