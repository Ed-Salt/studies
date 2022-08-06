
public class StudentTester 
{

	public static void main(String[] args) 
	{
		Student stud1 = new Student("Steve", "Marriott", 
				"1001PG", "BSc Mathematics");
		Student stud2 = new Student("Sean", "Croassan", 
				"1002UG", "BSc Computer Science");
		Student stud3 = new Student("Alan", "McLachlan", 
				"1003UG", "BSc Computer Information Systems");
		System.out.println("Student Tester\n**************\n");
		System.out.println("1. Check 'get...' and 'toString' functions\n");
		System.out.println("Expected:\nStudent[foreName:Steve, surName:Marriott, "
				+ "studentID:1001PG, degreeScheme:BSc Mathematics]\n"
				+ "Student[foreName:Sean, surName:Croassan, "
				+ "studentID:1002UG, degreeScheme:BSc Computer Science]\n"
				+ "Student[foreName:Alan, surName:McLachlan, "
				+ "studentID:1003UG, degreeScheme:BSc Computer Information Systems]\n");
		System.out.println("Actual:\n" + stud1.toString() + "\n"
				+ stud2.toString() + "\n"
				+ stud3.toString() + "\n");
		System.out.println("2. Check 'set...' and 'format' functions\n(Changing 1003UG - stud3)\n");
		stud3.setForeName("John");
		stud3.setSurName("Smith");
		stud3.setStudentID("1004PG");
		stud3.setDegreeScheme("BSc Electronic Engineering");
		System.out.println("Expected:\nJohn Smith          "
				+ "1004PG    BSc Electronic Engineering\n");
		System.out.println("Actual:\n" + stud3.format() + "\n");

	}

}
