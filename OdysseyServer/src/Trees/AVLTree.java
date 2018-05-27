package Trees;

public class AVLTree {
	
	private AVLNode root;
	
	public AVLTree() {
		this.root = null;
	}

	public void add(String element){
		root = add(element, root);
	}

	private AVLNode add(String element,AVLNode current){
		if(current == null ) {
			current = new AVLNode(element);
		}
		if (element.compareTo(current.dato) < 0) {
			current.left = add(element, current.left);
			if(height(current.left) - height(current.right) == 2) {
				if(element.compareTo(current.left.dato) < 0) {
					current = rotateWithLeftChild(current);
				}else {
					current = doubleWithLeftChild(current);
				}
			}
		}else if(element.compareTo(current.dato) > 0) {
			current.right = add(element, current.right);
			if(height(current.right) - height(current.left) == 2) {
				if(element.compareTo(current.right.dato) > 0) {
					current = rotateWithRightChild(current);
				}else {
					current = doubleWithRightChild(current);
				}
			}
		}else {}
		current.height = max(height(current.left), height(current.right)) + 1;
		return current;
	}

	private static int max(int leftHeight, int rightHeight){
		return leftHeight > rightHeight ? leftHeight:rightHeight;
	}

	private static AVLNode rotateWithLeftChild(AVLNode nodo2){
		AVLNode nodo1 = nodo2.left;
		nodo2.left = nodo1.right;
		nodo1.right = nodo2;
		nodo2.height = max(height(nodo2.left), height(nodo2.right)) + 1;
		nodo1.height = max(height(nodo1.left), nodo2.height) + 1;
		return nodo1;
	}

	private static AVLNode rotateWithRightChild(AVLNode nodo1){
		AVLNode nodo2 = nodo1.right;
		nodo1.right = nodo2.left;
		nodo2.left = nodo1;
		nodo1.height = max(height(nodo1.left), height(nodo1.right)) + 1;
		nodo2.height = max(height(nodo2.right), nodo1.height) + 1;
		return nodo2;
	}

	private static AVLNode doubleWithLeftChild(AVLNode nodo){
		nodo.left = rotateWithRightChild(nodo.left);
		return rotateWithLeftChild(nodo);
	}

	private static AVLNode doubleWithRightChild(AVLNode nodo){
		nodo.right = rotateWithLeftChild(nodo.right);
		return rotateWithRightChild(nodo);
	}

	private static int height(AVLNode current){
		return current == null? - 1:current.height;
	}
	
	public AVLNode findMin() {
		if (this.root == null) {
			return null;
		}else {
			return findMin(this.root);
		}
	}
	
	private AVLNode findMin(AVLNode nodo) {
		if (nodo == null) {
			return null;
		}else if (nodo.left == null) {
			return nodo;
		}else {
			return findMin(nodo.left);
		}
	}
	
	public AVLNode delete(String element) {
		return delete(element, this.root);
	}
	
	public AVLNode delete(String element, AVLNode nodo) {
		if (nodo == null) {
			return nodo;
		}
		if (element.compareTo(nodo.dato) < 0) {
			nodo.left = delete(element, nodo.left);
			if(height(nodo.right) - height(nodo.left) == 2) {
				if(element.compareTo(nodo.right.dato) > 0) {
					nodo = rotateWithRightChild(nodo);
				}else {
					nodo = doubleWithRightChild(nodo);
				}
			}
		}else if(element.compareTo(nodo.dato) > 0) {
			nodo.right = delete(element, nodo.right);
			if(height(nodo.left) - height(nodo.right) == 2) {
				if(element.compareTo(nodo.left.dato) < 0) {
					nodo = rotateWithLeftChild(nodo);
				}else {
					nodo = doubleWithLeftChild(nodo);
				}
			}
		}else if (nodo.left != null && nodo.right != null) {
			nodo.dato = findMin(nodo.right).dato;
			nodo.right = delete(nodo.dato, nodo.right);
		}else {
			nodo = nodo.left != null? nodo.left: nodo.right;
		}
		nodo.height = max(height(nodo.left), height(nodo.right)) + 2;
		return nodo;
	}
	
	public String search(String element) {
		return search(element, this.root);
	}
	
	private String search(String element, AVLNode current) {
		if (current == null) {
			return "No se encuentra ese dato";
		}else {
			if (element.compareTo(current.dato) < 0) {
				return search(element, current.left);
			}else if(element.compareTo(current.dato) > 0) {
				return search(element, current.right);
			}else {
				return String.valueOf(current.dato);
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//PUEDE SERVIR DE ALGO
	/*
	 * Imprime el arbol con el recorrido InOrden
	 */
	public void imprimir(){
		imprimir(root);
	}

	private void imprimir(AVLNode nodo){
		if ( nodo != null ){
			imprimir(nodo.right);
			System.out.println("["+ nodo.dato + "]");
			imprimir(nodo.left);
		}
	}

	public void imprimirXaltura(){
		imprimirXaltura ( root );
	}
	/*
	 * Imprime cada nodo linea por linea. Recorriendo el arbol desde
	 * el Nodo más a la derecha hasta el nodo más a la izquierda,
	 * y dejando una identacion de varios espacios en blanco segun su
	 * altura en el arbol
	 */
	private void imprimirXaltura(AVLNode nodo){
		if ( nodo != null ){
			imprimirXaltura(nodo.right);
			System.out.println( replicate(" ",height(root) - height(nodo)) +"["+ nodo.dato + "]");
			imprimirXaltura(nodo.left);
		}
	}
	/*
	 * Metodo estatico auxiliar que dada una cadena a y un enterto cnt
	 * replica o concatena esa cadena a, cnt veces
	 */
	private static String replicate (String a, int cnt){
		String x = new String("");

		for ( int i = 0; i < cnt; i++ )
			x = x + a;
		return x;
	}
	/*
	 * Obtiene la altura del arbol AVL
	 */
	public int calcularAltura(){
		return calcularAltura(root);
	}

	private int calcularAltura(AVLNode actual){
		if (actual == null)
			return -1;
		else
			return 1 + Math.max(calcularAltura(actual.left), calcularAltura(actual.right));
	}

	// Imprime el arbol por niveles. Comienza por la raiz.
	public void imprimirPorNiveles(){
		imprimirPorNiveles(root);
	}

	// Imprime el arbol por niveles.
	private void imprimirPorNiveles(AVLNode nodo){
		// Mediante la altura calcula el total de nodos posibles del árbol
		// Y crea una array cola con ese tamaño
		int max = 0;
		int nivel = calcularAltura();

		for ( ; nivel >= 0; nivel--)
			max += Math.pow(2, nivel);
		max++;      // Suma 1 para no utilizar la posicion 0 del array

		AVLNode cola[] = new AVLNode[ max ];

		// Carga en la pos 1 el nodo raiz
		cola[1] = nodo;
		int x = 1;
		// Carga los demas elementos del arbol,
		// Carga null en izq y der si el nodo es null
		// i aumenta de a 2 por q carga en izq y der los hijos
		// x aumenta 1, que son los nodos raiz - padre
		for (int i = 2; i < max; i += 2, x++){
			if (cola[x] == null){
				cola[i] = null;
				cola[i + 1] = null;
			}
			else{
				cola[i]   = cola[x].left;
				cola[i + 1] = cola[x].right;
			}
		}
		nivel = 0;
		int cont = 0;                       // contador para cada nivel
		int cantidad = 1;                   // cantidad de nodos por nivel
		int ultimaPosicion = 1;             // ultimaPosicion del nodo en la cola de cada nivel

		// Cuando i es = a 2^nivel hay cambio de nivel
		// 2 ^ 0 = 1 que es el nodo raiz
		for (int i = 1; i < max; i++){
			if(i == Math.pow(2, nivel)){
				// Nodo raiz tiene nivel 1, por eso (nivel + 1)
				System.out.print("\n Nivel " + (nivel) + ": ");
				nivel++;
			}
			if( cola[i] != null ){
				System.out.print("[" + cola[i].dato + "]");
				cont++;
			}
			if(ultimaPosicion == i  && cantidad == Math.pow(2, --nivel)){
				if(cantidad == 1)
					System.out.print(" Cantidad de nodos: " + cont + " (raiz)");
				else
					System.out.print(" Cantidad de nodos: " +  cont);
				cont = 0;
				cantidad *= 2;
				ultimaPosicion += (int)Math.pow(2, ++nivel);
			}
		}
	}
	

}
