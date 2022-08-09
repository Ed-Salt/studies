public class GradeCalculator
{

	public static void main(String[] args)
	{
		System.out.println("Class Tests");
		System.out.println("Class Test 1    7/20");
		System.out.println("Class Test 2    13/20");
		System.out.println("Class Test 3    15/20");
		System.out.println("Class Test 4    10/20");
		System.out.print("Total           45/80 = ");
		System.out.printf("%1$-1.0f", ((45.0/80)*100));
		System.out.println("%\n");
		System.out.println("Laboratory Work");
		System.out.println("Lab 1           8/10");
		System.out.println("Lab 2           8/10");
		System.out.println("Lab 3           9/10");
		System.out.println("Lab 4           7/10");
		System.out.println("Lab 5           8/10");
		System.out.print("Total           40/50 = ");
		System.out.printf("%1$-1.0f", ((40.0/50)*100));
		System.out.println("%\n");
		System.out.println("Weighted Total = (56% * 0.6) + (80% * 0.4)");
		System.out.print("               = 33.6 + 32 = 65.8\n               = ");
		System.out.printf("%1$-1.0f", ((((45.0/80)*100)*0.6)+(((40.0/50)*100)*0.4)));
		System.out.println("%");
	}

}