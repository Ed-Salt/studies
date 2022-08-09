/*Edward Salt - dns18chh
 * Threaded balls program
 * Lab 8
 * 
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.JFrame;

class TimerListener implements ActionListener {
	
	private static Random generator = new Random();
	private int xAdjust = generator.nextInt(2);
	private int yAdjust = generator.nextInt(2);
	private JFrame animateFrame;
	private Ellipse2D.Double animatedBall;
	private double xPos;
	private double yPos;
	private int arrPos;
	
	public TimerListener(Ellipse2D.Double ball, JFrame frame, int pos) {
		xAdjust = correctAdjust(xAdjust);
		yAdjust = correctAdjust(yAdjust);
		animateFrame = frame;
		animatedBall = ball;
		arrPos = pos;
		xPos = animatedBall.x + xAdjust;
		yPos = animatedBall.y + yAdjust;
	}
	
	int correctAdjust(int adjust) {
		//apply direction adjustment
		if (adjust == 0) {
			return -1;
		} 
		return 1;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		//calculate movement
		int width = AnimatedBall.width;
		int height = AnimatedBall.height;
		int size = AnimatedBall.size;
		//if not paused
		if (!AnimatedBall.paused) {
			/*
			//if the ball collides with another
			//change direction
			//--unused as doesn't work correctly--
			xAdjust = ballCollision(xAdjust);
			yAdjust = ballCollision(yAdjust);
			*/
			//if at the positive or negative x edge,
			//change direction
			xAdjust = wallCollision(xPos, width-size, xAdjust);
			//if at the positive or negative y edge,
			//change direction
			yAdjust = wallCollision(yPos, height-size-54, yAdjust);
			//update new coordinates based
			//on directional change
			xPos += xAdjust;
			yPos += yAdjust;
			//update ball's position change
			animatedBall.x = xPos;
			animatedBall.y = yPos;
			animateFrame.repaint();
		}
	}
	
	int wallCollision(double pos, int target, int adjustment) 
	{	
		//calculate wall collision redirection
		if (pos >= target) {
			adjustment = -1;
		} else if (pos <= 0) {
			adjustment = 1;
		}
		return adjustment;
	}
	
	int ballCollision(int adjustment) {
		//calculate ball collision redirection
		//--unused as doesn't work correctly--
		for (int i = AnimatedBall.minCount-AnimatedBall.COUNT; i < AnimatedBall.ballList.size(); i++) {
			if (animatedBall.intersects(AnimatedBall.ballList.get(i).getBounds2D()) &&
					(!(i == arrPos)) && (!(AnimatedBall.deletedBalls.contains(arrPos)))) {
				adjustment *= -1;
				AnimatedBall.deleteBall(arrPos);
			}		
		}
		return adjustment;
	}
}
