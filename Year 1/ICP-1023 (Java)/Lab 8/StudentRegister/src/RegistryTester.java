
public class RegistryTester
{

	public static void main(String[] args)
	{
		Registry register1 = new Registry();
		Student stud1 = new Student("Steve", "Marriott", 
				"1001PG", "BSc Mathematics");
		Student stud2 = new Student("Sean", "Croassan", 
				"1002UG", "BSc Computer Science");
		Student stud3 = new Student("Alan", "McLachlan", 
				"1003UG", "BSc Computer Information Systems");
		register1.addStudent(stud1);
		register1.addStudent(stud2);
		register1.addStudent(stud3);
		System.out.println("Student Tester\n**************\n");
		System.out.println("1. Check 'addStudent' and 'toString' functions\n");
		System.out.println("Expected:\nRegistry[Student[foreName:Steve, surName:Marriott, "
				+ "studentID:1001PG, degreeScheme:BSc Mathematics], "
				+ "Student[foreName:Sean, surName:Croassan, "
				+ "studentID:1002UG, degreeScheme:BSc Computer Science], "
				+ "Student[foreName:Alan, surName:McLachlan, "
				+ "studentID:1003UG, degreeScheme:BSc Computer Information Systems]]\n");
		System.out.println("Actual: \n" + register1.toString() + "\n");
		register1.deleteStudent(stud2.getStudentID());
		System.out.println("2. Check 'deleteStudent' and 'format' functions\n(removing 1002UG - stud2)\n");
		System.out.println("Expected:\nSteve Marriott      "
				+ "1001PG    BSc Mathematics\n"
				+ "Alan McLachlan      "
				+ "1003UG    BSc Computer Information Systems\n");
		System.out.println("Actual: \n" + register1.format() + "\n");
	}
}
