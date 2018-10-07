package interfaces;

public interface INode<T extends Comparable<T>> {
	/**
	* Returns the left child of the current element/node in the heap tree
	* @return
	INode wrapper to the left child of the current element/node
	*/
	INode<T> getLeftChild();
	void setLeftChild(INode<T> leftChild);

	/**
	* Returns the right child of the current element/node in the heap tree
	* @return
	INode wrapper to the right child of the current element/node
	*/
	INode<T> getRightChild();
	void setRightChild(INode<T> rightChild);

	/**
	* Returns the parent node of the current element/node in the heap tree
	* @return
	INode wrapper to the parent of the current element/node
	*/
	INode<T> getParent();
	void setParent(INode<T> parent);

	/**
	* Set/Get the value of the current node
	* @return
	Value of the current node
	*/
	T getValue();
	void setValue(T value);
	
	void setIndex(int index);
	int getIndex();
	
}
