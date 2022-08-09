import javax.swing.*;

public class EmptyFrameViewer {

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		
		final int FRAME_WIDTH = 800;
		final int FRAME_HEIGHT = 600;
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Empty");
		frame.setVisible(true);
	
		System.out.println(frame.getContentPane().getSize());

	}
}
