
public class Employee {
	
	private	int age;
	private String firstName;
	private String lastName;
	private String id;
	private double salary;
	
	
	public Employee(int age, String firstName, String lastName, String id, double salary) {
		super();
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.salary = salary;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [age=" + age + ", firstName=" + firstName + ", lastName=" + lastName + ", id=" + id
				+ ", salary=" + salary + "]";
	}
	
}
