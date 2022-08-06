import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class EllipseComponent extends JComponent 
{ 
	// Override paintComponent method of parent class 
	
	public void paintComponent(Graphics g)
    {  
      // Recover Graphics2D reference by down casting 
      Graphics2D g2 = (Graphics2D) g;
      Shape circle = new Ellipse2D.Double(100, 100, 200, 200);
      g2.setColor(Color.RED);
      g2.fill(circle);
      /*circle.translate(15, 25);
      g2.draw(circle);*/
    }
}
