import javax.swing.JFrame;

public class RectangleViewer
{
   public static void main(String[] args)   
   {
	final int FRAME_WIDTH = 300;
	final int FRAME_HEIGHT = 400;    

	JFrame frame = new JFrame();      	
	frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);      
	frame.setTitle("Two rectangles");                 	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      	
	RectangleComponent component = new RectangleComponent();      	
	frame.add(component);      
	frame.setVisible(true);   
   }
}