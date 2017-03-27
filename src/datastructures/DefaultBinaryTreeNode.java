package datastructures;
/**
 * Node class containing methods for getting and setting the data of the node, getting and setting the left
 * and right child of the node, and check if the node is a leaf. Implement BinaryTreeNode<T> interface
 * @author Xijie Guo
 * 
 */
public class DefaultBinaryTreeNode<T> implements BinaryTreeNode<T> {
	private T data;

	private BinaryTreeNode<T> left;

	private BinaryTreeNode<T> right;
	
	/**
	 * Constructor for DefaultBinaryTreeNode
	 */
	public DefaultBinaryTreeNode(T data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	/**
	 * Get the data stored at this node.
	 * @return Object data.
	 */
	public T getData() {
		return data;
	}

	/**
	 * Set the data stored at this node.
	 * @param data Object data
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Get the left child.
	 * @return BinaryTreeNode that is left child,
	 * or null if no child.
	 */
	public BinaryTreeNode<T> getLeftChild() {
		return left;
	}

	/**
	 * Get the right child.
	 * @return BinaryTreeNode that is right child,
	 * or null if no child.
	 */
	public BinaryTreeNode<T> getRightChild() {
		return right;
	}

	/**
	 * Set the left child.
	 */
	public void setLeftChild(BinaryTreeNode<T> left) {
		this.left = left;
		
	}

	/**
	 * Set the right child.
	 */
	public void setRightChild(BinaryTreeNode<T> right) {
		this.right = right;
		
	}

	/**
	 * Tests if this node is a leaf (has no children).
	 * @return true if leaf node.
	 */
	public boolean isLeaf() {
		return (left == null) && (right == null);
	}

}
