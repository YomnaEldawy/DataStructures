package eg.edu.alexu.csd.filestructure.avl.implementations;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.IDictionary;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Target .
 */
public class MyDictionary implements IDictionary {
	/**
	 * .
	 */
	private IAVLTree<String> tree;
	/**
	 * .
	 */
	private static final int ONE = 1;
	/**
	 * .
	 */
	private BufferedReader bufferReader;

	/**
	 * .
	 */
	public MyDictionary() {
		tree = new MyAVLTree<String>();
	}

	@Override
	public final void load(final File file) {
		try {
			String line;
			bufferReader = new BufferedReader(new FileReader(file));
			line = bufferReader.readLine();
			while (line != null) {
				insert(line);
				line = bufferReader.readLine();
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public final boolean insert(final String word) {
		if (!exists(word)) {
			tree.insert(word);
		}
		return false;
	}

	@Override
	public final boolean exists(final String word) {
		return tree.search(word);
	}

	@Override
	public final boolean delete(final String word) {
		if (tree.search(word)) {
			tree.delete(word);
		}
		return false;
	}

	@Override
	public final int size() {
		return size((AVLNode<String>) tree.getTree()) / 2;
	}

	@Override
	public final int height() {
		return tree.height();
	}

	/**
	 * @param iNode
	 *            .
	 * @return .
	 */
	public final int size(final AVLNode<String> iNode) {
		if (iNode == null) {
			return ONE;
		}
		return (size((AVLNode<String>) iNode.getLeftChild())
				+ size((AVLNode<String>) iNode.getRightChild())
				+ ONE);
	}

}
