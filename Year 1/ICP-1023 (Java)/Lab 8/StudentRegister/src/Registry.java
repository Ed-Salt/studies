import java.util.*;
import java.util.function.Predicate;
import java.util.function.Consumer;

public class Registry 
{

	LinkedList<Student> studentList = new LinkedList<Student>();
	
	//constructor
	public Registry() 
	{
	}
	
	//adds the passed Student to the list
	public void addStudent(Student aStudent)
	{
		studentList.add(aStudent);
	}
	
	//deletes the student from the list who
	//shares the passed student ID
	public void deleteStudent(String studentID)
	{		
		for (Student temp : studentList) {
			if (temp.getStudentID() == studentID ) {
				studentList.remove(temp);
			}
		}
	}
	
	//returns the values in the toString layout
	public String toString()
	{
		String tString = getClass().getName() + "[";
		for (Student temp : studentList) {
			tString = tString + temp.toString() + ", ";
		}		
		tString = tString.substring(0, tString.length()-2) + "]";
		return tString;
	}
	
	//returns the values in a formatted layout
	public String format()
	{
		String fString = "";
		for (Student temp : studentList) {
			fString = fString + temp.format() + "\n";
		}
		return fString;
	}
	
}
