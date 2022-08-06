import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Dice
{
     private Random generator;
     private int sides;
     private int value;

     public Dice(int noSides, Graphics2D g2, int xPos, Color colour)
     {
         generator = new Random();
		 sides = noSides;
         value = sides;
         DiceFace die = new DiceFace(160, xPos, 80, value, colour);
         die.draw(g2);
         
     }

     public int throwDice()
     {        
         value = 1 + generator.nextInt(sides);
         return value;
     }
     
     public int getValue()
     {
          return value;
     }
     
	  // Use for program testing
	  public void setValue(int v)
	  {
		  value = v;
	  }
}
