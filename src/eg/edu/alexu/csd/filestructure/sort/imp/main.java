package eg.edu.alexu.csd.filestructure.sort.imp;

import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
//		Heap heap = new Heap<Integer>();
//		heap.insert(1);
//		System.out.println(heap.getRoot().getValue());
//		System.out.println(heap.getRoot().getParent());
//		System.out.println(heap.toString());
		
		ArrayList<Integer> unordered = new ArrayList<Integer>();
		unordered.add(15);
		unordered.add(20);
		unordered.add(10);
		unordered.add(8);
		unordered.add(5);
		unordered.add(50);
		unordered.add(9);
		unordered.add(10);
		unordered.add(18);
		unordered.add(30);
		System.out.println(unordered.toString());
		Sort sort = new Sort();
		Heap h = (Heap) sort.heapSort(unordered);
		System.out.println(unordered.toString());
		System.out.println(h.toString());
//		sort.sortSlow(unordered);
//		sort.sortFast(unordered);
//		System.out.println(unordered.toString());
	}

}
