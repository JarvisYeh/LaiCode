package Algorithms.C25_Tire;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test_FindWordsWithPrefix {
	public List<String> findWordsWithPrefix(TrieNode root, String prefix) {
		List<String> res = new ArrayList<>();
		TrieNode startNode = searchNode(root, prefix);
		if (startNode == null) return res;
		helper(startNode, new StringBuilder(prefix), res);
		return res;
	}

	// return the TrieNode with prefix = prefix
	// if not found, return null
	private TrieNode searchNode(TrieNode root, String prefix) {
		TrieNode curr = root;
		for (int i = 0; i < prefix.length(); i++) {
			TrieNode next = curr.children.get(prefix.charAt(i));
			if (next == null) {
				return null;
			}
			curr = next;
		}
		return curr;
	}

	// dfs the node and store all words in res
	// curr物理意义
	// 包含currPath中所有letters作为前缀所代表的node
	private void helper(TrieNode curr, StringBuilder currPath, List<String> res) {
		// base case: leaf node, actually no need to write that explicitly
		// if (curr == null) return;

		if (curr.isWord) {
			res.add(new String(currPath));
			// no need to return, there might be other words downward
		}

		for (Map.Entry<Character, TrieNode> entry : curr.children.entrySet()) {
			currPath.append(entry.getKey());
			helper(entry.getValue(), currPath, res);
			currPath.deleteCharAt(currPath.length() - 1);
		}
	}
}
