public class PersonTester
{
	public static void main(String[] args) 
	{
		Person person1 = new Person("joe", "smith", 25, 1.57, "male");
		System.out.println(person1.getFName());
		System.out.println(person1.getSName());
		System.out.println(person1.getAge());
		System.out.println(person1.getHeight());
		System.out.println(person1.getGender());
		System.out.println(person1.toString());
		System.out.println(person1.format());
		Person person2 = new Person("sian", "davis", 18, 1.73, "female");
		System.out.println(person2.format());
		Person person3 = new Person("sam", "jones", 19, 1.80, "male");
		System.out.println(person3.format());
		Person person4 = new Person("masie", "long", 32, 1.72, "female");
		System.out.println(person4.format());
		Person person5 = new Person("harry", "kirby", 14, 1.40, "male");
		System.out.println(person5.format());
		System.out.println("PersonCount:  " + Person.getPersonCount());
	}


}