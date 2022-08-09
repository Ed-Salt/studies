/*
Imperative Programming in C
ICP-1029 Lab 4 : Word Scrambler
Ed Salt | dns18cch
*/

#include "pch.h"
#include <iostream>
#include <string.h>

int main()
{
	printf("\n------------------------\n");
	printf("WELCOME TO THE SCRAMBLER\n------------------------\n\n");
	printf("Please enter your string : ");
	char string[64];
	char cipher[128];
	scanf_s("%s", string, 64);
	printf("\nYour string is : %s\n\n", string);
	bool exit = false;
	int count = 0;
	//Loops asking user for a target/replace char
	//until the user enters qq
	while (exit == false) {
		printf("Enter 'qq' to exit\n");
		printf("Target char : ");
		char input[3];
		scanf_s("%s", input, 3);
		//If the input isn't qq, then it will take the first char inputted
		//as the target or, if 3 chars are entered with the second being a space, 
		//it will also take the third char as the replacer
		if (input[1] != 'q' || input[0] != 'q') {
			char target = input[0];
			printf("Replace with : ");
			char replace = input[2];
			if (isspace(input[1])) {
				printf("\n");
			}
			else {
				scanf_s(" %c", &replace);
			}
			//Adds the target and replacer to the cipher string
			cipher[count] = target;
			cipher[count + 1] = replace;
			count += 2;
			printf("\nReplacing %c with %c\n", target, replace);
			char temp[64];
			strncpy_s(temp, string, 64);
			//Loops through each character in the string to find a target to replace
			//If it find a char that matches the replacer, then it stop searching and 
			//returns the string to it's previous state
			for (int i = 0; i < 64; i++) {
				if (string[i] == target) {
					string[i] = replace;
				}
				else if (string[i] == replace) {
					strncpy_s(string, temp, 64);
					printf("\n%c already exists in the string\n", replace);
					i = 64;
				}
			}
			printf("Your string is : %s\n\n", string);
		}
		else {
			exit = true;
		}
		printf("\n\nYour final scrambled string is : %s\n", string);
	}
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
