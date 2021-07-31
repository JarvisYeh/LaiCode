package Algorithms.C25_Tire;

import java.util.HashMap;

class Trie {
	TrieNode root;  // root node, represents empty string

	/** Initialize your data structure here. */
	public Trie() {
		root = new TrieNode();
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		// if the word already in Trie, no need to alter any nodes' count field
		if (search(word)) {
			return;
		}

		TrieNode curr = root;
		curr.count++;
		for (int i = 0; i < word.length(); i++) {
			TrieNode next = curr.children.get(word.charAt(i));
			if (next == null) {
				next = new TrieNode();
				curr.children.put(word.charAt(i), next);
			}
			next.count++;
			curr = next;
		}
		curr.isWord = true;
	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		TrieNode curr = root;
		for (int i = 0; i < word.length(); i++) {
			TrieNode next = curr.children.get(word.charAt(i));
			if (next == null) {
				return false;
			}
			curr = next;
		}
		return curr.isWord;
	}

	/** Returns if there is any word in the trie that starts with the given prefix. */
	public boolean startsWith(String prefix) {
		TrieNode curr = root;
		for (int i = 0; i < prefix.length(); i++) {
			TrieNode next = curr.children.get(prefix.charAt(i));
			if (next == null) {
				return false;
			}
			curr = next;
		}
		return true;
	}

	/** Returns if successfully delete a word from Trie. */
	public boolean delete(String word) {
		if (!search(word)) {
			return false;
		}

		TrieNode curr = root;
		curr.count--;
		for (int i = 0; i < word.length(); i++) {
			TrieNode next = curr.children.get(word.charAt(i));
			next.count--;
			if (next.count == 0) {
				curr.children.remove(next);
				return true;
			}
		}
		curr.isWord = false;
		return true;
	}
}

class TrieNode {
	boolean isWord;
	int count;	// amount of words in the subtree with this node as root
	HashMap<Character, TrieNode> children;

	public TrieNode() {
		isWord = false;
		children = new HashMap<>();
		count = 0;
	}
}
