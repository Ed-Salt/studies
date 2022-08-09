import java.util.Scanner;

public class QuotientRemainder
{

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Quotient and Remainder");
		System.out.println("**********************");
		System.out.print("Enter dividend :> ");
		int viden = in.nextInt();
		System.out.print("Enter divisor :> ");
		int visor = in.nextInt();
		System.out.print("Quotient = ");
		System.out.print(viden/visor);
		System.out.print("           Remainder = ");
		System.out.println(viden%visor);
		System.out.println("Program ends");
	}

}