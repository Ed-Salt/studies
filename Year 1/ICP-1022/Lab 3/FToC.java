import java.util.Scanner;

public class FToC
{

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the temparature in Fahrenheit :> ");
		int fah = in.nextInt();
		double cel = (fah-32) / 1.8;
		System.out.print("The temparature in Celsius is ");
		System.out.printf("%1$-1.0f", cel);
		System.out.println(" degrees");
	}

}