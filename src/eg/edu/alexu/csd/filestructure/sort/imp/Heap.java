package eg.edu.alexu.csd.filestructure.sort.imp;

import java.util.ArrayList;
import java.util.Collection;

import eg.edu.alexu.csd.filestructure.sort.IHeap;
import eg.edu.alexu.csd.filestructure.sort.INode;

public class Heap<T extends Comparable<T>> implements IHeap {
    private ArrayList<Comparable<T>> array;

    public Heap() {
        array = new ArrayList<Comparable<T>>();
    }
    
    private Heap(Heap h) {
    	this.array = new ArrayList<Comparable<T>>(h.array);
    }

    @Override
    public INode<T> getRoot() {
        INode<T> root = new Node<T>(0);
        if (array.size() == 0)
            return null;
        return root;
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public void heapify(INode node) {
    	if(node == null) return;
        Node<T> l = (Node<T>) node.getLeftChild();
        Node<T> r = (Node<T>) node.getRightChild();
        Node<T> largest;
        if(l != null && l.getIndex() < array.size() && l.getValue().compareTo(node.getValue()) > 0) {
            largest = l;
        } else {
        	largest = (Node<T>)node;
        }
        if(r!= null && r.getIndex() < array.size() && r.getValue().compareTo(largest.getValue()) > 0) {
            largest = r;
        }
        if(((Node<T>)node).getIndex() == largest.getIndex()) return;
        Comparable<T> temp = node.getValue();
        array.set(((Node<T>)node).getIndex(), largest.getValue());
        array.set(largest.getIndex(), temp);
        heapify(largest);
    }
    private void heapify(INode node, int size) {
    	if(node == null) return;
        Node<T> l = (Node<T>) node.getLeftChild();
        Node<T> r = (Node<T>) node.getRightChild();
        Node<T> largest ;
        if(l != null && l.getIndex() < size && l.getValue().compareTo(node.getValue()) > 0) {
            largest = l;
        } else {
        	largest = (Node<T>)node;
        }
        if(r != null && r.getIndex() < size && r.getValue().compareTo(largest.getValue()) > 0) {
            largest = r;
        }
        if(((Node<T>)node).getIndex() == largest.getIndex()) return;
        Comparable<T> temp = node.getValue();
        array.set(((Node<T>)node).getIndex(), largest.getValue());
        array.set(largest.getIndex(), temp);
        heapify(largest, size);
    }

    @Override
    public Comparable extract() {
        if(array.size() == 0) return null;
        Comparable<T> item = getRoot().getValue();
        array.set(0, array.get(array.size() - 1));
        array.remove(array.size() - 1);
        heapify(getRoot());
        return item;
    }

    @Override
    public void insert(Comparable element) {
    	if(element == null) return;
        array.add(element);
        Node<T> added = new Node<>(array.size() - 1);
        while(added.getParent() != null && added.getParent().getIndex() >= 0) {
            Node<T> parent = added.getParent();
            if(added.getValue().compareTo(parent.getValue()) > 0) {
                Comparable temp = parent.getValue();
                array.set(parent.getIndex(), added.getValue());
                array.set(added.getIndex(), temp);
                added = parent;
            } else {
            	break;
            }
        }
    }

    @Override
    public void build(Collection unordered) {
    	if(unordered == null) {
    		return;
    	}
        array = new ArrayList<Comparable<T>>(unordered);
        int i = array.size() / 2;
        while(i >= 0) {
            Node<T> node = new Node<T>(i);
            heapify(node);
            i--;
        }
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
    	return new Heap<>(this);
    }
    
    private void buildArrayList(ArrayList unordered) {
    	if(unordered == null) {
    		return;
    	}
        array = unordered;
        int i = array.size() / 2;
        while(i >= 0) {
            Node<T> node = new Node<T>(i);
            heapify(node);
            i--;
        }
    }
    
    public void heapSort(ArrayList unordered) {
    	buildArrayList(unordered);
    	int i = array.size() - 1;
    	while (i >= 0) {
    		Comparable<T> top = array.get(0);
    		array.set(0, array.get(i));
    		array.set(i, top);
    		heapify(getRoot(), i);
    		i--;
    	}
    }

    class Node<T extends Comparable<T>> implements INode {
        private Integer index;
        
        public Node(Integer index) {
            this.index = index;
        }
        
        public Integer getIndex() {
            return index;
        }
        
        @Override
        public Node getLeftChild() {
        	int newIndex = (index * 2) + 1;
        	if(newIndex >= array.size()) return null;
            Node<T> lChild = new Node<T>(newIndex);
            return lChild;
        }

        @Override
        public Node getRightChild() {
        	int newIndex = (index * 2) + 2;
        	if(newIndex >= array.size()) return null;
            Node<T> rChild = new Node<T>(newIndex);
            return rChild;
        }

        @Override
        public Node getParent() {
        	if(index == 0) return null;
        	int newIndex = (index - 1) / 2;
            Node<T> parent = new Node<T>(newIndex);
            return parent;
        }

        @Override
        public Comparable getValue() {
        	if(index >= array.size()) {
        		return null;
        	}
            return array.get(index);
        }

        @Override
        public void setValue(Comparable value) {
        	if(index >= array.size()) return;
            array.set(index, value);
        }

       
    }
    
    @Override
    public String toString() {
    	String s = "";
    	for(int i = 0; i < array.size(); i++) {
    		s += array.get(i) + " ";
    	}
    	return s;
    }

}

