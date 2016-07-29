package com.folkcat.trie;

public class TrieTreeTest {

	public static void main(String args[]) {

		TrieNode root = new TrieNode('0');

		root.addWord("0995", root, "新疆：托克逊县");

		root.addWord("0856", root, "贵州：铜仁");

		root.addWord("0775", root, "广西：玉林");
		root.addWord("0592", root, "福建：厦门");

		String result = root.search("0775", root);

		System.out.println("0775:" + result);

		result = root.search("0592", root);

		System.out.println("0592:" + result);

	}

}