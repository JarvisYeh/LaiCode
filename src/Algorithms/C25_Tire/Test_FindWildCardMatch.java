package Algorithms.C25_Tire;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test_FindWildCardMatch {
	public List<String> findWordMatch(TrieNode root, String word) {
		List<String> res = new ArrayList<>();
		// corner case
		if (root == null || word.isEmpty()) {
			return res;
		}
		helper(root, 0, word, new StringBuilder(), res);
		return res;
	}

	// curr物理意义
	// 包含sb中所有letters作为前缀所代表的node
	private void helper(TrieNode curr, int idx, String word,
						   StringBuilder sb, List<String> res) {
		// base case
		if (idx == word.length()) {
			// only append to res if that node is a word
			if (curr != null && curr.isWord) {
				res.add(sb.toString());
			}
			return;
		}

		// recursive rule 1: ?
		if (word.charAt(idx) == '?') {
			// iterate all children branch
			for (Map.Entry<Character, TrieNode> child : curr.children.entrySet()) {
				sb.append(child.getKey());
				helper(child.getValue(), idx + 1, word, sb, res);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		// recursive rule 2: curr node has char c branch
		else if (curr.children.get(word.charAt(idx)) != null) {
			sb.append(word.charAt(idx));
			helper(curr.children.get(word.charAt(idx)), idx + 1, word, sb, res);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
}
