import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame{
	private Refrigerator refrigerator;
	
	private Point defaultLocation;
	private final int frameWidth = 400;
	private final int frameHeight = 400;
	
	public GUI(){
		add(new Panel());
	
		
		
		centerGUI();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(this.defaultLocation);
		setTitle("Lantronix Test");
//		setResizable(false);
		setPreferredSize(new Dimension(frameWidth, frameHeight));
		pack();
		setVisible(true);
	}
	
	private void centerGUI() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();

		width /= 2;
		height /= 2;

		defaultLocation = new Point(width - (frameWidth / 2), height
				- (frameHeight / 2));
	}
	
	
	
	
	class Panel extends JPanel{
		public Panel(){
			
		}
		
		

		
	}
	
	
	
	
	
	public static void main(String[] args) {
		new GUI();
	}
}
