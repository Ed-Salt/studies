/*
	Java Clock Program - Lab 2
	Edward Salt - dns18cch

*/
import java.awt.*;
import javax.swing.*;

public class ClockComponent extends JComponent 
{ 
	
	public void paintComponent(Graphics g)
    {  
		//construct the clock with hands at the time
		//in the Clock() parameters
		Graphics2D g2 = (Graphics2D) g;
		Clock clck = new Clock(10, 10);
		clck.draw(g2);
    }
}
