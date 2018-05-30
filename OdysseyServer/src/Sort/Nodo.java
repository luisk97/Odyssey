package Sort;

public class Nodo {
	private Nodo next;
	private Nodo prev;
	private Song song;
	public Nodo(Song song){
		this.song = song;
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
	public Song getSong() {
		return song;
	}
	public void setSong(Song song) {
		this.song = song;
	}
}