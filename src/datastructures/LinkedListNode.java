package datastructures;
/**
 * A class for nodes
 * @author Xijie Guo
 *
 */
public class LinkedListNode<T> {
	private T data;

	private LinkedListNode<T> next;
	
	/**
	 * Constructor of the linked list node
	 * @param data for data stored in this node
	 * @param next for the next node in the linked list
	 */
	public LinkedListNode(T data, LinkedListNode<T> next) {
		this.data = data;
	    this.next = next;
	}
	
	/**
	 * Set the data stored at this node.
	 */
	public void setData( T data ) {
		this.data = data;
	}
	 
	/**
	 * Get the data stored at this node.
	 */
	public T getData() {
		return data;
	}
	 
	/**
	 * Set the next pointer to passed node.
	 */
	public void setNext( LinkedListNode<T> node ) {
		this.next = node;
	}
	 
	/**
	 * Get (pointer to) next node.
	 */
	public LinkedListNode<T> getNext() {
		return next;
	}
	 
	/**
	 * Returns a String representation of this node.
	 */
	public String toString() {
		return data.toString();
	}
	
}

