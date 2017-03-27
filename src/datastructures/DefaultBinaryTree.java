package datastructures;
/**
 * Binary tree class containing methods for getting and setting root of the tree and implementing inorder,
 * preorder, postorder traversals. Super class for DefaultBinarySearchTree. This class implements
 * BinaryTree<T> interface
 * @author Xijie Guo
 *
 */
public class DefaultBinaryTree<T> implements BinaryTree<T> {
	private BinaryTreeNode<T> root; 
	
	/**
	 * Constructor for DefaultBinaryTree
	 */
	public DefaultBinaryTree() {
		this.root = null;
	}

	/**
	 * Get the root node for this tree.
	 * 
	 * @return root or null if tree is empty.
	 */
	public BinaryTreeNode<T> getRoot() {
		if(!isEmpty()) {
			return root;
		}
		return null;
	}

	/**
	 * Set the root node for this tree.
	 * @param root root node of the tree
	 */
	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}

	/**
	 * Test if the tree is empty.
	 * 
	 * @return true if tree has no data.
	 */
	public boolean isEmpty() {
		if(root == null) {
			return true;
		}
		return false;
	}

	/**
	 * Get the data of this tree using inorder traversal.
	 * 
	 * @return inorder List.
	 */
	public LinkedList<T> inorderTraversal() {
		LinkedList<T> inorderList = new LinkedList<T>();

		if(isEmpty()) {
			return inorderList;
		}
		inorderTraversal(root, inorderList);
		return inorderList;
	}
	
	/**
	 * Recursive method for the logic of inorder traversal
	 * @param node the current node of the tree
	 * @param traversal the inorder linked list
	 */
	private void inorderTraversal(BinaryTreeNode<T> node, LinkedList<T> traversal) {
        //If the current node has a left child, update current node to be the left 
		//child node and implement inorder traversal recursively to find the leftmost node
		if(node.getLeftChild() != null) {
			inorderTraversal(node.getLeftChild(), traversal);
		}

		traversal.insertLast(node.getData()); 
		
		//If the current node has a right child, update current node to be the right 
		//child node and implement inorder traversal recursively to find the rightmost node
		if(node.getRightChild() != null) {
			inorderTraversal(node.getRightChild(), traversal);
		}
	}

	/**
	 * Get the data of this tree using preorder traversal.
	 * 
	 * @return preorder List.
	 */
	public LinkedList<T> preorderTraversal() {
		LinkedList<T> preorderList = new LinkedList<T>();
		if(isEmpty()) {
			return preorderList;
		}
		preorderTraversal(root, preorderList);
		return preorderList;
	}
	
	
	/**
	 * Recursive method for the logic of preorder traversal
	 * @param node the current node
	 * @param traversal the preorder linked list
	 */
	private void preorderTraversal(BinaryTreeNode<T> node, LinkedList<T> traversal) {
		//Base case
		if(node == null) {
			return;
		}
		
		//recursive case. If the current node is not null, insert it, then go to its left child node.
		//Go to right child node at last
		else {
			traversal.insertLast(node.getData());
			preorderTraversal(node.getLeftChild(), traversal);
			preorderTraversal(node.getRightChild(), traversal);
		}
		
	}

	/**
	 * Get the data of this tree using postorder traversal.
	 * 
	 * @return postorder List.
	 */
	public LinkedList<T> postorderTraversal() {
		LinkedList<T> postorderList = new LinkedList<T>();
		if(isEmpty()) {
			return postorderList;
		}
		postorderTraversal(root, postorderList);
		return postorderList;
	}
	
	/**
	 * Recursive method for the logic of postorder traversal
	 * @param node the current node
	 * @param traversal the postordered linked list
	 */
	private void postorderTraversal(BinaryTreeNode<T> node, LinkedList<T> traversal) {
		if(node.getLeftChild() != null) {
			postorderTraversal(node.getLeftChild(), traversal);
		}
		if(node.getRightChild() != null) {
			postorderTraversal(node.getRightChild(), traversal);
		}
		traversal.insertLast(node.getData());
	}
	
	public static void main(String[] args) {
		DefaultBinaryTree<String> tree = new DefaultBinaryTree<String>();
		DefaultBinaryTreeNode<String> node1 = new DefaultBinaryTreeNode<String>("Happy");
		DefaultBinaryTreeNode<String> node2 = new DefaultBinaryTreeNode<String>("Doc");
		DefaultBinaryTreeNode<String> node3 = new DefaultBinaryTreeNode<String>("Sleepy");
		DefaultBinaryTreeNode<String> node4 = new DefaultBinaryTreeNode<String>("Bashful");
		DefaultBinaryTreeNode<String> node5 = new DefaultBinaryTreeNode<String>("Grumpy");
		DefaultBinaryTreeNode<String> node6 = new DefaultBinaryTreeNode<String>("Sneezy");

		tree.setRoot(node1);
		node1.setLeftChild(node2);
		node1.setRightChild(node3);
		node2.setLeftChild(node4);
		node2.setRightChild(node5);
		node3.setRightChild(node6);
		
		tree.inorderTraversal();
		System.out.println(tree.inorderTraversal().toString());
		
		tree.preorderTraversal();
		System.out.println(tree.preorderTraversal().toString());

		tree.postorderTraversal();
		System.out.println(tree.postorderTraversal().toString());
	}

}
