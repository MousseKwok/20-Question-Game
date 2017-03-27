import javax.swing.JFrame;

/**
 * Main application for starting the guessing game application
 * @author Xijie Guo
 *
 */
public class GuessingGameApplication {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Guessing Game");
		
		frame.setSize(1200, 700);

		frame.add(new GuessingGameController());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}
}
