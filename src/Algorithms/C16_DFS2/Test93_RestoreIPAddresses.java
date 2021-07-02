package Algorithms.C16_DFS2;

import java.util.ArrayList;
import java.util.List;

public class Test93_RestoreIPAddresses {
	public List<String> restoreIpAddresses(String s) {
		if (s.length() > 16) return new ArrayList<>();
		List<String> res = new ArrayList<>();
		dfs(s, 0, 0, new StringBuilder(), res);
		return res;
	}


	private void dfs(String s, int idx, int block, StringBuilder sb, List<String> res) {
		// base case 1
		// all 4 blocks are filled, and all digit is used
		if (idx == s.length() && block == 4) {
			// delete the last '.'
			sb.deleteCharAt(sb.length() - 1);
			res.add(new String(sb));
			// append it back
			sb.append('.');
			return;
		}

		// base case 2
		// all 4 blocks are filled but there are digits left
		// all digits are used but not all 4 blocks are filled
		if (idx == s.length() || block == 4) return;

		// if a block start with 0
		// it should be a whole block contains only 0
		if (s.charAt(idx) == '0') {
			sb.append("0.");
			dfs(s, idx + 1, block + 1, sb, res);
			sb.delete(sb.length() - 2, sb.length());
			return;
		}

		// take account for current block
		// we can take 1 digit 2 digit or 3 digit as long as it's less than 255
		int size = sb.length();
		int count = 0;
		for (int i = idx; i < idx + 3; i++) {
			if (i >= s.length()) {
				sb.delete(size, sb.length());
				return;
			}
			count = count*10 + s.charAt(i) - '0';
			if (count <= 255) {
				sb.append(s.charAt(i));
				// add '.'
				sb.append('.');
				dfs(s, i + 1, block + 1, sb, res);
				// delete '.'
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		sb.delete(size, sb.length());
	}

	public static void main(String[] args) {
		Test93_RestoreIPAddresses t = new Test93_RestoreIPAddresses();
		t.restoreIpAddresses("010010");
	}
}
