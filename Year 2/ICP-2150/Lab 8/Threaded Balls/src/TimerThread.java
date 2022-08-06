/*Edward Salt - dns18chh
 * Threaded balls program
 * Lab 8
 * 
*/

import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.Timer;

public class TimerThread implements Runnable {

	private Ellipse2D.Double threadedBall;
	private int delay;
	private JFrame threadFrame;
	private Timer t;
	private int arrPos;
	
	public TimerThread(Ellipse2D.Double ball, int time, JFrame frame, int pos) {
		threadedBall = ball;
		delay = time;
		threadFrame = frame;
		arrPos = pos;
	}
	
	public void run() {
		//construct and start the thread's listener
		TimerListener listener = new TimerListener(threadedBall, threadFrame, arrPos);
		t = new Timer(delay, listener);
		t.start();		
	}
	
	public void stop() {
		//stop the thread
		t.stop();
	}

	public void setDelay(int time) {
		//update timer's delay
		t.setInitialDelay(time);
		t.setDelay(time);
	}
}
