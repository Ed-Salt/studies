import java.util.Scanner;

public class Mean
{

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter first number :> ");
		int first = in.nextInt();
		System.out.print("Enter second number :> ");
		int second = in.nextInt();
		System.out.print("Enter third number :> ");
		int third = in.nextInt();
		System.out.print("Enter fourth number :> ");
		int fourth = in.nextInt();
		System.out.print("Enter fifth number :> ");
		int fifth = in.nextInt();
		System.out.print("The numbers entered were ");
		System.out.println(first + "," + second + "," + third + "," + fourth + " and " + fifth);
		System.out.print("Sum:        ");
		System.out.print(first + " + " + second + " + " + third + " + " + fourth + " + " + fifth + " = ");
		double sum = first+second+third+fourth+fifth;
		System.out.printf("%1$-30.0f", sum);
		System.out.print("\nMean:       ");
		System.out.println(sum/5);
	}

}