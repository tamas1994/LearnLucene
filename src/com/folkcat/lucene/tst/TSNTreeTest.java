package com.folkcat.lucene.tst;

public class TSNTreeTest {

	public static void main(String[] args) {
		TSTNode root = new TSTNode(' ');
		root.addWord("大", root);
		root.addWord("大学", root);
		root.addWord("大学生", root);
		root.addWord("活动", root);
		root.addWord("生活", root);
		root.addWord("中", root);
		root.addWord("中心", root);
		root.addWord("心", root);

		String sentence = "大学生活动中心";
		String ret = root.matchLong(sentence, 0, root);
		System.out.println(sentence + " match:" + ret);
		root.wordSegment(sentence, root);
	}
}