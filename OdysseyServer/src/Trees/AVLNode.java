package Trees;

public class AVLNode {
	
	public String dato;
	public AVLNode left;
	public AVLNode right;
	public int height;

	public AVLNode(String dato){
		this.dato = dato;
	    this.left = null;
	    this.right = null;
	    height   = 0;
	    }
	
	
}
