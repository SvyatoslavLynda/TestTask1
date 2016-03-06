package com.svdroid;

import static com.svdroid.Utils.buildTree;
import static com.svdroid.Utils.linkSameLevel;

public class Main {

	public static void main(String[] args) {
		int initialNodesCount = 1000000;
		int maxNodesCount = 10000000;
		int delta = 1000000;

		for (int nodesCount = initialNodesCount; nodesCount < maxNodesCount; nodesCount += delta) {
			int nodeValues[] = new int[nodesCount];
			for (int i = 0; i < nodeValues.length; i++) nodeValues[i] = i + 1;
			Node node = buildTree(nodeValues, 0);
			System.out.println();

			System.out.printf("Start testing tree with nodes: %d\n", nodesCount);
			System.out.println(timeTest(node));
			System.out.println(memoryTest(node));
		}
	}

	private static String timeTest(Node node) {
		long startTime = System.currentTimeMillis();
		linkSameLevel(node);
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;

		return String.valueOf("\ttime:\t" + elapsedTime);
	}

	private static String memoryTest(Node node) {
		linkSameLevel(node);
		Runtime runtime = Runtime.getRuntime();
		runtime.gc();
		long memory = runtime.totalMemory() - runtime.freeMemory();
		return String.valueOf("\tmemory:\t" + memory);
	}
}
