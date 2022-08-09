
public class PlayerTester {

	public static void main(String[] args) 
	{
		Player player1 = new Player(1);
		player1.score(12);
		String name = player1.getName();
		int score = player1.getScore();
		System.out.println(name + " has " + score + " points.");
		player1.score(5);
		score = player1.getScore();
		System.out.println(name + " now has " + score + " points.");
	}

}
