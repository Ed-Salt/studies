import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;

public class TimerThreadOLD implements Runnable {

	private Ellipse2D.Double threadedBall;
	final int DELAY;
	private JFrame frame;
	private JPanel panel;
	
	public TimerThreadOLD(Ellipse2D.Double ball, int time, JFrame threadContainer) {
		threadedBall = ball;
		DELAY = time;
		frame = threadContainer;
		//panel = threadContainer;
	}
	
	public void run() {
		//construct and start the thread's listener
		TimerListenerOLD listener = new TimerListenerOLD(threadedBall, frame);
		//TimerListener listener = new TimerListener(threadedBall, panel);
		Timer t = new Timer(DELAY, listener);
		t.start();
	}

}
