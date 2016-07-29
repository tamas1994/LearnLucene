package com.folkcat.trie;

/**
 * 
 * 给定一个固定电话号码，找出这个号码对应的区域。固定电话号码都是以0开始的多位数字，可以通过给定电话号码的前缀找出对已ing的区域
 * 
 * 如：
 * 
 * 0995：新疆：托克逊县
 * 
 * 0856：贵州：铜仁
 * 
 * 0775：广西：玉林
 * 
 * 可以采用数字搜索树算法快速查找电话号码前缀。
 */

public final class TrieNode {

	protected TrieNode[] children;// 孩子节点

	protected char splitChar;// 分隔字符

	protected String area;// 电话所属地区信息

	protected TrieNode(char splitchar) {

		children = new TrieNode[10];

		area = null;

		this.splitChar = splitchar;

	}

	/**
	 * 
	 * 加载词，形成数字搜索树
	 * 
	 * param:string输入的电话号码
	 * 
	 * param:root树根
	 * 
	 * area:所在区域
	 */

	void addWord(String string, TrieNode root, String area) {

		TrieNode tNode = root;

		// 第一个字符都是0，作为根节点

		for (int i = 1; i < string.length(); i++) {

			char c0 = string.charAt(i);

			int ind = Integer.parseInt(string.substring(i, i + 1));

			TrieNode tempNode = tNode.children[ind];

			if (null == tempNode) {

				tempNode = new TrieNode(c0);

			}

			if (i == string.length() - 1) {

				tempNode.area = area;

			}

			tNode.children[ind] = tempNode;

			tNode = tempNode;

		}

	}

	/**
	 * 
	 * 查询的过程对于查询词来说，从前往后一个字符一个字符的匹配。对于Trie树来说，是从根节点往下匹配的过程。
	 * 
	 * 从给定电话号码搜索前缀的方法如下：
	 */

	public String search(String tel, TrieNode root) {

		TrieNode tNode = root;

		for (int i = 1; i < tel.length(); i++) {

			tNode = tNode.children[(tel.charAt(i) - '0')];

			if (null != tNode.area) {

				return tNode.area;

			}

		}

		// 没找到

		return null;

	}

}