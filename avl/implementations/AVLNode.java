package eg.edu.alexu.csd.filestructure.avl.implementations;

import eg.edu.alexu.csd.filestructure.avl.INode;

/**
 * @author AyaAndYomna .
 * @param <T>
 *            .
 */
public class AVLNode<T extends Comparable<T>> implements INode<T> {
	/**
	 * .
	 */
	private AVLNode<T> leftChild, rightChild;
	/**
	 * .
	 */
	private int height;
	/**
	 * .
	 */
	private T nodeValue;
	/**
	 *
	 * @param element value for the node
	 */
	public AVLNode(final T element) {
		this.height = 0;
		nodeValue = element;
	}

	/**
	 * @param node
	 *            .
	 */
	public final void setLeftChild(final AVLNode<T> node) {
		this.leftChild = node;
	}

	@Override
	public final INode<T> getLeftChild() {
		return leftChild;
	}

	/**
	 * @param node right child
	 *            .
	 */
	public final void setRightChild(final AVLNode<T> node) {
		this.rightChild = node;
	}

	@Override
	public final INode<T> getRightChild() {
		return rightChild;
	}

	@Override
	public final T getValue() {
		return nodeValue;
	}

	@Override
	public final void setValue(final T value) {
		this.nodeValue = value;
	}

	/**
	 * @return height of the node
	 */
	public final int getHeight() {

		return this.height;

	}

	/**
	 * @param height1 height of the node
	 *            .
	 */
	public final void setHeight(final int height1) {
		this.height = height1;
	}

}
