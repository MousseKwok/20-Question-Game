import javax.swing.JFrame;

/**
 * Main application for starting the unrestricted guessing game application
 * @author Xijie Guo
 *
 */
public class UnrestrictedGuessingGameApplication {
	
	public static void main(String[] args) {

		JFrame frame = new JFrame("UnrestrictedGuessing Game");
		
		frame.setSize(1200, 700);

		frame.add(new UnrestrictedGuessingGameController());
	    
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
}
