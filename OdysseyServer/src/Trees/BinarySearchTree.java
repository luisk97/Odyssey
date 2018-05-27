package Trees;

public class BinarySearchTree{
	
	private BSTNode root;
	
	public BinarySearchTree() {
		this.root = null;
	}

	public BSTNode getRoot() {
		return root;
	}

	public void setRoot(BSTNode root) {
		this.root = root;
	}
	
	public void add(String element) {
		this.root = this.add(element, this.root);
	}
	
	private BSTNode add(String element, BSTNode current) {
		if (current == null) {
			return new BSTNode(element);
		}
		if (element.compareTo(current.elemento) < 0) {
			current.left = this.add(element, current.left);
		}else if (element.compareTo(current.elemento) > 0) {
			current.right = this.add(element, current.right);
		}
		return current;
	}
	
	public BSTNode findMin() {
		if (this.root == null) {
			return null;
		}else {
			return findMin(this.root);
		}
	}
	
	private BSTNode findMin(BSTNode nodo) {
		if (nodo == null) {
			return null;
		}else if (nodo.left == null) {
			return nodo;
		}else {
			return findMin(nodo.left);
		}
	}
	
	public BSTNode delete(String element) {
		return delete(element, this.root);
	}
	
	public BSTNode delete(String element, BSTNode nodo) {
		if (nodo == null) {
			return nodo;
		}
		if (element.compareTo(nodo.elemento) < 0) {
			nodo.left = delete(element, nodo.left);
		}else if(element.compareTo(nodo.elemento) > 0) {
			nodo.right = delete(element, nodo.right);
		}else if (nodo.left != null && nodo.right != null) {
			nodo.elemento = findMin(nodo.right).elemento;
			nodo.right = delete(nodo.elemento, nodo.right);
		}else {
			nodo = nodo.left != null? nodo.left:nodo.right;
		}
		return nodo;
	}
	
	public String search(String element) {
		return search(element, root);
	}
	
	public String search(String element, BSTNode current) {
		if (current == null) {
			return "No se encuentra ese dato";
		}else {
			if (element.compareTo(current.elemento) < 0) {
				return search(element, current.left);
			}else if(element.compareTo(current.elemento) > 0) {
				return search(element, current.right);
			}else {
				return String.valueOf(current.elemento);
			}
		}
	}
	
}
