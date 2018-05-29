package Trees;

import java.util.ArrayList;

public class AVLNode {
	
	private String dato;
	private AVLNode left;
	private AVLNode right;
	private int height;
	public ArrayList<Integer> index = new ArrayList<Integer>();
	

	public AVLNode(String dato, int i){
		setDato(dato);
	    setLeft(null);
	    setRight(null);
	    setHeight(0);
	    index.add(i);
	}


	public String getDato() {
		return dato;
	}


	public void setDato(String dato) {
		this.dato = dato;
	}


	public AVLNode getLeft() {
		return left;
	}


	public void setLeft(AVLNode left) {
		this.left = left;
	}


	public AVLNode getRight() {
		return right;
	}


	public void setRight(AVLNode right) {
		this.right = right;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}
	
}