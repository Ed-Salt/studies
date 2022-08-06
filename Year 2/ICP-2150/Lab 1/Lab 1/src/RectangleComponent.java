import java.awt.*;
import javax.swing.*;
public class RectangleComponent extends JComponent 
{ 
	// Override paintComponent method of parent class 
	public void paintComponent(Graphics g)
  {  
      // Recover Graphics2D reference by down casting 
      Graphics2D g2 = (Graphics2D) g;
      Rectangle rect = new Rectangle(5, 10, 20, 30);
      g2.draw(rect);
      rect.translate(15, 25);
      g2.draw(rect);
   }
}
