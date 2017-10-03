import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import datastructures.DefaultBinaryTree;
import datastructures.DefaultBinaryTreeNode;


@SuppressWarnings("serial")
public class UnrestrictedGuessingGameController extends JPanel implements ActionListener{

	//Store all movie names
	public static final String[] MOVIE_NAMES = {"Frozen", "Finding Nemo", "Spirited Away", "The Triplets of Belleville",
			                     "Roman Holiday", "Love Actually", "Iron Man", "Harry Potter", "Rebecca", "Psycho",
			                     "Casablanca", "Sunset Boulevard", "Crouching Tiger, Hidden Dragon", "Brokeback Mountain",
			                     "Titanic", "Fight Club"};
	
	//16 movie name labels
	private JLabel[] nameLabel = new JLabel[16];
	
	//Start button, yes button and no button
	private JButton startButton, yesButton, noButton;
	
	//Question label
	private JLabel questionLabel;
	
	//Instance of MovieFileReader
	private MovieFileReader movie = new MovieFileReader();
	
	//Set it to false to make sure yes and no buttons won't work before we click on start button at first
	private boolean ifGameStart = false;
	
	//Check if the user has win
	private boolean ifWin = false;
	
	//Get a movie binary tree
	private DefaultBinaryTree<String> movieTree = movie.getTree();
	
	//Get the root of the movie tree
	private DefaultBinaryTreeNode<String> movieTreeNode = (DefaultBinaryTreeNode<String>) movieTree.getRoot();
	
	/**
	 * Constructor of UnrestrictedGussingGameController
	 */
	public UnrestrictedGuessingGameController() {
		super(new BorderLayout());
		createView();
	}
	
	/**
	 * Create the whole view
	 */
	private void createView() {
		createTitlePanel();
		createButtons();
		createQuestion();

		add(new ImagePanel(), BorderLayout.CENTER);
	}
	 
	/**
	 * Create a panel of name labels
	 */
	private void createTitlePanel() {
		JPanel panel = new JPanel();
		for(int i = 0; i < nameLabel.length; i++) {
			nameLabel[i] = new JLabel(MOVIE_NAMES[i]);
			nameLabel[i].setBackground(new Color(229, 255, 204));
			nameLabel[i].setOpaque(true);
			panel.add(nameLabel[i]);
		}
		panel.setLayout(new GridLayout(4, 4));
		add(panel, BorderLayout.NORTH);
	}
	
	/**
	 * Create question parts 
	 */
	private void createQuestion() {
		JPanel bottomPanel = new JPanel();
		startButton = new JButton("START");

		startButton.setFont(new Font("Arial Black", Font.BOLD, 60));
		startButton.setBackground(Color.pink);
		startButton.setOpaque(true);
		startButton.setBorderPainted(false);
		startButton.addActionListener(this);
		questionLabel = new JLabel("");
		questionLabel.setFont(new Font("Zapfino", Font.PLAIN, 25));
		questionLabel.setForeground(new Color(148, 0, 211));

        bottomPanel.add(questionLabel);
		bottomPanel.add(startButton);
        bottomPanel.setLayout(new GridLayout(1, 2));
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	
	/**
	 * Create yes and no buttons
	 */
	private void createButtons() {
		JPanel buttonPanel = new JPanel();
		yesButton = new JButton("YES");

		yesButton.setFont(new Font("Arial", Font.BOLD, 50));
		yesButton.setBackground(new Color(255, 255, 204));
		yesButton.setOpaque(true);
		yesButton.setBorderPainted(false);
		yesButton.addActionListener(this);

		noButton = new JButton("NO");
		noButton.setFont(new Font("Arial", Font.BOLD, 50));
		noButton.setBackground(Color.lightGray);
		noButton.setOpaque(true);
		noButton.setBorderPainted(false);
		noButton.addActionListener(this);

		buttonPanel.add(yesButton);
		buttonPanel.add(noButton);
		buttonPanel.setLayout(new GridLayout(2,1));
		add(buttonPanel, BorderLayout.EAST);
	}
	
	/**
	 * Check if the node is a leaf node
	 * @return a leaf node 
	 */
	private boolean lastRound() {
		return movieTreeNode.isLeaf();
	}
	
	/**
	 * Add a new question, answer, and an object
	 */
	private void addNewThing() {
		String temp = movieTreeNode.getData();

		//Instructions that are displayed in the pop-up window 
		String instruction1 = "Pick a thing that is not on the list and write it down below";
		String instruction2 = "Add a new question below";
		String instruction3 = "Write yes or no(in lowercase) as the answer to this quesion, and "
				+ "click on the start button to restart the game. The question and object has been added";

		//Represents the new answer and new question 
		String newAnswer = "";
	        String newQuestion = "";
		
		String newObject = JOptionPane.showInputDialog(instruction1);
		
		//If the user does not click on the cancel button, pop up the second window
		if(newObject != null) {
			
			newQuestion = JOptionPane.showInputDialog(instruction2);
			
			//If the user does not click on the cancel button, pop up the third window
			if(newQuestion != null) {
				
				newAnswer = JOptionPane.showInputDialog(instruction3);
				
				if(newAnswer != null) {
					
					movieTreeNode.setData(newQuestion);
					
					//Check what the answer of the question is 
					if(newAnswer.equals("yes")) {
						//For the answer as "yes", set left child as the node with the data of the new object
						DefaultBinaryTreeNode<String> newNode1 = new DefaultBinaryTreeNode<String>(newObject);
						movieTreeNode.setLeftChild(newNode1);
						movieTreeNode.setRightChild(new DefaultBinaryTreeNode<String>(temp));
					}
					
					//For the answer as "no", set right child as the node with the data of the new object
					else if(newAnswer.equals("no")) {
						DefaultBinaryTreeNode<String> newNode2 = new DefaultBinaryTreeNode<String>(newObject);
						movieTreeNode.setRightChild(newNode2);
						movieTreeNode.setLeftChild(new DefaultBinaryTreeNode<String>(temp));
					}
				}
				
			}
		}
		
	}
	
	/**
	 * Implement activities related to JButtons
	 */
	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();

		if(src == startButton) {
			//Set it to true to mark the start of the game
			ifGameStart = true;
			DefaultBinaryTreeNode<String> movieTreeRoot = (DefaultBinaryTreeNode<String>) movieTree.getRoot();
		
			String start = movieTreeRoot.getData();
			questionLabel.setText(start);
			
			//Every time click on the start button, we want to restart the game,
			//So let the movie node start from tree root again
			movieTreeNode = movieTreeRoot;
			
			ifWin = false;
		}

		else if(src == yesButton) {
			
			//Make sure the game has started and the node is not a leaf node
			if(!lastRound() && ifGameStart) {
				
				movieTreeNode = (DefaultBinaryTreeNode<String>) movieTreeNode.getLeftChild();
				String yes = movieTreeNode.getData();
				questionLabel.setText(yes);
			}
			
			//Make sure the game has started and the node is the last one, that means the user wins
			//Pop a message window
			else if(lastRound() && ifGameStart){
				JOptionPane.showMessageDialog(null, "Congratulations, you got the right answer!");
				
				//Set it to true if the user has win
				ifWin = true;
			} 
			
		}

		else if(src == noButton){
			
			////Make sure the game has started and the node is not the last one
			if(!lastRound() && ifGameStart) {
				
				movieTreeNode = (DefaultBinaryTreeNode<String>) movieTreeNode.getRightChild();
				String no = movieTreeNode.getData();
				questionLabel.setText(no);

			}
			//Add a new question and corresponding object, call addNewThing method
			else if(lastRound() && ifGameStart && ! ifWin){
				addNewThing();
			}
		}
	}
}
