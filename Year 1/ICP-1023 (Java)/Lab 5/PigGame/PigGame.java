import java.util.Scanner;

public class PigGame {

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		System.out.println("**************\nWELCOME TO PIG\n**************");
		int playerCount = 0;
		//Loops until valid player count (2 or more) is entered
		while (playerCount < 2) {
			System.out.print("\nHow many people are playing? (2+) : ");
			String input = in.next();
			int count = 0;
			//Validates the input was a number
			for (int i = 0; i < input.length(); i++) {
				for (int j = 0; j < 10; j++) {
					String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
					if (input.substring(i, i+1).equals(nums[j])) { 
						count++;
						j = 10;
					}
				}
			}
			if (count == input.length()) {
				playerCount = Integer.parseInt(input);
			}
			if (playerCount < 2) {
				System.out.println("Enter a valid input!");
			}
		}
		Player[] players = new Player[playerCount];
		Die die = new Die(6);
		//Initialises players (asks each for their name)
		for (int i = 0; i < playerCount; i++) {
			players[i] = new Player(i+1);
		}
		int turn = 0;
		int points = 0;
		//Loops until a player gets 100 or more points
		while (players[turn].getScore() < 100) {
			//After a player decides to hold, it moves to the next player
			if (players[turn].getHold() == true) {
				players[turn].hold(false);
				points = 0;
				turn ++;
				//If turn goes out of bounds, it resets to the first player
				if (turn >= playerCount) {
					turn = 0;
				}
			}
			//Asks the player if they want to roll or hold
			String input = players[turn].rollOrHold();
			//Rolls the die
			if	(input.equals("R")) {
				die.roll();
				System.out.println(players[turn].getName() + " rolled a " + die.getRoll() + ".");
				//If the roll is a 1, then all accumulated points are lost and the player is forced to hold
				if (die.getRoll() == 1) {
					points = 0;
					System.out.println("You lost all accumulated points!");
					players[turn].hold(true);
				}
				//If the roll is 2+, then the roll is added to the accumulated points
				else {
					points += die.getRoll();
					System.out.println("You've now accumulated " + points + " points!");
				}
			}
			//Holds and adds the accumulated points to their total score
			else {
				players[turn].score(points);
				System.out.println("You added " + points + " to your total,\n" + players[turn].getName() + " now has " + players[turn].getScore() + " points.");
			}
		}
		System.out.println("\nGame over!\n" + players[turn].getName() + " passed 100 points and won!");
	}

}
