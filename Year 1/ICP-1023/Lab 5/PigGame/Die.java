import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Die {
	
	private int num;
	private int faces;
	
	//Initialises the die with the number of faces passed
	public Die(int faceCount)
	{
		faces = faceCount;
	}
	
	//Randomly rolls the die to a number between (and including) 1 and the value of 'faces'
	public void roll()
	{
		Random rand = ThreadLocalRandom.current();
		num = rand.nextInt(faces)+1;
	}
	
	//Returns the number rolled
	public int getRoll()
	{
		return num;
	}	
	
}
