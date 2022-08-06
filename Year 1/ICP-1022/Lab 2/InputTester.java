import java.util.Scanner;

public class InputTester
{

	public static void main(String[] args)
	{
		System.out.println("Multiples of 1K");
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a number :> ");
		int number = in.nextInt();
		System.out.print("Your number multiplied by 1024 is ");
		System.out.println( number * 1024);
	}

}
		