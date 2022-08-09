public class CompoundInterest
{

	public static void main(String[] args) 
	{
		int bal = 1000;
		System.out.println("Initial balance:            " + bal);
		double rate = 6.0;
		System.out.println("Annual interest rate (%):   " + rate);
		double year1 = bal+(bal*(rate/100));
		System.out.print("End of year 1:              ");
		System.out.printf("%1$-1.2f", year1);
		double year2 = year1+(year1*(rate/100));
		System.out.print("\nEnd of year 2:              ");
		System.out.printf("%1$-1.2f", year2);
		double year3 = year2+(year2*(rate/100));
		System.out.print("\nEnd of year 3:              ");
		System.out.printf("%1$-1.2f", year3);
	}

}