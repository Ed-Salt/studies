import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class ColoredBall extends Ellipse2D.Double {

	private int size = 200;
	
	public ColoredBall(int x, int y) {
		new Ellipse2D.Double(x, y, size, size);
	}

}
