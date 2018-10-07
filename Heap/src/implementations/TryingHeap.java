package implementations;

public class TryingHeap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Heap<Integer> h = new Heap<Integer>();
		h.insert(15);
		h.insert(19);
		h.insert(1344);
		h.insert(44);
		h.print();
	}

}
