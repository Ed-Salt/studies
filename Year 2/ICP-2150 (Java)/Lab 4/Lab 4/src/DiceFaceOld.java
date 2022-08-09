import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D.Double;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiceFaceOld {

	private double yDice = 80;
	private double[] xDice = {100, 444};
	private double sizeDice = 160;
	private double numInterval = 5;
	private double numTopLeft = numInterval * 5;
	private double numMidCent = numInterval * 13;
	private double numBotRght = numInterval * 21;
	private double rad = 15;
	private int[] dice = {0, 0};
	
	public DiceFaceOld(int f, int s) {
		dice[0] = f;
		dice[1] = s;
	}
	
	public void draw(Graphics2D g2)
	{
		//construct
		//dice face
	    Rectangle.Double dice1 = new Rectangle.Double(xDice[0], yDice, sizeDice, sizeDice);
	    Rectangle.Double dice2 = new Rectangle.Double(xDice[1], yDice, sizeDice, sizeDice);
	    
	    //numbers
	    Ellipse2D.Double[][] dnum = new Ellipse2D.Double[2][];
	    for (int i = 0; i < 2; i++) {
	    	dnum[i] = new Ellipse2D.Double[7];
	    	dnum[i][0] = new Ellipse2D.Double(xDice[i] + numTopLeft, yDice + numTopLeft, rad * 2, rad * 2);
	    	dnum[i][1] = new Ellipse2D.Double(xDice[i] + numBotRght, yDice + numTopLeft, rad * 2, rad * 2);
	    	dnum[i][2] = new Ellipse2D.Double(xDice[i] + numTopLeft, yDice + numMidCent, rad * 2, rad * 2);
	    	dnum[i][3] = new Ellipse2D.Double(xDice[i] + numMidCent, yDice + numMidCent, rad * 2, rad * 2);
	    	dnum[i][4] = new Ellipse2D.Double(xDice[i] + numBotRght, yDice + numMidCent, rad * 2, rad * 2);
	    	dnum[i][5] = new Ellipse2D.Double(xDice[i] + numTopLeft, yDice + numBotRght, rad * 2, rad * 2);
	    	dnum[i][6] = new Ellipse2D.Double(xDice[i] + numBotRght, yDice + numBotRght, rad * 2, rad * 2);
	    }
	    /*
	    Ellipse2D.Double d1num1 = new Ellipse2D.Double(numLeft1, numTop, rad * 2, rad * 2);
	    Ellipse2D.Double d1num2 = new Ellipse2D.Double(numCent1, numMid, rad * 2, rad * 2);
	    Ellipse2D.Double d1num3 = new Ellipse2D.Double(numRght1, numBot, rad * 2, rad * 2);
		*/
		
		BasicStroke main = new BasicStroke(2.0f);
		BasicStroke h = new BasicStroke(7.0f);
		BasicStroke m = new BasicStroke(3.0f);
		
		Color BLACK = new Color(0);
		Color RED = new Color(255, 0, 0);
		Color WHITE = new Color(255, 255, 255);
		
	    //draw
		g2.setStroke(main);
		g2.setColor(BLACK);
	    g2.fill(dice1);
	    g2.setColor(RED);
	    g2.fill(dice2);	
	    g2.setColor(WHITE);
	    for (int i = 0; i < 2; i++) {
	    	List<Integer> nums = new ArrayList<Integer>();
	    	if (dice[i] == 1) {
	    		nums.add(3);
	    	}
	    	else if (dice[i] == 2) {
	    		nums.addAll(Arrays.asList(0, 6));
	    	}
	    	else if (dice[i] == 3) {
	    		nums.addAll(Arrays.asList(0, 3, 6));
	    	}
	    	else if (dice[i] == 4) {
	    		nums.addAll(Arrays.asList(0, 1, 5, 6));
	    	}
	    	else if (dice[i] == 5) {
	    		nums.addAll(Arrays.asList(0, 1, 3, 5, 6));
	    	}
	    	else if (dice[i] == 6) {
	    		nums.addAll(Arrays.asList(0, 1, 2, 4, 5, 6));
	    	}
	    	for (int j = 0; j < nums.size(); j++) {
	    		g2.fill(dnum[i][nums.get(j)]);
	    	}
	    }
	    /*
	    for (int j = 0; j < 2; j++) {
	    	for (int i = 0; i < 7; i++) {
	    		g2.fill(dnum[j][i]);
	    	}
	    }
	    g2.fill(d1num1);
	    g2.fill(d1num2);
	    g2.fill(d1num3);
	    */
	    
	}

}
