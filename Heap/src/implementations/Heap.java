package implementations;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.IHeap;
import interfaces.INode;

public class Heap<T extends Comparable<T>> implements IHeap<T> {

	ArrayList<INode<T>> nodesList = new ArrayList<INode<T>>();

	public Heap() {
		INode<T> dummy = new Node<T>();
		nodesList.add(dummy);
	}

	@Override
	public INode<T> getRoot() {
		if (nodesList.size() < 2) {
			return null;
		}
		return nodesList.get(1);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return nodesList.size() - 1;
	}

	@Override
	public void heapify(INode<T> node) {

	}

	@Override
	public T extract() {
		return null;
	}

	@Override
	public void insert(T element) {
		Node<T> n = new Node<T>();
		n.setValue(element);
		n.setIndex(nodesList.size());
		nodesList.add(n);
		heapifyUp(n.getIndex());
		
	}

	@Override
	public void build(Collection<T> unordered) {

	}

	public void heapifyUp(int i) {
		if (i > 1
				&& (nodesList.get(i).getValue().compareTo(nodesList.get(i / 2)
						.getValue())) > 0) {
			swap(nodesList.get(i), nodesList.get(i / 2));
			heapifyUp(i / 2);
		}
	}

	public void swap(INode<T> n1, INode<T> n2) {
		INode<T> temp = n1;
		n1.setLeftChild(n2.getLeftChild());
		n1.setRightChild(n2.getRightChild());
		n1.setParent(n2.getParent());
		n2.setLeftChild(temp.getLeftChild());
		n2.setRightChild(temp.getRightChild());
		n2.setParent(temp.getParent());
		nodesList.set(n1.getIndex(), nodesList.get(n2.getIndex()));
		nodesList.set(n2.getIndex(), temp);
	}
	
	public void print() {

		for (int i = 1; i < nodesList.size(); i++) {
			System.out.println(nodesList.get(i).getValue());

		}
	}

}
