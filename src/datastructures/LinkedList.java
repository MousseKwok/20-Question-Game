package datastructures;
/**
 * This class manages the activities we made to the linked list
 * @author Xijie Guo
 *
 */
public class LinkedList<T> {
	private LinkedListNode<T> head;
	
	/**
	 * Constructor of LinkedList
	 */
	public LinkedList() {
		this.head = null;
	}
	
	/**
	 * Get data stored in head node of list.
	 */
	public T getFirst() {
		return head.getData();
	}
	 
	/**
	 * Get the head node of the list.
	 */
	public LinkedListNode<T> getFirstNode() {
		return head;
	}
	 
	/**
	 * Get data stored in last node of list.
	 */
	public T getLast() {
		LinkedListNode<T> temp = head;
		while(temp.getNext()!= null) {
		    temp = temp.getNext();
		}
		return temp.getData();
	}
	 
	/**
	 * Get the tail node of the list.
	 */
	public LinkedListNode<T> getLastNode() {
		LinkedListNode<T> temp = head;
		while(temp.getNext() != null) {
			temp = temp.getNext();
		}
		return temp;
	}
	 
	/**
	 * Insert a new node with data at the head of the list.
	 * @param data for the data of the node
	 */
	public void insertFirst( T data ) {
		head = new LinkedListNode<T>(data, head);
	}
	 
	/**
	 * Insert a new node with data after currentNode
	 * @param currentNode for the current node
	 * @param data for the data of the node
	 */
	public void insertAfter( LinkedListNode<T> currentNode, T data ) {
		LinkedListNode<T> afterCurrent = currentNode.getNext();
		afterCurrent = new LinkedListNode<T>(data, afterCurrent);
		currentNode.setNext(afterCurrent);
		
	}
	 
	/**
	 * Insert a new node with data at the end of the list.
	 * @param data for the data of the node
	 */
	public void insertLast( T data ) {
		if(isEmpty()) {
			insertFirst(data);
		}

		else {
			LinkedListNode<T> last = getLastNode();
			insertAfter(last, data);
		}
	}
	 
	/**
	 * Remove the first node
	 */
	public void deleteFirst() {
		head = head.getNext();
	}
	 
	/**
	 * Remove the last node
	 */
	public void deleteLast() {
		LinkedListNode<T> current = head;
	    if(current.getNext() == null) {
			head = null;
		}
			
		else {
			while(current.getNext().getNext() != null) {
			current = current.getNext();
	        }
	        current.setNext(null);
		}
	}
			
	    
	/**
	 * Remove node following currentNode
	 * If no node exists (i.e., currentNode is the tail), do nothing
	 * @param currentNode for the current node in the list
	 */
	public void deleteNext( LinkedListNode<T> currentNode ) {
		LinkedListNode<T> afterCurrent = currentNode.getNext();
		if(afterCurrent != null) {
			afterCurrent = afterCurrent.getNext();
			currentNode.setNext(afterCurrent);
		}
	}
	 
	/**
	 * Return the number of nodes in this list.
	 */
	public int size() {
		int num = 0;
		LinkedListNode<T> cursor;
		for(cursor = head; cursor != null; cursor = cursor.getNext()) {
			num++;
		}
		return num;
	}
	 
	/**
	 * Check if list is empty.
	 * @return true if list contains no items.
	 */
	public boolean isEmpty() {
		if(head == null) {
			return true;
		}
		return false;
	}
	 
	/**
	 * Return a String representation of the list.
	 */
	public String toString() {
		LinkedListNode<T> current = head;
		String str = "";
		while(current != null) {
			//If the current node is not the last node, include the symbol "->"
			if(current.getNext() != null) {
				str += (current.getData()).toString() + "->";
				current = current.getNext();
			}
			//If the current node is the last node, do not include the symbol "->"
			else {
				str += (current.getData()).toString();
				current = current.getNext();
			}
			
		}
		return str;
	}

}
