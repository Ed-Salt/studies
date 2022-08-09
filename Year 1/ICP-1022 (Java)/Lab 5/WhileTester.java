public class WhileTester
{

	public static void main(String[] args) 
	{
		System.out.println("WhileTester running ...");
		int count = 0;
		System.out.println("Loop to print values 0 to 9 inclusive");
		while (count < 9) {
			System.out.print(count + ", ");
			count++;
		}
		System.out.println(count + "\n");
		//loops through 9 times to print 0-8 with ", "
		//prints 9 without comma outside the loop
		count = 99;
		System.out.println("Loop to print values 99 to 50 inclusive");
		while (count > 50) {
			System.out.print(count + ", ");
			count--;
		}
		System.out.println(count + "\n");
		//starts count at 99 and prints each number to 50 by
		//reducing the count by 1 with each loop
		count = 50;
		System.out.println("Loop to print even numbers between 50 and 100 inclusive");
		while (count < 100) {
			System.out.print(count + ", ");
			count += 2;
		}
		System.out.println(count + "\n");
		//starts count at 50 and adds 2 to the count
		//every cycle up to 100
		count = 0;
		System.out.println("Loop to print first 20 numbers in the sequence 0, 8, 16, 24, ...");
		while (count < 19) {
			System.out.print((count*8) + ", ");
			count++;
		}
		System.out.println((count*8) + "\n");
		//loops through the numbers 0-19 and outputs
		//the count multiplied by 8
		count = 0;
		System.out.println("Loop to print first 10 numbers in the sequence 1, 2, 4, 8, ...");
		while (count < 9) {
			System.out.print(Math.round(Math.pow(2, count)) + ", ");
			count++;
		}
		System.out.println(Math.round(Math.pow(2, count)) + "\n");
		//counts from 0-9 and with each print it displays the
		//the output given when 2 is put to the power of count's value
		count = 2;
		int count2 = 1;
		System.out.println("Loop to print first 10 numbers in the sequence 1, 2, 0, 3, -1, 4, -2, ...");
		while (count < 6) {
			System.out.print(count2 + ", ");
			count2--;
			System.out.print(count + ", ");
			count++;
		}
		System.out.println(count2 + ", " + count + "\n");
		//uses two counts, one counting up, the other counting down,
		//and alternates the output between each counter
		count = 1;
		count2 = 0;
		System.out.println("Loop to print the first 10 triangle numbers");
		while (count < 10) {
			System.out.print((count2 += count) + ", ");
			count++;
		}
		System.out.println((count2 += count) + "\n");
		//uses the second count to store the sum value given with
		//each output
		count = 0;
		int[] prevCount =  new int[12];
		prevCount[count] = 1;
		System.out.println("Loop to print first 12 numbers in the sequence 1, 1, 1, 2, 2, 3, 4, 5, 7, 9, 12, 16 ...");
		while (count < 11) {
			System.out.print(prevCount[count] + ", ");
			count++;
			if (count <= 2) {
				prevCount[count] = 1;
			}
			else {
				prevCount[count] = prevCount[count - 2] + prevCount[count - 3];
			}
		}
		System.out.println(prevCount[count] + "\n");
		//stores every value in the prevCount array, then once the
		//count is greater than 2, it outputs the value from 2 cycles ago added
		//to the value from 3 cycles ago
	}

}
