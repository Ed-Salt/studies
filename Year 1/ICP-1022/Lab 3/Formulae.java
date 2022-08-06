import java.util.Scanner;

public class Formulae
{

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter radius of circle :> ");
		double num = in.nextDouble();
		System.out.print("\nArea of a circle = ");
		System.out.printf("%1$-30.2f", Math.PI * (num*num));
		System.out.println(" cm\u00b2");
		System.out.print("Circumference of circle = ");
		System.out.printf("%1$-30.2f", 2*(Math.PI * num));
		System.out.println(" cm");
		System.out.print("\nSurface area of sphere = ");
		System.out.printf("%1$-30.2f", 4*(Math.PI * (num*num)));
		System.out.println(" cm\u00b2");
		System.out.print("Volume of sphere = ");
		System.out.printf("%1$-30.2f", (4.0/3)*(Math.PI*(num*num*num)));
		System.out.println(" cm\u00b3");
	}

}