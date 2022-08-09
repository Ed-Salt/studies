//pseudo code
//while array has less than 50 numbers
	//generate random number between 1-999
	//if the array doesn't contain this number already
		//add the number to the array
		//print the number
	//
//loop

import java.util.Arrays;
import java.util.Random;

public class Random50
{

	public static void main(String[] args) 
	{
		//adjustable variables
		int size = 50;
		int max = 999;
		int min = 1;
		//set variables
		Random rand = new Random();
		int count = 0;
		int newNum = 0;
		int[] numbers =  new int[size];
		//loops for every value up to the size of the array
		//incrementing count by 1 each time
		while (count < size) {
			boolean invalid = true;
			while (invalid) {
				//generates a new random number
				newNum = rand.nextInt((max - min) + 1) + min;
				//if it already exists, 'invalid' remains true and
				//the loop repeats, otherwise it becomes false and
				//the loop can exit
				invalid = Arrays.asList(numbers).contains(newNum);
			}
			numbers[count] = newNum;
			//formats and prints out the new number
			//also begins a new line if 5 numbers have been displayed
			printNumber(numbers, count, String.valueOf(max).length());
			count++;
		}
	}

	public static void printNumber(int[] numbers, int i, int len)
	{
		//formats the new number to fill up empty spaces at the start
		//with 0's to match the length of the max value (999; 3 digits)
		String num = String.format("%0" + len + "d", numbers[i]);
		System.out.print(num + " ");
		//if the current number (plus 1, to account for starting at 0)
		//is divisible by 5, then start a new line
		if ((i + 1) % 5 == 0) {
			System.out.println();
		}
	}

}