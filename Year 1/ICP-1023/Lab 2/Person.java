

public class Person
{
	private static int personCount;
	private String fName;
	private String sName;
	private Integer age;
	private Double height;
	private String gender;

	//Initiates the Person class
	//Increments personCount
	public Person(String fN, String sN, int ag, double hei, String gen)
	{
		fName = fN;
		sName = sN;
		age = ag;
		height = hei;
		gender =  gen;
		personCount += 1;
	}

	//Returns personCount value
	public static int getPersonCount()
	{
		return personCount;
	}

	//Returns forename
	public String getFName()
	{
		return fName;
	}

	//Returns surname
	public String getSName()
	{
		return sName;
	}

	//Returns age
	public Integer getAge()
	{
		return age;
	}

	//Returns height
	public Double getHeight()
	{
		return height;
	}

	//Returns gender
	public String getGender()
	{
		return gender;
	}
 	
 	//Sets forename
	public void setFName(String fN)
	{
		fName = fN;
	}

	//Sets surname
	public void setSName(String sN)
	{
		sName = sN;
	}

	//Sets age
	public void setAge(int ag)
	{
		age = ag;
	}

	//Sets height
	public void setHeight(double hei)
	{
		height = hei;
	}

	//Sets gender
	public void setGender(String gen)
	{
		gender = gen;
	}
 
	public String toString()
	{
		return getClass().getName() + 
			"[foreName=" + fName + 
			", surName=" + sName +
			", age=" + age +
			", height=" + height +
			",gender=" + gender +
			"]";
	}

	public String format()
	{
		return String.format("%-10s", sName) +
		String.format("%-10s", fName) +
		String.format("%-10s", age) +
		String.format("%-10s", height) +
		String.format("%-10s", gender);
	}

}