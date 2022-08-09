import java.util.Scanner;

public class Player {

	private String name;
	private int score = 0;
	private int num;
	private boolean hold = false;
	private Scanner in = new Scanner(System.in);

	
	//Get's player number/count and asks user to input their player name
	public Player(int playerNumber)
	{
		num = playerNumber;
		System.out.print("\nPlayer #" + num + ", what is your name? : ");
		name = in.next();
	}
	
	//Adds the value of points passed to the score
	public void score(int points)
	{
		score += points;
	}
	
	//Updates the hold boolean to the passed value
	public void hold(boolean upHold)
	{
		hold = upHold;
	}
	
	//Validates the input and condenses it to a single character string
	//If hold is chosen, it also updates the player's hold status
	//If an invalid input is given, then it recursively repeats
	private String rollOrHold(String input)
	{
		if (input.equals("R") || input.equals("ROLL")) {
			return input.substring(0, 1);
		}
		else if (input.equals("H") || input.equals("HOLD")) {
			hold = true;
			System.out.println(name + " decided to hold.");
			return input.substring(0, 1);
		}
		System.out.println("Enter a valid input!");
		return rollOrHold();
	}
	
	//Asks the player to roll or hold and returns the input
	//(Allows the rollOrHold function to be called, without parameters)
	public String rollOrHold()
	{
		System.out.print("\n" + name + ", will you roll or hold? [r/roll|h/hold] : ");
		return rollOrHold(in.next().toUpperCase());
	}
	
	//Returns the player's name
	public String getName()
	{
		return name;
	}
	
	//Returns the player's score
	public int getScore()
	{
		return score;
	}
	
	//Returns the hold status
	public boolean getHold()
	{
		return hold;
	}
	
}
