package eg.edu.alexu.csd.filestructure.avl.implementations;

import eg.edu.alexu.csd.filestructure.avl.INode;

/**
 * @author Target
 * 
 * @param <T>
 .
 */
public class BST<T extends Comparable<T>>{
    /**
     * .
     */
    private boolean isDeleted = false;
    /**
     *. 
     */
    private static final int ZERO = 0; 


    /**
     * .
     */
    private AVLNode<T> root;

    public void insert(T key) {
        root = insertAVL(key, root);
    }

    /**
     * @param key
     *            .
     * @param node
     *            .
     * @return .
     */
    private AVLNode<T> insertAVL(T key, AVLNode<T> node) {
        if (node == null) {
            return new AVLNode<T>(key);
        }
        int compare = key.compareTo(node.getValue());
        if (compare < ZERO) {
            node.setLeftChild(insertAVL(key,
            (AVLNode<T>) node.getLeftChild()));
        } else if (compare > ZERO) {
            node.setRightChild(insertAVL(key,
            (AVLNode<T>) node.getRightChild()));
        }
        return node;
    }

        public boolean delete(T key) {
        isDeleted = false;
        root = delete(key, (AVLNode<T>) getTree());
        return isDeleted;
    }

    private AVLNode<T> delete(T key, AVLNode<T> n) {
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
            isDeleted= true;
            n.setValue(((AVLNode<T>)
            findMin(n.getRightChild())).getValue());
            n.setRightChild(delete(n.getValue(),
                (AVLNode<T>) n.getRightChild()));
        } else if (n.getLeftChild() != null) {
            isDeleted= true;
            n = (AVLNode<T>) n.getLeftChild();
            n.setLeftChild(null);
        } else {
            isDeleted= true;
            n = (AVLNode<T>) n.getRightChild();
            if (n != null)
                n.setRightChild(null);
        }
        return n;
    }

    public boolean search(T key) {
        INode<T> current = root;
        while (current != null) {
            if (key.compareTo(current.getValue()) == ZERO) {
                return true;
            } else if (current.getLeftChild() != null
                    && key.compareTo(current.getValue()) < ZERO) {
                current = current.getLeftChild();
            } else if (current.getRightChild() != null
                    && key.compareTo(current.getValue()) > ZERO) {
                current = current.getRightChild();
            } else {
                break;
            }
        }
        return false;
    }

    public int height() {
        if (root == null) {
            return ZERO;
        }
        return root.getHeight() + 1;
    }

    
    public INode<T> getTree() {
        return this.root;
    }

    /**
     * @param iNode
     *            print AVL Tree from the root.
     */
    public void printTreeInOrder(INode<T> iNode) {
        if (iNode == null) {
            return;
        }
        System.out.println(iNode.getValue());
        printTreeInOrder(iNode.getLeftChild());
        printTreeInOrder(iNode.getRightChild());
    }

    /**
     * @param n
     .
     * @return
     .
     */
    private INode<T> findMin(INode<T> n) {
        if (n == null ) {
            return null;
        }
        if (n.getLeftChild() == null) {
            return n;
        }
        return findMin(n.getLeftChild());
    }

}