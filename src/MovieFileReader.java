import datastructures.DefaultBinaryTree;
import datastructures.DefaultBinaryTreeNode;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;  
import org.w3c.dom.*;
import java.io.*;

/**
 * This class is responsible for parsing the xml file and make a binary tree representation
 * @author Xijie Guo
 *
 */
public class MovieFileReader {
	
	//Represents binary tree of questions
	private DefaultBinaryTree<String> questionTree;
	
	/**
	 * Constructor of MovieFileReader
	 */
	public MovieFileReader() {
		//Setup XML Document
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File xmlFile = new File("MovieNames.xml");
		try {
			//Creates a Document object
			Document document = builder.parse(xmlFile);
			//Get the question tree by parsing the xml file
			questionTree = parseMovieFile(document);

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Navigate through the major document structure, parse the file
	 * @param document the Document object
	 * @return the question binary tree
	 */
	public DefaultBinaryTree<String> parseMovieFile(Document document) {
		DefaultBinaryTree<String> tree = new DefaultBinaryTree<String>();
		//Get the first element node in the tree and parse the node 
		Node docRoot =  document.getDocumentElement();
		DefaultBinaryTreeNode<String> movieRoot = parseQuestion(docRoot);
		
		//Set the root of the tree
		tree.setRoot(movieRoot);
		return tree;
	}
	
	/**
	 * Recursive method for parsing question
	 * @param n the Node object
	 * @return the Node object 
	 */
	private DefaultBinaryTreeNode<String> parseQuestion(Node n) {
		//Get the question after "text" and store it to a binary tree node
		DefaultBinaryTreeNode<String> movieNode = new DefaultBinaryTreeNode<String>(((Element) n).getAttribute("text"));

		//Get all child nodes 
		NodeList childNodes = n.getChildNodes();
		//Loop through all child nodes
		for (int i = 0; i < childNodes.getLength(); i++) {
			//Make sure the node is an element node
			if (childNodes.item(i) instanceof Element){
				//No matter which child node we get(yes or no), parse answer
				if (childNodes.item(i) != null) {
					if (((Element) childNodes.item(i)).getAttribute("useranswer").equals("yes")) {
						movieNode.setLeftChild(parseAnswer(childNodes.item(i)));
					}

					else if(((Element) childNodes.item(i)).getAttribute("useranswer").equals("no")){	
						movieNode.setRightChild(parseAnswer(childNodes.item(i)));
					}
				}
			}

		}
		return movieNode;
	}
	
	/**
	 * Recursive method for parsing answer
	 * @param n the Node object
	 * @return the Node object
	 */
	private DefaultBinaryTreeNode<String> parseAnswer(Node n) {
		//Get all child nodes
		NodeList childNodes = n.getChildNodes();

		//Loop through all child nodes
		for (int i = 0; i < childNodes.getLength(); i++) {
			
			//Get the ith index node in all child nodes
			Node childNode = childNodes.item(i);
			//Check if the node is an element node or a text node, which is "thing"
			if(childNode.getNodeName().equals("question")) {
				return parseQuestion(childNode);
			}

			//Just return the node with its content in it as a binary tree node
			else if(childNode.getNodeName().equals("thing")) {
				DefaultBinaryTreeNode<String> objectNode = new DefaultBinaryTreeNode<String>(childNode.getTextContent());
				return objectNode;
			}
		}

		return null;
	}
	
	/**
	 * Get the String binary tree
	 * @return the String binary tree
	 */
	public DefaultBinaryTree<String> getTree() {
		return questionTree;
	}
}
