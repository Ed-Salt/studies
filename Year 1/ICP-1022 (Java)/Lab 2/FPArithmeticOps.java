import java.util.Scanner;

public class FPArithmeticOps
{

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a double  :> ");
		double num1 = in.nextDouble();
		System.out.print("Enter a double  :> ");
		double num2 = in.nextDouble();
		System.out.print("\nSum:               ");
		System.out.printf("%1$-30.2f", num1+num2);
		System.out.print("\nDifference:       ");
		System.out.printf("%1$-30.2f", num1-num2);
		System.out.print("\nProduct:           ");
		System.out.printf("%1$-30.2f", num1*num2);
		System.out.print("\nQuotient:          ");
		System.out.printf("%1$-30.2f", num1/num2);
		System.out.print("\nMean:              ");
		System.out.printf("%1$-30.2f", (num1+num2)/2);
		System.out.print("\nMax:               ");
		if (num2 > num1) 
		{
			System.out.printf("%1$-30.2f", num2);
			System.out.print("\nMin:               ");
			System.out.printf("%1$-30.2f", num1);
		}
		else 
		{
			System.out.printf("%1$-30.2f", num1);
			System.out.print("\nMin:               ");
			System.out.printf("%1$-30.2f", num2);
		}
		System.out.println();
	}

}