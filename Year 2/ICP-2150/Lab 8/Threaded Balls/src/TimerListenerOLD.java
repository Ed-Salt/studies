import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JFrame;

class TimerListenerOLD implements ActionListener {
	
	private static Random generator = new Random();
	private int xAdjust = generator.nextInt(2);
	private int yAdjust = generator.nextInt(2);
	private JFrame frame;
	private JPanel panel;
	private Ellipse2D.Double animatedBall;
	private double xPos;
	private double yPos;
	
	public TimerListenerOLD(Ellipse2D.Double ball, JFrame timerContainer) {
		if (xAdjust == 0) {
			xAdjust = -1;
		} else {
			xAdjust = 1;
		}
		if (yAdjust == 0) {
			yAdjust = -1;
		} else {
			yAdjust = 1;
		}
		frame = timerContainer;
		//panel = timerContainer;
		animatedBall = ball;
		xPos = animatedBall.x + xAdjust;
		yPos = animatedBall.y + yAdjust;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		// Insert code to be executed on each timer
		// event (e.g. move a shape 1 pixel to right)
		int width = AnimatedBall.width;
		int height = AnimatedBall.height;
		int size = AnimatedBall.size;

		//if at the positive or negative x edge,
		//change direction
		xAdjust = wallCollision(xPos, width-size, xAdjust);
		//if at the positive or negative y edge,
		//change direction
		yAdjust = wallCollision(yPos, height-size, yAdjust);
		//update new coordinates based
		//on directional change
		xPos += xAdjust;
		yPos += yAdjust;
		//update ball's position change

		animatedBall.x = xPos;
		animatedBall.y = yPos;
		frame.repaint();
		//panel.repaint();
	}
	
	int wallCollision(double pos, int target, int adjustment) 
	{	
		if (pos >= target) {
			adjustment = -1;
		} else if (pos <= 0) {
			adjustment = 1;
		}
		return adjustment;
	}

	
}
