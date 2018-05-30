package Sort;

public class ListaEnlazada{
	private Nodo head;
	private Nodo tail;
	private int large;
	
	public ListaEnlazada(){
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
	public void add(Song song){
		large ++;
		if(this.head == null){
			this.head = new Nodo(song);
			this.tail = head;
		}else{
			Nodo temp = this.tail;
			temp.setNext(new Nodo(song));
			this.tail = temp.getNext();
		}
	}
	/**
	 * Metodo para eliminar datos
	 * @param dato
	 */
	public void delete(Song song) {
		Nodo nodo = this.head;
		if(nodo.getSong().getTitle().compareToIgnoreCase(song.getTitle()) == 0){
			this.head = nodo.getNext();
			large --;
		}else{
			while(nodo.getNext()!= null){
				if(nodo.getNext().getSong().getTitle().compareToIgnoreCase(song.getTitle()) == 0){
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
	public void delete(String str) {
		Nodo nodo = this.head;
		if(nodo.getSong().getTitle().compareToIgnoreCase(str) == 0){
			this.head = nodo.getNext();
			large --;
		}else{
			while(nodo.getNext()!= null){
				if(nodo.getNext().getSong().getTitle().compareToIgnoreCase(str) == 0){
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
		int x = 1;
		while(actual != null){
			System.out.println("Cancion " + x);
			System.out.println(actual.getSong().getTitle());
			System.out.println(actual.getSong().getArtist());
			System.out.println(actual.getSong().getAlbum());
			System.out.println("");
			actual = actual.getNext();
			x ++;
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
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7


//package Sort;
//
//public class LinkedList{
//	private Nodo head;
//	private Nodo tail;
//	private int large;
//	
//	public LinkedList(){
//		this.head = null;
//		this.tail = null;
//		this.large = 0;
//	}
//	/**
//	 * Getter and setters
//	 * @return
//	 */
//	public Nodo getHead() {
//		return head;
//	}
//	public void setHead(Nodo head) {
//		this.head = head;
//	}
//	public Nodo getTail() {
//		return tail;
//	}
//	public void setTail(Nodo tail) {
//		this.tail = tail;
//	}
//	public int getLarge() {
//		return large;
//	}
//	public void setLarge(int large) {
//		this.large = large;
//	}
//	/**
//	 * Metodo para añadir datos
//	 * @param song
//	 */
//	public void add(Song song){
//		large ++;
//		if(this.head == null){
//			this.head = new Nodo(song);
//			this.tail = head;
//		}else{
//			Nodo temp = this.tail;
//			temp.setNext(new Nodo(song));
//			this.tail = temp.getNext();
//		}
//	}
//	/**
//	 * Metodo para eliminar datos
//	 * @param name
//	 */
//	public void delete(String name){
//		Nodo nodo = this.head;
//		if(nodo.getSong().getTitle() == name){
//			this.head = nodo.getNext();
//			large --;
//		}else{
//			while(nodo.getNext()!= null){
//				if(nodo.getNext().getSong().getTitle() == name){
//					nodo.setNext(nodo.getNext().getNext());
//					large --;
//					if(nodo.getNext() == null){
//						this.tail = nodo;
//					}
//					break;
//				}else{
//					nodo = nodo.getNext();
//				}
//			}
//		}
//	}
//	/**
//	 * elimina todos los datos de la lista
//	 */
//	public void deleteAll() {
//		this.head.setNext(null);
//		this.head = null;
//		this.setLarge(0);
//	}
//	/**
//	 * Matodo para imprimir la lista
//	 */
//	public void printList(){
//		Nodo actual = this.head;
//		while(actual != null){
//			System.out.println(actual.getSong().getTitle());
//			actual = actual.getNext();
//		}
//	}
//	/**
//	 * obtiene el nodo de la posicion solicitada
//	 * @param i
//	 * @return
//	 */
//	public Nodo getNodo(int i) {
//		int cont = 0;
//		Nodo temp = this.head;
//		while(cont < i) {
//			temp = temp.getNext();
//			cont ++;
//		}
//		return temp;
//	}
//}