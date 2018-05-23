/**
 * 
 */
package List;

/**
 * @author Daniel Acuña Mora
 *
 */
public class SimpleNode<T> {
	private T obj;
	private SimpleNode<T> next;
	private SimpleNode<T> prev;

	/**
	 * @param name
	 * @param next
	 * @param prev
	 */
	public SimpleNode(T obj) {
		this.obj = obj;
	}

	public void linkNext(SimpleNode<T> next) {
		this.next = next;
	}

	public void linkPrev(SimpleNode<T> prev) {
		this.next = prev;
	}

	public T getObj() {
		return this.obj;
	}

	/**
	 * @return the next
	 */
	public SimpleNode<T> getNext() {
		return next;
	}

	/**
	 * @return the prev
	 */
	public SimpleNode<T> getPrev() {
		return prev;
	}

	/**
	 * 
	 */
}
