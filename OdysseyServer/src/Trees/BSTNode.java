package Trees;

import usuario.User;

public class BSTNode {
	
	public User elemento;
	public BSTNode right;
	public BSTNode left;
	
	public BSTNode(User element) {
		this.elemento = element;
		this.right = null;
		this.left = null;
	}

}
