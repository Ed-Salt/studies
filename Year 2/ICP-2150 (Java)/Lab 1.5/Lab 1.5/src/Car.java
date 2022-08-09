import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class Car {
	private double xLeft;
	private double yTop;
	
	/**
	Constructs a car with a given top left corner
	@param x the x coordinate of top left corner
	@param y the y coordinate of top left corner
	*/	
	public Car(int x, int y)
	{
		xLeft = x;
		yTop = y;
	}
	
	public void draw(Graphics2D g2)
	{
		//Construct components
		Rectangle2D.Double body = new Rectangle2D.Double(xLeft, yTop + 10, 60, 10);
		Ellipse2D.Double frontTire = new Ellipse2D.Double(xLeft + 10, yTop + 20, 10, 10);
		Ellipse2D.Double rearTire = new Ellipse2D.Double(xLeft + 40, yTop + 20, 10, 10);
		
		Point2D r1 = new Point2D.Double(xLeft + 10, yTop + 10);
		Point2D r2 = new Point2D.Double(xLeft + 20, yTop);
		Point2D r3 = new Point2D.Double(xLeft + 40, yTop);
		Point2D r4 = new Point2D.Double(xLeft + 50, yTop + 10);
		
		Line2D frontWindshield = new Line2D.Double(r1, r2);
		Line2D roofTop = new Line2D.Double(r2, r3);
		Line2D rearWindshield = new Line2D.Double(r3, r4);
		
		//Draw components
		g2.draw(body);
		g2.draw(frontTire);
		g2.draw(rearTire);
		g2.draw(frontWindshield);
		g2.draw(roofTop);
		g2.draw(rearWindshield);
		
	}
}