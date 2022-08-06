
public class DieTester {

	public static void main(String[] args) 
	{
		Die die1 = new Die(6);
		die1.roll();
		int num = die1.getRoll();
		System.out.println("The die rolled a " + num + ".");
	}

}
