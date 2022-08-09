import java.util.Scanner;

public class OddEven
{

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		int num = 0;
			System.out.print("Enter number: > ");
			num = in.nextInt();
		while (num > -1) {
			if (num % 2 == 0) {
				System.out.println("The number you entered is even.");
			}
			else {
				System.out.println("The number you entered is odd.");
			}
			System.out.print("Enter number: > ");
			num = in.nextInt();
		}
	}

}