import java.util.Scanner;

public class TimeInterval
{

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Time Interval Calculator");
		System.out.println("************************");
		System.out.print("Enter the start time: ");
		int shr = in.nextInt();
		System.out.print("                      ");
		int smin = in.nextInt();
		System.out.print("Enter the end time: ");
		int ehr = in.nextInt();
		System.out.print("                    ");
		int emin = in.nextInt();
		System.out.println("\nTime elapsed is " + (ehr-shr) + " hours and " + (emin-smin) + " minutes");
	}

}