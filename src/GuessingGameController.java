import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import datastructures.DefaultBinaryTree;
import datastructures.DefaultBinaryTreeNode;

@SuppressWarnings("serial")
/**
 * This class is responsible for creating GUI for the game and manages activities related to buttons.
 * This class implements ActionListener
 * @author Xijie Guo
 *
 */
public class GuessingGameController extends JPanel implements ActionListener{

	//Store movie names 
	public static final String[] MOVIE_NAMES = {"Frozen", "Finding Nemo", "Spirited Away", "The Triplets of Belleville",
			                     "Roman Holiday", "Love Actually", "Iron Man", "Harry Potter", "Rebecca", "Psycho",
			                     "Casablanca", "Sunset Boulevard", "Crouching Tiger, Hidden Dragon", "Brokeback Mountain",
			                     "Titanic", "Fight Club"};
	
	//16 movie name labels
	private JLabel[] nameLabel = new JLabel[16];
	
	//Start button, yes button and no button
	private JButton startButton, yesButton, noButton;
	
	//label for displaying question
	private JLabel questionLabel;
	
	//instance of MovieFileReader
	private MovieFileReader movie = new MovieFileReader();
	
	//boolean variable for Checking if we start the game(if we click on the start button)
	private boolean ifGameStart = false;
	
	//Check if the user has win
	private boolean ifWin = false;
	
	//Get the binary movie tree 
	private DefaultBinaryTree<String> movieTree = movie.getTree();
	
	//Tree node tracking child nodes, start from root node
	private DefaultBinaryTreeNode<String> movieTreeNode = (DefaultBinaryTreeNode<String>) movieTree.getRoot();
	
	/**
	 * Constructor of GuessingGameController
	 */
	public GuessingGameController() {
		super(new BorderLayout());
		createView();
	}
	
	/**
	 * Create view by creating movie name panels, question parts, and yes and no buttons
	 */
	private void createView() {
		createTitlePanel();
		createButtons();
		createQuestionPart();
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
	 * Create question parts, which is the bottom area of the frame
	 */
	private void createQuestionPart() {
		JPanel bottomPanel = new JPanel();
		
		startButton = new JButton("START");
		startButton.setFont(new Font("Arial Black", Font.BOLD, 60));
		startButton.setBackground(Color.pink);
		startButton.setOpaque(true);
		startButton.setBorderPainted(false);
		startButton.addActionListener(this);
		questionLabel = new JLabel("");
		questionLabel.setFont(new Font("Zapfino", Font.BOLD, 25));
		questionLabel.setForeground(new Color(148, 0, 211));
		
        bottomPanel.add(questionLabel);
		bottomPanel.add(startButton);
        bottomPanel.setLayout(new GridLayout(1, 2));
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Create two buttons representing yes and no
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
	 * @return true if it is 
	 */
	private boolean lastRound() {
		return movieTreeNode.isLeaf();
	}
	
	/**
	 * Implement activities related to JButtons
	 */
	public void actionPerformed(ActionEvent e) {
		//Decide which button we are interacting with
		JButton src = (JButton) e.getSource();
		if(src == startButton) {
			
			ifGameStart = true;

			DefaultBinaryTreeNode<String> movieTreeRoot = (DefaultBinaryTreeNode<String>) movieTree.getRoot();
			
			//Get the data in the root node and set the text of the question label
			String start = movieTreeRoot.getData();
			questionLabel.setText(start);
			
			//Every time click on the start button, we want to restart the game,
			//So let the movie node start from tree root again
			movieTreeNode = movieTreeRoot;
			
			//Set it to false when the user restarts the game
			ifWin = false;
	}

		else if(src == yesButton) {
			//Check if the game has started and the node is not a leaf node
			if(!lastRound() && ifGameStart) {
				
				movieTreeNode = (DefaultBinaryTreeNode<String>) movieTreeNode.getLeftChild();
				
				String yes = movieTreeNode.getData();
				questionLabel.setText(yes);
			}
			
			//Make sure the game has started and the node is the last one, that means the user wins
			//Pop a message window 
			else if(lastRound() && ifGameStart){
				
				JOptionPane.showMessageDialog(null, "Congratulations, you got the right answer!");
				ifWin = true;
			} 	
			
		}

		else if(src == noButton){
			//Make sure the game has started and the node is not the last one
			if(!lastRound() && ifGameStart) {
				
				movieTreeNode = (DefaultBinaryTreeNode<String>) movieTreeNode.getRightChild();
				
				String no = movieTreeNode.getData();
				questionLabel.setText(no);
			}
			
			//If the answer does not match the answer the user think of
			//Open a pop up window stating the loss of the user
			else if(lastRound() && ifGameStart && !ifWin) {
				JOptionPane.showMessageDialog(null, "Sorry, you lost!");
			}
			
		}
	}
}

