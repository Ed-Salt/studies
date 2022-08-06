import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ColoredBallPanel extends JPanel{
	
	List<ColoredBall> ballList;
	
	public ColoredBallPanel() {
		//this.getContentPane().setPreferredSize(new Dimension(600,600));
		ballList = new ArrayList<ColoredBall> ();
	}

	public void add(ColoredBall ball) 
	{
		ballList.add(ball);		
	}
	/*
	public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(Color.RED);
        g2.setStroke(new BasicStroke(5.0f));
        for (int i = 0; i < ballList.size(); i++) {
        	g2.fill(ballList.get(i));
        }
    }
    */
}
