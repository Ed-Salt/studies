import java.util.Scanner;

public class MinMax3
{
	public static void main(String[] args)
	{
		int num1 = getInteger();
		int num2 = getInteger();
		int num3 = getInteger();
		int max = max3(num1, num2, num3);
		int min = min3(num1, num2, num3);
		printMaxMin(max, min);
	}
	
	public static int getInteger()
	{
		// Declare and create a Scanner
		// to read input values

		Scanner in = new Scanner(System.in);
		System.out.print("Enter an integer: ");
		int num = in.nextInt();
		return num;
	}

	public static int max3(int n1, int n2, int n3)
	{
		//...

		System.out.println("You entered three integers");
		int maxN = Math.max(n1, n2);
		return Math.max(maxN, n3);
	}

	public static int min3(int n1, int n2, int n3)
	{
		//...

		int minN = Math.min(n1, n2);
		return Math.min(minN, n3);
	}

	public static void printMaxMin(int n1, int n2)
	{
		//...

		System.out.println("Largest value is " + n1);
		System.out.println("Smallest value is " + n2);
	}

}