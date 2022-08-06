import java.util.Scanner;

public class StringName
{

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter your forename :> ");
		String fname = in.next();
		System.out.print("Enter your surname :> ");
		String sname = in.next();
		String name = fname + " " + sname;
		System.out.println("Hello " + name);
		System.out.println("The length of your name is " + name.length());
	}

}