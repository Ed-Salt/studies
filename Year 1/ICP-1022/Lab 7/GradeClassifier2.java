import java.util.Scanner;

public class GradeClassifier2
{

	public static void main(String[] args) 
	{
		//Prints the title of the program
		displayTitle();
		//Takes a numerical input from the user between 0-100
		int mark = getMark();
		//Translates the mark into the appropriate grade
		char grade = determineGrade(mark);
		//Prints the grade alongside a message for whether they passed or failed
		displayGrade(grade);
	}

	public static void displayTitle()
	{
		System.out.println("Grade Classifier\n****************");
	}

	public static int getMark()
	{
		int input = -1;
		while (input > 100 || input < 0) {
			System.out.print("Enter exam mark :> ");
			Scanner in = new Scanner(System.in);
			input = in.nextInt();
		}
		return input;
	}

	public static char determineGrade(int mark)
	{
		if (mark < 40) 
			return 'F';
		
		else if (mark < 50) 
			return 'D';
		
		else if (mark < 60) 
					return 'C';
		
		else if (mark < 70) 
			return 'B';
		
		else 
			return 'A';
		
	}

	public static void displayGrade(char grade)
	{
		if (grade == 'F') {
			System.out.println("Unlucky, you are awarded a Grade F Fail.");
		}
		else {
			System.out.println("Congratulations, you are awarded a Grade " + grade + " Pass.");
		}
	}

}