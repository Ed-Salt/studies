import java.util.Scanner;

public class RegistryCLI
{

	private static Registry theRegistry;
		
	//initialises the registry
	public RegistryCLI(Registry aRegistry)
	{
		theRegistry = aRegistry;
	}
			
	//prints the menu and takes the users choice
	public void doMenu()
	{
		Scanner in = new Scanner(System.in);
		int choice = 0;
		//loops until 4 (quit) is chosen
		while (choice != 4) {
			System.out.print("Registry Main Menu\n******************\n\n"
					+ "1. Add a Student\n"
					+ "2. Delete a Student\n"
					+ "3. Print Registry\n"
					+ "4. Quit\n"
					+ ":> ");
			choice = in.nextInt();
			//validation
			while (choice > 4 || choice < 1) {
				System.out.print("Invalid input!\n\n:>");
				choice = in.nextInt();			
			}
			if (choice == 1) {
				doAddStudent();
			}
			else if (choice == 2) {
				doDeleteStudent();
			}
			else if (choice == 3) {
				doPrintRegistry();
			}
		}
	}
	
	//asks for student details
	//creates the student and adds to the registry
	public static void doAddStudent()
	{
		Scanner in = new Scanner(System.in);
		String cont = "Y";
		while (cont.equals("Y")) {
			System.out.println("\n\nAdd New Student\n***************\n");
			System.out.printf("%-20s:> ", "Enter forename");
			String fName = in.next();
			System.out.printf("%-20s:> ", "Enter surname");
			String sName = in.next();
			System.out.printf("%-20s:> ", "Enter student ID");
			String studID = in.next();
			System.out.printf("%-20s:> ", "Enter degree scheme");
			String degScheme = in.next();
			Student aStudent = new Student(fName, sName, studID, degScheme);
			theRegistry.addStudent(aStudent);
			System.out.printf("%-20s:> ", "Enter another? [Y/N]");
			cont = in.next().toUpperCase();
			//validation
			while (!cont.equals("Y") && !cont.equals("N")) {
				System.out.println("Invalid input!\n");
				cont = in.next().toUpperCase();
			}
		}
	}
	
	//asks for a student ID
	//removes the student from the registry with the same ID
	public void doDeleteStudent()
	{
		Scanner in = new Scanner(System.in);
		String cont = "Y";
		while (cont == "Y") {
			System.out.println("\n\nDelete Student\n**************\n");
			System.out.printf("%-20s:> ", "Enter student's ID");
			String studID = in.next();
			theRegistry.deleteStudent(studID);
			System.out.printf("%-20s:> ", "Delete another? [Y/N]");
			cont = in.next().toUpperCase();
			while (!cont.equals("Y") && !cont.equals("N")) {
				System.out.println("Invalid input!\n");
				cont = in.next().toUpperCase();
			}
		}
	}
	
	//prints the content of the registry
	public void doPrintRegistry()
	{
		System.out.println("\n\nPrint Register\n**************\n");
		System.out.println(theRegistry.format());
	}

}
