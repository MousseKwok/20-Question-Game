import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
/**
 * This class loads an image from the folder and paint it on the frame
 * This class extends JPanel and it is a new class
 * @author Xijie Guo
 *
 */
public class ImagePanel extends JPanel {

	//The BufferedImage object
	private BufferedImage backgroundImage = null;
	
	/**
	 * Constructor for this class
	 */
	public ImagePanel() {
		try{
			//Try to read the image as a file
			backgroundImage = ImageIO.read(new File("movieCollage.jpg"));
		} catch (IOException e) {
        	e.printStackTrace();
        }
		
	}
	
	/**
	 * Paint the image
	 * @param g the Graphics object
	 */
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
}
