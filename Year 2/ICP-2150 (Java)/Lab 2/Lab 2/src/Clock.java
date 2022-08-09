/*
	Java Clock Program - Lab 2
	Edward Salt - dns18cch

*/
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Clock {
	private double xLeft = 10.0;
	private double yTop = 10.0;
	private double hr = 0;
	private double min = 0;
	private double rad = 190.0;
    private double xCent = xLeft + rad;
    private double yCent = yTop + rad;
    private double hrLen = rad * 0.7;
    private double minLen = rad * 0.94;
    private double sLen = rad * 0.84;

	public Clock(double h, double m)
	{
		//convert time into degrees and then into radians
		hr = Math.toRadians(h * 30 +m*0.5);
		min = Math.toRadians(m * 6);
	}

	public void draw(Graphics2D g2)
	{
		//construct shapes
	    Ellipse2D.Double face = new Ellipse2D.Double(xLeft, yTop, rad * 2, rad * 2);
	    Ellipse2D.Double center = new Ellipse2D.Double(xLeft + 180, yTop + 180, 20, 20);
	    
	    //hour & minute hand points
	    Point2D r1 = new Point2D.Double(xCent, yCent);
		Point2D r2 = new Point2D.Double(xCent + (Math.sin(hr) * hrLen), yCent - (Math.cos(hr) * hrLen));
		Point2D r3 = new Point2D.Double(xCent + (Math.sin(min) * minLen), yCent - (Math.cos(min) * minLen));
		
		//hour & minute hands
		Line2D hour = new Line2D.Double(r1, r2);
		Line2D minute = new Line2D.Double(r1, r3);
		
		//strokes for the shapes
		BasicStroke main = new BasicStroke(2.0f);
		BasicStroke h = new BasicStroke(7.0f);
		BasicStroke m = new BasicStroke(3.0f);
		
	    //draw face and hands
		g2.setStroke(main);
	    g2.draw(face);
	    g2.fill(center);
	    g2.setStroke(h);
	    g2.draw(hour);
	    g2.setStroke(m);
	    g2.draw(minute);
	    
	    //construct & draw hour markings
		BasicStroke hs = new BasicStroke(1.0f);
	    g2.setStroke(hs);
	    for (int i = 0; i < 12; i++) {
	    	double s = Math.toRadians(i*30);
			Point2D s1 = new Point2D.Double(xCent + (Math.sin(s) * sLen), yCent - (Math.cos(s) * sLen));
			Point2D s2 = new Point2D.Double(xCent + (Math.sin(s) * rad), yCent - (Math.cos(s) * rad));
	    	Line2D hrs = new Line2D.Double(s1, s2);
	    	g2.draw(hrs);
	    }
	}
}
