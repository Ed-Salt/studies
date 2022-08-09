import java.util.Scanner;

public class InOrder
{

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a positive integer :> ");
		int originalNo = in.nextInt();
		int magicNo = originalNo;
		magicNo--;
		magicNo *= 3;
		magicNo += 12;
		magicNo /= 3;
		magicNo += 5;
		magicNo -= originalNo;
		System.out.printf("The magic number is: " + magicNo);
	}

}