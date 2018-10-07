package eg.edu.alexu.csd.filestructure.avl.implementations;

import java.util.Random;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;

/**
 * .
 * 
 * @author AyaOsman And YomnaElDawy. .
 */
public class Test {
	/**
	 * .
	 */
	private static final int MAX = 10000000;
	/**
	 * .
	 */

	public static void main(String[] args) {
		Random rand = new Random();
		IAVLTree<Double> avl = new MyAVLTree<Double>();
		BST<Double> bst = new BST<Double>();
		int counter = 0;
		Double randomNumbers[] = new Double[MAX];
		long startTime = System.nanoTime();
		while (counter < MAX) {
			double n = rand.nextDouble();
			randomNumbers[counter] =(double) counter * n;
			counter++;
		}
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		totalTime = totalTime/1000000;
		System.out.println("Array generated in "+ totalTime + "ms" );
		// AVL time
		startTime = System.nanoTime();
		for (int i = 0; i < MAX; i++) {
			avl.insert(randomNumbers[i]);
		}
		endTime = System.nanoTime();
		totalTime = endTime - startTime;
		System.out.println("AVL total insertion time: " + totalTime/1000000 + "ms");
		// BST time
		startTime = System.nanoTime();
		for (int i = 0; i < MAX; i++) {
			bst.insert(randomNumbers[i]);
		}
		endTime = System.nanoTime();
		totalTime = endTime - startTime;
		System.out.println("BST total insertion time: " + totalTime/1000000 + "ms");
		
		startTime = System.nanoTime();
		for (int i = 0; i < MAX; i++) {
			bst.search(randomNumbers[i]);
		}
		endTime = System.nanoTime();
		totalTime = endTime - startTime;
		System.out.println("Total search time in bst = " + totalTime/1000000 + "ms");
		
		startTime = System.nanoTime();
		for (int i = 0; i < MAX; i++) {
			avl.search(randomNumbers[i]);
		}
		endTime = System.nanoTime();
		totalTime = endTime - startTime;
		System.out.println("Total search time in AVL = " + totalTime/1000000 + "ms");
	}
}