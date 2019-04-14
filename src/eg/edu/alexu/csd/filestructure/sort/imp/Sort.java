package eg.edu.alexu.csd.filestructure.sort.imp;

import java.util.ArrayList;

import eg.edu.alexu.csd.filestructure.sort.IHeap;
import eg.edu.alexu.csd.filestructure.sort.ISort;

public class Sort<T extends Comparable<T>> implements ISort {

    @Override
    public IHeap heapSort(ArrayList unordered) {
        Heap<T> heap = new Heap<T>();
    	if(unordered == null || unordered.size() == 0) return heap;
        heap.heapSort(unordered);
        return heap;
    }

    @Override
    public void sortSlow(ArrayList unordered) {
    	if(unordered == null || unordered.size() == 0) return;
        for(int i = 0; i < unordered.size(); i++) {
        	for(int j = 0; j < unordered.size() - i - 1; j++) {
        		Comparable current = (Comparable) unordered.get(j);
        		Comparable next = (Comparable) unordered.get(j + 1);
        		if(current.compareTo(next) > 0) {
        			unordered.set(j, next);
        			unordered.set(j + 1, current);
        		}
        	}
        }
        
    }

    @Override
    public void sortFast(ArrayList unordered) {
    	if(unordered == null || unordered.size() == 0) return;
        int l = 0;
        int r = unordered.size() - 1;
        mergeSort(unordered, l, r);
    }
    
    private void mergeSort(ArrayList array, int l, int r) {
    	if(l >= r) return;
    	int m = (l + r) / 2;
    	mergeSort(array, l, m);
    	mergeSort(array, m + 1, r);
    	merge(array, l, r, m);
    }
    
    private void merge(ArrayList array, int l, int r, int m) {
    	int i = m + 1;
    	int j = l;
    	ArrayList n = new ArrayList();
    	while(j <= m && i <= r) {
    		Comparable lower = (Comparable) array.get(j);
    		Comparable upper = (Comparable) array.get(i);
    		if(lower.compareTo(upper) > 0) {
    			n.add(upper);
    			i++;
    		} else {
    			n.add(lower);
    			j++;
    		}
    	}
    	while(j <= m) {
			Comparable lower = (Comparable) array.get(j);
			n.add(lower);
			j++;
		}
    	while(i <= r) {
    		Comparable upper = (Comparable) array.get(i);
    		n.add(upper);
			i++;
    	}
//    	System.out.println(n.toString());
    	for(i = 0; i < n.size(); i++) {
    		array.set(l + i, n.get(i));
    	}
    }

}
