/*
Imperative Programming in C
ICP-1029 Lab 6 : Jackpot Dreams
Ed Salt | dns18cch
*/

#include "pch.h"
#include <iostream>
#include <time.h>

const int size = 6;
int* getLottoDraw();
void printArray(int* ray, int year);
int findMatches(int* ray1, int* ray2);

int main()
{
	//welcome
	srand(time(0));
	printf("\n---------------------------\n  Project : Jackpot Dreams\n---------------------------\n");
	int myNum[size] = { 5,11,15,33,42,43 };
	printf("\nHow many years in sleep? > ");
	int years = 0;
	scanf_s("%d", &years);
	if (years > 1) {
		printf("\nOK. I'll play the lottery for %d years.\n\n...\n", years);
	}
	else {
		printf("\nOK. I'll play the lottery for %d year.\n\n...\n", years);
	}
	//loops for the number of years or until the lottery is won
	int finalYear = years;
	int weeks = 0;
	int matches[size + 1] = { 0 };
	for (int i = 0; i < years;) {
		//after 52 weeks, increments the years
		if (weeks > 51) {
			i++;
			weeks = 0;
		}
		int *lotNum;
		lotNum = getLottoDraw();
		int matchCount = findMatches(lotNum, myNum);
		matches[matchCount]++;
		//when a full match is found, it registers the current year, 
		//exits the loop and displays the numbers
		if (matchCount >= 6) {
			finalYear = i;
			i = years;
			printf("\nMatched the lottery numbers : %d %d %d %d %d %d", lotNum[0], lotNum[1], lotNum[2], lotNum[3], lotNum[4], lotNum[5]);
		}
		weeks++;
	}
	//prints the counts for each number of matchups
	printArray(matches, finalYear);
	getchar();
}

//randomly generates 6 unique numbers as the lottery numbers
int * getLottoDraw()
{
	static int lotNum[size] = { 0 };
	int max = 49;
	for (int i = 0; i < size; i++) {
		int ranNum = (rand() % max) + 1;
		bool check = true;
		//if the new number isn't already in the list, then it gets added
		for (int j = 0; j < i; j++) {
			if (lotNum[j] == ranNum) {
				i--;
				j = i;
				check = false;
			}
		}
		if (check) {
			lotNum[i] = ranNum;
		}
	}
	return lotNum;
}

//prints the matchups and current year
void printArray(int* ray, int year)
{
	printf("\nWake up! It's the year %d.\n\n", year+2019);
	for (int i = 0; i < size + 1; i++) {
		printf("Matched %d numbers %d times\n", i, ray[i]);
	}
}

//finds all matches between the lottery numbers and the users numbers
int findMatches(int* ray1, int* ray2)
{
	int count = 0;
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			if (ray1[i] == ray2[j]) {
				count++;
			}
		}
	}
	return count;
}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
