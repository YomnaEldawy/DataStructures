package implementations;

import interfaces.INode;

public class Node<T extends Comparable<T>> implements INode<T> {

	INode<T> leftChild, rightChild, parent;
	int index;
	T value;
	
	@Override
	public INode<T> getLeftChild() {
		return leftChild;
	}

	@Override
	public void setLeftChild(INode<T> leftChild) {
		this.leftChild = leftChild;
		
	}

	@Override
	public INode<T> getRightChild() {
		return rightChild;
	}

	@Override
	public void setRightChild(INode<T> rightChild) {

		this.rightChild = rightChild;
	}

	@Override
	public INode<T> getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	@Override
	public void setParent(INode<T> parent) {
		this.parent = parent;
	}

	@Override
	public T getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return index;
	}

}
