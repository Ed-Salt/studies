import java.util.Scanner;

public class DivisionTester
{

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter first number :> ");
		int num1 = in.nextInt();
		System.out.print("Enter second number :> ");
		int num2 = in.nextInt();
		System.out.println("Integer Division");
		System.out.println("Quotient " + num1 + " / " + num2 + " = " + (num1/num2) + "      Remainder   " + num1 + " % " + num2 + " = " + (num1%num2));
		System.out.println("         " + num1 + " / " + (-num2) + " = " + (num1/(-num2)) + "                " + num1 + " % " + (-num2) + " = " + (num1%(-num2)));
		System.out.println("        " + (-num1) + " / " + num2 + " = " + ((-num1)/num2) + "                " + (-num1) + " % " + num2 + " = " + ((-num1)%num2));
		System.out.println("        " + (-num1) + " / " + (-num2) + " = " + ((-num1)/(-num2)) + "                " + (-num1) + " % " + (-num2) + " = " + ((-num1)%(-num2)));
		float flo1 = num1;
		float flo2 = num2;
		System.out.println("Floating Point Division (No remainder)");
		System.out.println("Quotient " + flo1 + " / " + flo2 + " = " + (flo1/flo2));
		System.out.println("         " + flo1 + " / " + (-flo2) + " = " + (flo1/(-flo2)));
		System.out.println("        " + (-flo1) + " / " + flo2 + " = " + ((-flo1)/flo2));
		System.out.println("        " + (-flo1) + " / " + (-flo2) + " = " + ((-flo1)/(-flo2)));
	}

}