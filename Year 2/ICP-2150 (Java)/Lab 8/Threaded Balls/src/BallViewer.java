import java.awt.EventQueue;
import javax.swing.JFrame;

public class BallViewer {

	public static MyFrame frame;
	
	public static void main(String[] args)	{
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame = new MyFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
}
