package Sort;

public class Nodo {
	private Nodo next;
	private Nodo prev;
	private String dato;
	public Nodo(String dato){
		this.dato = dato;
		this.next = null;
		this.prev = null;
	}
	/**
	 * Getters and setters
	 * @return
	 */
	public Nodo getNext() {
		return next;
	}
	public void setNext(Nodo next) {
		this.next = next;
	}
	public Nodo getPrev() {
		return prev;
	}
	public void setPrev(Nodo prev) {
		this.prev = prev;
	}
	public String getDato() {
		return dato;
	}
	public void setDato(String dato) {
		this.dato = dato;
	}
}
