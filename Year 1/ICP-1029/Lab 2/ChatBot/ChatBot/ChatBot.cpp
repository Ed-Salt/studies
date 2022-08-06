// ChatBot.cpp : This file contains the 'main' function. 
// Program execution begins and ends there.
//

#include "pch.h"
#include <iostream>

int main()
{
	printf("------------\nChatbot v0.1\n------------\n");
	printf("I am a chatbot\nYou are a human\nI have five questions for you!\n\n");
	//asks questions and stores user input
	printf("What is your first name? ");
	char fName[32];
	scanf_s("%s", fName, 32);
	printf("What is your surname? ");
	char sName[32];
	scanf_s("%s", sName, 32);
	printf("What year were you born? ");
	int yearOB;
	scanf_s("%d", &yearOB);
	printf("How tall are you in centimeters? ");
	double height;
	scanf_s("%lf", &height);
	printf("What is your weight in kilograms? ");
	double weight;
	scanf_s("%lf", &weight);
	//performs calculations to display info in appropriate format
	int age = ((2018 - yearOB));
	double hFeet = ((height/30.48));
	double wPnd = ((weight*2.205));	
	//displays info
	printf("\nYour name is %s %s. You are about %d years old\n", fName, sName, age);
	if (floor(hFeet) == hFeet && floor(wPnd) == wPnd) {
		printf("You are about %.0f feet tall and weight about %.0f pounds\n\n", hFeet, wPnd);
	}
	else if (floor(hFeet) == hFeet) {
		printf("You are about %.0f feet tall and weight about %.2f pounds\n\n", hFeet, wPnd);
	}
	else if (floor(wPnd) == wPnd) {
		printf("You are about %.2f feet tall and weight about %.0f pounds\n\n", hFeet, wPnd);
	}
	else {
		printf("You are about %.2f feet tall and weight about %.2f pounds\n\n", hFeet, wPnd);
	}
	system("PAUSE");

}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or 
//      Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project 
//      and select the .sln file
