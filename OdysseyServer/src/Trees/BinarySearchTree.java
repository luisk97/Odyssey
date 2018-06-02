package Trees;

import usuario.User;

public class BinarySearchTree {

	private static BSTNode root;

	public BinarySearchTree() {
		this.root = null;
	}

	public BSTNode getRoot() {
		return root;
	}

	public void setRoot(BSTNode root) {
		this.root = root;
	}

	public static void add(User element) {
		root = add(element, root);
	}

	private static BSTNode add(User element, BSTNode current) {
		if (current == null) {
			return new BSTNode(element);
		}
		if (element.getUsuario().compareTo(current.elemento.getUsuario()) < 0) {
			current.left = add(element, current.left);
		} else if (element.getUsuario().compareTo(current.elemento.getUsuario()) > 0) {
			current.right = add(element, current.right);
		}
		return current;
	}

	public BSTNode findMin() {
		if (this.root == null) {
			return null;
		} else {
			return findMin(this.root);
		}
	}

	private BSTNode findMin(BSTNode nodo) {
		if (nodo == null) {
			return null;
		} else if (nodo.left == null) {
			return nodo;
		} else {
			return findMin(nodo.left);
		}
	}

	public BSTNode delete(User element) {
		return delete(element, this.root);
	}

	public BSTNode delete(User element, BSTNode nodo) {
		if (nodo == null) {
			return nodo;
		}
		if (element.getUsuario().compareTo(nodo.elemento.getUsuario()) < 0) {
			nodo.left = delete(element, nodo.left);
		} else if (element.getUsuario().compareTo(nodo.elemento.getUsuario()) > 0) {
			nodo.right = delete(element, nodo.right);
		} else if (nodo.left != null && nodo.right != null) {
			nodo.elemento = findMin(nodo.right).elemento;
			nodo.right = delete(nodo.elemento, nodo.right);
		} else {
			nodo = nodo.left != null ? nodo.left : nodo.right;
		}
		return nodo;
	}

	public static boolean search(String element) {
		return search(element, root);
	}

	public static boolean search(String element, BSTNode current) {
		if (current == null) {
			return false;
		} else {
			if (element.compareTo(current.elemento.getUsuario()) < 0) {
				return search(element, current.left);
			} else if (element.compareTo(current.elemento.getUsuario()) > 0) {
				return search(element, current.right);
			} else {
				return true;
			}
		}
	}

	public static User searchUser(String element, BSTNode current) {
		if (current == null) {
			return null;
		} else {
			if (element.compareTo(current.elemento.getUsuario()) < 0) {
				return searchUser(element, current.left);
			} else if (element.compareTo(current.elemento.getUsuario()) > 0) {
				return searchUser(element, current.right);
			} else {
				return current.elemento;
			}
		}
	}

	public static User searchUser(String user) {
		return searchUser(user, root);

	}
}
