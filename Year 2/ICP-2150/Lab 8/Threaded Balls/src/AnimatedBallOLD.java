import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AnimatedBallOLD extends JComponent {
		
	public static int width = 360;
	public static int height = 120;
	private static Random generator = new Random();
	public static int size = 10;
	
	final static int DELAY = 30;
	static List<Ellipse2D.Double> ballList = new ArrayList<Ellipse2D.Double> ();
	/*
	public Ellipse2D.Double ball = 
			new Ellipse2D.Double(
					generator.nextInt(width-size), 
					generator.nextInt(height-size),
					size, size);
	//*/
	private static JFrame frame = new JFrame();//BallViewer.frame;
	
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(Color.RED);
        g2.setStroke(new BasicStroke(5.0f));
        ballList.forEach((b) -> g2.fill(b));
        //g2.fill(ball);
    }
	/*
	public static void main(String[] args) {   
		//add ball(s) to frame
        int ballCount = 10;
        for (int i = 0; i < ballCount; i++) {
        	addBall();
        }
        JPanel pane = new JPanel();
        JButton btn = new JButton("Start");
        pane.add(btn);
        frame.add(BorderLayout.SOUTH, pane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.pack();
		frame.setVisible(true);
		
	}
	*/
	public static void addBall() {
		//construct new ball
		Ellipse2D.Double ball = 
				new Ellipse2D.Double(
						generator.nextInt(width-size), 
						generator.nextInt(height-size),
						size, size);
		ballList.add(ball);
		AnimatedBallOLD newBall = new AnimatedBallOLD();
		System.out.println("Ball #" + ballList.size());
		//construct and start the ball's thread
		Runnable r =
				new TimerThread(ballList.get(ballList.size()-1), DELAY, frame, ballList.size());
		Thread t = new Thread(r);
		t.start();
		//add ball to frame
        frame.getContentPane().add(newBall);
	}
}
