package eg.edu.alexu.csd.filestructure.avl.implementations;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.INode;

import java.util.LinkedList;

import java.util.Queue;

/**
 * @author Target .
 * @param <T>
 *            .
 */
public class MyAVLTree<T extends Comparable<T>> implements IAVLTree<T> {
	/**
	 * .
	 */
	private static final int ALLOWED_IMBALANCE = 1;
	/**
	 * .
	 */
	private static final int ZERO = 0;
	/**
	 *
	 */
	private static final int ONE = 1;

	/**
	 * .
	 */
	private boolean isDeleted = false;

	/**
	 * .
	 */
	private AVLNode<T> root;

	@Override
	final public void insert(final T key) {
		root = insertAVL(key, root);
	}

	/**
	 * @param key
	 *            .
	 * @param node
	 *            .
	 * @return .
	 */
	private AVLNode<T> insertAVL(final T key, final AVLNode<T> node) {
		if (node == null) {
			return new AVLNode<T>(key);
		}
		int compare = key.compareTo(node.getValue());
		if (compare < ZERO) {
			node.setLeftChild(insertAVL(key,
					(AVLNode<T>) node.getLeftChild()));
		} else if (compare >= ZERO) {
			node.setRightChild(insertAVL(key,
					(AVLNode<T>) node.getRightChild()));
		}
		return balance(node);
	}

	/**
	 * @param node
	 *            .
	 * @return .
	 */
	private AVLNode<T> balance(AVLNode<T> node) {
		if (node == null) {
			return node;
		}
		int leftHeight = getLeftHieght(node);
		int rightHeight = getRightHieght(node);
		if (leftHeight - rightHeight > ALLOWED_IMBALANCE) {
			int leftLeftHeight = -1;
			int leftRightHeight = -1;
			if (node.getLeftChild() != null) {
				leftLeftHeight = getLeftHieght((AVLNode<T>) node
						.getLeftChild());
				leftRightHeight = getRightHieght((AVLNode<T>)
						node.getLeftChild());
			}
			if (leftLeftHeight > leftRightHeight) {
				node = rotateLeft(node);
			} else {
				node = doubleRotationWithLeft(node);
			}
		} else if (rightHeight - leftHeight > ALLOWED_IMBALANCE) {
			int rightRightHeight = -1;
			int rightLeftHeight = -1;
			if (node.getRightChild() != null) {
				rightRightHeight = getRightHieght((AVLNode<T>)
						node.getRightChild());
				rightLeftHeight = getLeftHieght((AVLNode<T>)
						node.getRightChild());
			}
			if (rightRightHeight > rightLeftHeight) {
				node = rotateRight(node);
			} else {
				node = doubleRotationWithRight(node);
			}
		}
		leftHeight = getLeftHieght(node);
		rightHeight = getRightHieght(node);
		node.setHeight(Math.max(leftHeight, rightHeight) + ONE);
		return node;
	}

	/**
	 * @param node
	 *            .
	 * @return .
	 */
	private int getRightHieght(final AVLNode<T> node) {
		if (node.getRightChild() == null) {
			return -1;
		} else {
			return ((AVLNode<T>) node.getRightChild())
					.getHeight();
		}
	}

	/**
	 * @param node
	 *            .
	 * @return .
	 */
	private int getLeftHieght(final AVLNode<T> node) {
		if (node.getLeftChild() == null) {
			return -1;
		} else {
			return ((AVLNode<T>) node.getLeftChild()).getHeight();
		}
	}

	/**
	 * @param k2 root of the subtree to be rotated
	 * @return new root
	 */
	private AVLNode<T> rotateLeft(final AVLNode<T> k2) {
		AVLNode<T> k1 = (AVLNode<T>) k2.getLeftChild();
		k2.setLeftChild((AVLNode<T>) k1.getRightChild());
		k1.setRightChild(k2);
		k2.setHeight(Math.max(getLeftHieght(k2), getRightHieght(k2))
				+ ONE);
		k1.setHeight(Math.max(getLeftHieght(k1), k2.getHeight()) + ONE);
		return k1;
	}

	/**
	 * @param k2 root of subtree to be right-rotated
	 *
	 * @return new root
	 */
	private AVLNode<T> rotateRight(final AVLNode<T> k2) {
		AVLNode<T> k1 = (AVLNode<T>) k2.getRightChild();
		k2.setRightChild((AVLNode<T>) k1.getLeftChild());
		k1.setLeftChild(k2);
		k2.setHeight(Math.max(getLeftHieght(k2), getRightHieght(k2))
				+ ONE);
		k1.setHeight(Math.max(getRightHieght(k1), k2.getHeight())
				+ ONE);
		return k1;
	}

	/**
	 * @param k3 root of subtree to be double-rotated
	 *            .
	 * @return .
	 */
	private AVLNode<T> doubleRotationWithLeft(final AVLNode<T> k3) {
		k3.setLeftChild(rotateRight((AVLNode<T>) k3.getLeftChild()));
		return rotateLeft(k3);
	}

	/**
	 * @param k3 root of subtree to be double-rotated
	 *            .
	 * @return .
	 */
	private AVLNode<T> doubleRotationWithRight(final AVLNode<T> k3) {
		k3.setRightChild(rotateLeft((AVLNode<T>) k3.getRightChild()));
		return rotateRight(k3);
	}

	@Override
	public final boolean delete(final T key) {
		isDeleted = false;
		root = delete(key, (AVLNode<T>) getTree());
		return isDeleted;
	}

	/**
	 * @param key
	 *            .
	 * @param n
	 *            .
	 * @return .
	 */
	private AVLNode<T> delete(final T key, AVLNode<T> n) {
		if (n == null) {
			return n;
		}
		int compare = key.compareTo(n.getValue());
		if (compare < ZERO) {
			((AVLNode<T>) n).setLeftChild(delete(key,
					(AVLNode<T>) n.getLeftChild()));
		} else if (compare > ZERO) {
			((AVLNode<T>) n).setRightChild(delete(key,
					(AVLNode<T>) n.getRightChild()));
		} else if (n.getLeftChild() != null
				&& n.getRightChild() != null) {
			isDeleted = true;
			n.setValue(((AVLNode<T>) findMin(n.getRightChild()))
					.getValue());
			n.setRightChild(delete(n.getValue(),
					(AVLNode<T>) n.getRightChild()));
		} else if (n.getLeftChild() != null) {
			isDeleted = true;
			n = (AVLNode<T>) n.getLeftChild();
			n.setLeftChild(null);
		} else {
			isDeleted = true;
			n = (AVLNode<T>) n.getRightChild();
			if (n != null) {
				n.setRightChild(null);
			}
		}
		return balance(n);
	}

	@Override
	public final boolean search(final T key) {
		INode<T> current = root;
		while (current != null) {
			if (key.compareTo(current.getValue()) == ZERO) {
				return true;
			} else if (current.getLeftChild() != null
					&& key.compareTo(current.getValue())
					< ZERO) {
				current = current.getLeftChild();
			} else if (current.getRightChild() != null
					&& key.compareTo(current.getValue())
					> ZERO) {
				current = current.getRightChild();
			} else {
				break;
			}
		}
		return false;
	}

	@Override
	public final int height() {
		if (root == null) {
			return ZERO;
		}
		return root.getHeight() + ONE;
	}

	@Override
	public final INode<T> getTree() {
		return this.root;
	}

	/**
	 * @param iNode
	 *            print AVL Tree from the root.
	 */
	public final void printTreeInOrder(final INode<T> iNode) {
		// if (iNode == null) {
		// return;
		// }
		// System.out.println(iNode.getValue());
		// printTreeInOrder(iNode.getLeftChild());
		// printTreeInOrder(iNode.getRightChild());
		Queue<INode<T>> queue = new LinkedList<INode<T>>();
		queue.add(iNode.getLeftChild());
		queue.add(iNode.getRightChild());
		System.out.println(iNode.getValue());
		int i = ONE, j = ZERO;
		while (queue.peek() != null) {
			INode<T> current = queue.poll();
			System.out.print(current.getValue() + "    ");
			j++;
			if (j == 2 * i) {
				System.out.println();
				j = ZERO;
				i = 2 * i;
			}
			if (current.getLeftChild() != null) {
				queue.add(current.getLeftChild());
			}
			if (current.getRightChild() != null) {
				queue.add(current.getRightChild());
			}
		}
	}

	/**
	 * @param n
	 *            .
	 * @return .
	 */
	private INode<T> findMin(final INode<T> n) {
		if (n == null) {
			return null;
		}
		if (n.getLeftChild() == null) {
			return n;
		}
		return findMin(n.getLeftChild());
	}

}
