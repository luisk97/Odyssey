package Trees;

import java.util.ArrayList;

public class AVLTree {
	
	private AVLNode root;
	
	public AVLTree() {
		this.root = null;
	}

	public void add(String element, int i){
		root = add(element, root, i);
	}

	private AVLNode add(String element,AVLNode current, int i){
		if(current == null ) {
			current = new AVLNode(element, i);
		}
		if (element.compareTo(current.getDato()) < 0) {
			current.setLeft(add(element, current.getLeft(), i));
			if(height(current.getLeft()) - height(current.getRight()) == 2) {
				if(element.compareTo(current.getLeft().getDato()) < 0) {
					current = rotateWithLeftChild(current);
				}else {
					current = doubleWithLeftChild(current);
				}
			}
		}else if(element.compareTo(current.getDato()) > 0) {
			current.setRight(add(element, current.getRight(), i));
			if(height(current.getRight()) - height(current.getLeft()) == 2) {
				if(element.compareTo(current.getRight().getDato()) > 0) {
					current = rotateWithRightChild(current);
				}else {
					current = doubleWithRightChild(current);
				}
			}
		}else{
			boolean comp = true;
			for(int x = 0; x < current.index.size(); x++) {
				if(i == current.index.get(x)) {
					comp = false;
					break;
				}else {
					comp = true;
				}
			}
			if(comp) {
				current.index.add(i);
			}
			
		}
		current.setHeight(max(height(current.getLeft()), height(current.getRight())) + 1);
		return current;
	}

	private static int max(int leftHeight, int rightHeight){
		return leftHeight > rightHeight ? leftHeight:rightHeight;
	}

	private static AVLNode rotateWithLeftChild(AVLNode nodo2){
		AVLNode nodo1 = nodo2.getLeft();
		nodo2.setLeft(nodo1.getRight());
		nodo1.setRight(nodo2);
		nodo2.setHeight(max(height(nodo2.getLeft()), height(nodo2.getRight())) + 1);
		nodo1.setHeight(max(height(nodo1.getLeft()), nodo2.getHeight()) + 1);
		return nodo1;
	}

	private static AVLNode rotateWithRightChild(AVLNode nodo1){
		AVLNode nodo2 = nodo1.getRight();
		nodo1.setRight(nodo2.getLeft());
		nodo2.setLeft(nodo1);
		nodo1.setHeight(max(height(nodo1.getLeft()), height(nodo1.getRight())) + 1);
		nodo2.setHeight(max(height(nodo2.getRight()), nodo1.getHeight()) + 1);
		return nodo2;
	}

	private static AVLNode doubleWithLeftChild(AVLNode nodo){
		nodo.setLeft(rotateWithRightChild(nodo.getLeft()));
		return rotateWithLeftChild(nodo);
	}

	private static AVLNode doubleWithRightChild(AVLNode nodo){
		nodo.setRight(rotateWithLeftChild(nodo.getRight()));
		return rotateWithRightChild(nodo);
	}

	private static int height(AVLNode current){
		return current == null? - 1:current.getHeight();
	}
	
	public ArrayList<Integer> search(String element) {
		return search(element, this.root);
	}
	
	private ArrayList<Integer> search(String element, AVLNode current) {
		if (current == null) {
			return new ArrayList<Integer>();
		}else {
			if (element.compareTo(current.getDato()) < 0) {
				return search(element, current.getLeft());
			}else if(element.compareTo(current.getDato()) > 0) {
				return search(element, current.getRight());
			}else {
				return current.index;
			}
		}
	}
}
