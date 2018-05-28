package Sort;

public class LinkedList{
	private Nodo head;
	private Nodo tail;
	private int large;
	
	public LinkedList(){
		this.head = null;
		this.tail = null;
		this.large = 0;
	}
	/**
	 * Getter and setters
	 * @return
	 */
	public Nodo getHead() {
		return head;
	}
	public void setHead(Nodo head) {
		this.head = head;
	}
	public Nodo getTail() {
		return tail;
	}
	public void setTail(Nodo tail) {
		this.tail = tail;
	}
	public int getLarge() {
		return large;
	}
	public void setLarge(int large) {
		this.large = large;
	}
	/**
	 * Metodo para añadir datos
	 * @param dato
	 */
	public void add(String dato){
		large ++;
		if(this.head == null){
			this.head = new Nodo(dato);
			this.tail = head;
		}else{
			Nodo temp = this.tail;
			temp.setNext(new Nodo(dato));
			this.tail = temp.getNext();
		}
	}
	/**
	 * Metodo para eliminar datos
	 * @param dato
	 */
	public void delete(String dato){
		Nodo nodo = this.head;
		if(nodo.getDato() == dato){
			this.head = nodo.getNext();
			large --;
		}else{
			while(nodo.getNext()!= null){
				if(nodo.getNext().getDato() == dato){
					nodo.setNext(nodo.getNext().getNext());
					large --;
					if(nodo.getNext() == null){
						this.tail = nodo;
					}
					break;
				}else{
					nodo = nodo.getNext();
				}
			}
		}
	}
	/**
	 * elimina todos los datos de la lista
	 */
	public void deleteAll() {
		this.head.setNext(null);
		this.head = null;
		this.setLarge(0);
	}
	/**
	 * Matodo para imprimir la lista
	 */
	public void printList(){
		Nodo actual = this.head;
		while(actual != null){
			System.out.println(actual.getDato());
			actual = actual.getNext();
		}
	}
	/**
	 * obtiene el nodo de la posicion solicitada
	 * @param i
	 * @return
	 */
	public Nodo getNodo(int i) {
		int cont = 0;
		Nodo temp = this.head;
		while(cont < i) {
			temp = temp.getNext();
			cont ++;
		}
		return temp;
	}
}