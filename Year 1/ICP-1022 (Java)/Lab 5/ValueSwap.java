import java.util.Scanner;

public class ValueSwap
{

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a value for 'x' :> ");
		int x = in.nextInt();
		System.out.print("Enter a value for 'y' :> ");
		int y = in.nextInt();
		int z = y;
		y = x;
		x = z;
		System.out.print("The values have been swapped.\n'x' is now " + x + ", and 'y' is now " + y);
	}

}