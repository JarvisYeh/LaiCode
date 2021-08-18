package Algorithms.C25_Tire;

import java.util.*;

public class Test431_WordSearchII {
	// Method 1: brute force
	// TC: O(m*n*K*[4^L])
	// SC: O(m*n + L)
	public List<String> findWordsI(char[][] board, String[] words) {
		HashSet<String> res = new HashSet<>();
		for (String word : words) {
			if (findExist(board, word)) {
				res.add(word);
			}
		}
		return Arrays.asList(res.toArray(new String[0]));
	}

	private boolean findExist(char[][] board, String word) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				// visited not include node [i, j]
				boolean[][] visited = new boolean[board.length][board[0].length];
				// if found the word in (i, j), no need to check other positions
				if (helperI(board, i, j, word, 0, visited)) {
					return true;
				}
			}
		}
		return false;
	}

	// in visited matrix
	// the position from start position to (i, j), not include (i, j), are true
	private boolean helperI(char[][] board, int i, int j, String word, int idx, boolean[][] visited) {
		// base case 1: reach the end of word
		if (idx == word.length()) return true;

		// base case 2: out of boundary || (i, j) visited before
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) {
			return false;
		}

		// base case 3: not same char
		if (word.charAt(idx) != board[i][j]) return false;

		visited[i][j] = true;
		int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
		for (int[] dir : dirs) {
			int newI = i + dir[0];
			int newJ = j + dir[1];
			if (helperI(board, newI, newJ, word, idx + 1, visited)) {
				return true;
			}
		}
		visited[i][j] = false;
		return false;
	}

	// Method 2: together with Trie
	// TC: O(m*n*[4^L])
	// SC: O(m*n + L)
	public List<String> findWordsII(char[][] board, String[] words) {
		HashSet<String> res = new HashSet<>();
		// step 1: build a trie - O(K*L)
		Trie trie = new Trie();
		for (String word : words) {
			trie.insert(word);	// O(L)
		}
		TrieNode root = trie.root;

		// step 2: for each start positions, dfs with Trie
		int m = board.length, n = board[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				boolean[][] visited = new boolean[m][n];
				helperII(board, i, j, root, visited, new StringBuilder(), res);
			}
		}
		return Arrays.asList(res.toArray(new String[0]));
	}

	// sb contains letters from start position to (i, j), not include (i, j)
	// curr is the prefix node corresponds to sb
	private void helperII(char[][] board, int i, int j, TrieNode curr, boolean[][] visited,
						  StringBuilder sb, HashSet<String> res) {
		if (curr.isWord) {
			res.add(sb.toString());
		}

		// base case 1:
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
			return;
		}

		// base case 2:
		if (visited[i][j]) {
			return;
		}

		TrieNode child = curr.children.get(board[i][j]);
		if (child == null) return;
		visited[i][j] = true;
		sb.append(board[i][j]);
		int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
		for (int[] dir : dirs) {
			int newI = i + dir[0];
			int newJ = j + dir[1];
			helperII(board, newI, newJ, child, visited, sb, res);
		}
		sb.deleteCharAt(sb.length() - 1);
		visited[i][j] = false;
	}

	public static void main(String[] args) {
		Test431_WordSearchII t = new Test431_WordSearchII();
//		System.out.println(
//				t.findWordsII(new char[][]{
//						{'a','e','e','c','c'},
//						{'c','d','b','d','b'},
//						{'d','d','e','e','a'},
//						{'a','c','d','d','a'},
//						{'b','d','c','d','a'}},
//						new String[]{"b","b","bde","eeed"})
//		);
		t.findWordsII(new char[][]{{'a'}}, new String[]{"a"});
	}
}
