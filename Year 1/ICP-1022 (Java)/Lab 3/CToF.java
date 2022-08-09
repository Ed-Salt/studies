import java.util.Scanner;

public class CToF
{

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the temparature in Celsius :> ");
		int cel = in.nextInt();
		double fah = (cel*1.8) + 32;
		System.out.print("The temparature in Fahrenheit is ");
		System.out.printf("%1$-1.0f", fah);
		System.out.println(" degrees");
	}

}