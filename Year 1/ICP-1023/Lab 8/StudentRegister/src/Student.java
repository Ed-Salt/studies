
public class Student 
{
	
	private String foreName;
	private String surName;
	private String studentID;
	private String degreeScheme;
	
	//Initialises each variable for a student
	public Student(String fName, String sName, String studID, String degScheme)
	{		
		foreName = fName;
		surName = sName;
		studentID = studID;
		degreeScheme = degScheme;
	}
	
	//changes the fore name to the passed value
	public void setForeName(String fName)
	{
		foreName = fName;
	}
	
	//changes the surname to the passed value
	public void setSurName(String sName)
	{
		surName = sName;
	}
	
	//changes the student ID to the passed value
	public void setStudentID(String studID)
	{
		studentID = studID;
	}
	
	//changes the degree scheme to the passed value
	public void setDegreeScheme(String degScheme)
	{
		degreeScheme = degScheme;
	}
	
	//returns the fore name
	public String getForeName()
	{
		return foreName;
	}
	
	//returns the surname
	public String getSurName()
	{
		return surName;
	}
	
	//returns the student ID
	public String getStudentID()
	{
		return studentID;
	}
	
	//returns the degree scheme
	public String getDegreeScheme()
	{
		return degreeScheme;
	}
	
	//returns the values in the toString layout
	public String toString()
	{
		return getClass().getName() + "[foreName:" + foreName + ", surName:" + surName + 
				", studentID:" + studentID + ", degreeScheme:" + degreeScheme + "]";
	}
		
	//returns the values in a formatted layout
	public String format()
	{
		return String.format("%-20s", foreName + " " + surName) + 
				String.format("%-10s", studentID) +
				String.format("%-20s", degreeScheme);
	}
}
