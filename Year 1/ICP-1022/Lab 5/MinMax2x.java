import java.util.Scanner;

public class MinMax2x
{

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the first number :> ");
		int fnum = in.nextInt();
		System.out.print("Enter the second number :> ");
		int snum = in.nextInt();
		if (fnum == snum) {
			System.out.print("The first and second number are both " + fnum + " and therefore equal");
		}
		else if (fnum > snum) {
			System.out.print("The first number, " + fnum + ", is larger than the second number, " + snum);	
		}
		else {
			System.out.print("The second number, " + snum + ", is larger than the first number, " + fnum);	
		}
	}

}