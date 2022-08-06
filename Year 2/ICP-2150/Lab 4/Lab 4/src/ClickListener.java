import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JFrame;

public class ClickListener implements ActionListener {
	
	private JFrame window;
	
	public ClickListener(JFrame frame) {
		window = frame;
	}

	public void actionPerformed(ActionEvent event) {
		System.out.println("Clicked!");
		DiceComponent.throwDice();
		window.repaint();
	}
}
