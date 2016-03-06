package com.svdroid;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by SVDroid on 3/6/16.
 */
public class Utils {
	public static Node buildTree(int[] nodeValues, int i) {
		if (i >= nodeValues.length) return null;
		Node node = new Node(nodeValues[i]);
		node.left = buildTree(nodeValues, 2 * i + 1);
		node.right = buildTree(nodeValues, 2 * i + 2);

		return node;
	}

	public static void linkSameLevel(Node node) {
		Deque<Node> nodes = new ArrayDeque<Node>();

		nodes.addFirst(node);
		while (!nodes.isEmpty()) {
			node = nodes.removeLast();
			if (node.left != null) {
				nodes.addFirst(node.left);
				if (node.right != null) {
					node.left.level = node.right;
				} else {
					node.left.level = getNearestLevel(getNodeWithSubNodes(node));
				}
			}

			if (node.right != null) {
				nodes.addFirst(node.right);
				node.right.level = getNearestLevel(getNodeWithSubNodes(node));
			}
		}
	}

	private static Node getNearestLevel(Node node) {
		Node temp = null;

		if (node != null)
			if (node.left != null)
				temp = node.left;
			else if (node.right != null)
				temp = node.right;

		return temp;
	}

	private static Node getNodeWithSubNodes(Node node) {
		Node temp = node;

		while (temp.level != null)
			if (temp.level.right != null || temp.level.left != null)
				return temp.level;
			else
				temp = temp.level;

		return null;
	}
}