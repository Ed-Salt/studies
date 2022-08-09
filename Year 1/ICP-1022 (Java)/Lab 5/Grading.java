import java.util.Scanner;

public class Grading
{

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		boolean exit = false;
		while (exit == false) {
			System.out.print("Enter your mark :> ");
			int mark = in.nextInt();
			if (mark == 999) {
				exit = true;
			}
			else if (mark < 0 || mark > 100) {
				System.out.println("Invalid input!");
			}
			else if (mark < 40) {
				System.out.println("Your grade is F");
			}
			else if (mark <= 59) {
				System.out.println("Your grade is E");
			}
			else if (mark <= 69) {
				System.out.println("Your grade is D");
			}
			else if (mark <= 79) {
				System.out.println("Your grade is C");
			}
			else if (mark <= 89) {
				System.out.println("Your grade is B");
			}
			else if (mark <= 100) {
				System.out.println("Your grade is A");
			}
		}
	}

}