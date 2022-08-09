/*Edward Salt - dns18chh
 * Threaded balls program
 * Lab 8
 * 
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.ArrayList;
import java.util.List;

public class AnimatedBall extends JComponent {
		
	private static final long serialVersionUID = 1L;
	
	
	public final static int COUNT = 10;		//number of balls
	final static int MAXDELAY = 100;	//max delay (when delay set to 1)

	public static int width = 600;		//frame width
	public static int height = 600;		//frame height
	private static Random generator = new Random();
	public static int size = 20;		//ball size
	private static int sldMin = 1;		//slider min value
	private static int sldMax = 5;		//slider max value
	private static int sldInit = 3;		//slider start value
	private static int sldMultiplier = 2;//slider multiplier to calculate speed
	public static boolean paused = true;//whether the animation is paused or not
	private static boolean stopped = true;//whether the animation has been stopped
	private static boolean picked = false;//if whether ball colours have been picked
	public static int minCount = 0;	//ball base count
	private static int ballCount = 0;	//ball current count
	private static int delay = 			//the delay between draws (controls speed
			MAXDELAY / 					//of the animation)
			(sldInit*sldMultiplier) -
			sldInit*sldMultiplier; 
	
	public static List<Ellipse2D.Double> ballList = new ArrayList<Ellipse2D.Double> ();//list of each ball (as ellipse)
	public static List<Integer> deletedBalls = new ArrayList<Integer> ();		//list of deleted balls
	static List<Color> colourList = new ArrayList<Color> ();					//list of each ball's colour
	static List<AnimatedBall> jcompList = new ArrayList<AnimatedBall> ();	//list of each ball (as component)
	static List<TimerThread> threadList = new ArrayList<TimerThread> ();	//list of each ball's thread timer

	private static JFrame frame = new JFrame();
	
    public void paint(Graphics g) {
    	//paint balls
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5.0f));
        for (int i = minCount-COUNT; i < ballCount; i++) { 
        	//if it's a new set of balls, give them new colours
        	if (!picked) {
        		int[] numColour = {255, 255, 255};
        		//gets random rgb values
            	for (int j = 0; j < 3; j++) {
            		numColour[j] = 8+generator.nextInt(240);
            	}
            	//uses rgb value to make a new colour
        		colourList.add(new Color(numColour[0], numColour[1], numColour[2]));
        	}/*
        	if (deletedBalls.contains(i)) {
        		deleteBall(i);
        	}*/
        	//if not new, paint them with their selected random colour
    		g2.setPaint(colourList.get(i));
        	g2.fill(ballList.get(i));
        } 
        picked = true;
    }
	
	public static void main(String[] args) { 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame = new MyFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		initialiseFrameComponents();        		
	}
	
	static void initialiseFrameComponents() {
		//construct interface panel and buttons
		JPanel uiPanel = new JPanel();
		JButton btnStart = new JButton("Start");
		JButton btnPause = new JButton("Pause");
		JButton btnStop = new JButton("Stop");
		//construct button listeners
		ActionListener lsnStart = (new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//play animation
				paused = false;
				//if there are no balls on screen, add some
				if (stopped) {
					//get current ball count
					ballCount = COUNT + minCount;
					//add balls
					for (int i = minCount; i < ballCount; i++) {
						addBall();
					}
					minCount += COUNT;
					frame.pack();
					stopped = false;
					picked = false;
				}
			}
		});
		ActionListener lsnPause = (new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//pause animation
				paused = true;
			}
		});
		ActionListener lsnStop = (new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (!stopped) {
					//stop animation
					stopped = true;
					paused = true;
					//remove all balls on screen
					for (int i = minCount-COUNT; i < ballList.size(); i++) {
						//deletedBalls.add(i);
						deleteBall(i);		
					}
					//jcompList.forEach((c) -> frame.remove(c));
					//threadList.forEach((t) -> t.stop());;
					frame.repaint();
				}
			}
		});
		//add listeners to buttons
		btnStart.addActionListener(lsnStart);
		btnPause.addActionListener(lsnPause);
		btnStop.addActionListener(lsnStop);
		//add buttons to interface panel
		uiPanel.add(btnStart);
		uiPanel.add(btnPause);
		uiPanel.add(btnStop);
		//construct slider
		JSlider sldSpeed = new JSlider(sldMin, sldMax, sldInit);
		sldSpeed.setMajorTickSpacing(1);
		sldSpeed.setPaintTicks(true);
		sldSpeed.setPaintLabels(true);
		//construct slider listener
		ChangeListener lsnSpeed = (new ChangeListener() 
		{
		      public void stateChanged(ChangeEvent event) 
		      {
		          int value = sldSpeed.getValue();
		          int num = value*sldMultiplier;
		          delay = MAXDELAY / (num) - num + 1;
		          threadList.forEach(((r) -> r.setDelay(delay)));
		          
		      }
		});
		//add listener to slider
		sldSpeed.addChangeListener(lsnSpeed);
		//add listener to interface panel
		uiPanel.add(sldSpeed);
		//setup frame
        frame.add(BorderLayout.SOUTH, uiPanel);
		frame.pack();
	}
	
	public static void deleteBall(int position) {
		frame.remove(jcompList.get(position));
		threadList.get(position).stop();
		deletedBalls.add(position);
		System.out.println(position);

	}
	
	public static void addBall() {
		//construct new ball
		Ellipse2D.Double ball = 
				new Ellipse2D.Double(
						generator.nextInt(width-size), 
						generator.nextInt(height-size-54),
						size, size);;
		boolean ballValid = false;
		while (!ballValid) {
			ballValid = true;
			//makes sure the new ball doesn't overlap with any of the other balls
			for (int i = minCount; i < ballList.size(); i++) {
				if (ball.intersects(ballList.get(i).getBounds2D())) {
					System.out.println("redo " + i);
					ballValid = false;
					ball = 
						new Ellipse2D.Double(
								generator.nextInt(width-size), 
								generator.nextInt(height-size-54),
								size, size);
					i = ballCount;
				}
			}
		}
		ballList.add(ball);
		//construct ball's component
		AnimatedBall newBall = new AnimatedBall();
		jcompList.add(newBall);
		//construct and start the ball's thread
		Runnable r =
				new TimerThread(ballList.get(ballList.size()-1), delay, frame, ballList.size()-1);
		Thread t = new Thread(r);
		threadList.add((TimerThread) r);
		t.start();
		//add ball to frame
        frame.getContentPane().add(newBall);
	}
}
