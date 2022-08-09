import java.util.Scanner;

public class CircleCalculator
{
	public static void main(String[] args)
	{
		double radius = getRadius();
		double area = findCircleArea(radius);
		double circumference = findCircleCircumference(radius);
 		printArea(area, radius, circumference);
	}

	// Use a scanner to obtain radius
	public static double getRadius()
	{
 		//...

		Scanner in = new Scanner(System.in);
		System.out.print("Enter the radius of a circle: ");
		return in.nextDouble();
	}

	// Calculate area using radius
	public static double findCircleArea(double radius)
	{
 		//...

 		return (Math.PI * (Math.pow(radius, 2)));
	}

	//Calculate circumference using radius
	public static double findCircleCircumference(double radius)
	{
		return (2 * Math.PI * radius);
	}

	// Display radius and area
	public static void printArea(double area, double radius, double circumference)
	{
		//...

		System.out.println("The area of this circle is " + area);
		System.out.println("The circumference of this circle is " + circumference);
	}
}