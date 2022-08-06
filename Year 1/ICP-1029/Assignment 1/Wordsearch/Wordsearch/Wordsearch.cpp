/* 
Imperative Programming in C
ICP-1029 Assignment : Word Search
Ed Salt | dns18cch
*/

#include "pch.h"
#include <iostream>
#include <string.h>
#include <time.h>
#include <stdio.h>
#include <windows.h>

#define ANSI_COLOR_GREEN   "\x1b[32m"
#define ANSI_COLOR_RESET   "\x1b[0m"

//constant variables
const int size = 12;
const int wordCount = 6;
const int alp = 26;
const int asc = 65;
const char X = 'x';
const char Y = 'y';
const char blank = '.';
const int FRUIT = 1; const int ANIMALS = 2; 
const int GENI = 3; const int GENII = 4; const int GENIII = 5; const int GENIV = 6; 
const int GENV = 7; const int GENVI = 8; const int GENVII = 9; const int GENVIII = 10;
//functions
int welcome();
char **create2DArray();
void printArray(char** array, int size);
bool insertHorizontally(char* word, char** array, int*** answers, int &ansCount);
char ** getCategoryWords(int choice);
int spaceCheck(char** array, char* word, char dir, int y, int x, int i, int j);
int validatePos(int c, int len, int val);
int validateCoords(char dir, int len, char** array, char* word, int y, int x, int n, int m);
void insertWord(int len, int val, char** array, char* word, int*** answers, int &ansCount, int y, int x, int n, int m);
bool insertVertically(char* word, char** array, int*** answers, int &ansCount);
bool insertDiagonally(char* word, char** array, int*** answers, int &ansCount);
void sortWords(char ** array);
int diagonalValFit(int pos, int len);
char ** fillArray(char ** array, char fillType);
bool doInsert(char* word, char** array, int*** answers, int &ansCount, int choice);
int *** createAnsArray();

//
int main()
{
	srand(time(0));
	int choice = welcome();
	char **grid = create2DArray();
	char **words = getCategoryWords(choice);
	int ***answers = createAnsArray();
	int ansCount = 1;
	//inserts all words in the category, then fills the empty spaces with random letters
	for (int i = 0; i < wordCount; i++) {
		int num = (rand() % 5);
		if (doInsert(words[i], grid, answers, ansCount, num) == false) {
			i--;
		}
	}
	fillArray(grid, asc);
	ansCount = 0;
	char found[wordCount][size];
	bool endGame = false;
	printf("\n\nYou have 3 minutes to complete this...\n");
	Sleep(1000);
	printf("3\n");
	Sleep(1000);
	printf("2\n");
	Sleep(1000);
	printf("1\n");
	Sleep(1000);
	clock_t start = clock();
	//loops while the game is being played
	//exits if time runs out or all words are found
	while (ansCount < wordCount && endGame == false) {
		printArray(grid, size);
		char word[size];
		scanf_s("%s", word, size);
		for (int i = 1; i <= wordCount; i++) {
			_strupr_s(word, size);
			if (strcmp(words[i - 1], word) == 0) {
				if (strcmp(found[i - 1], word) != 0) {
					strncpy_s(found[i-1], word, size);
					ansCount++;
					for (int n = 0; n < size; n++) {
						for (int m = 0; m < size; m++) {
							//uses the positions in answers to find the found words in the grid and turns them to lowercase to be highlighted
							if (answers[i][n][m] == i) {
								grid[n][m] = tolower(grid[n][m]);
							}		
						}
					}
				}
				i = wordCount;
			}
		}
		clock_t end = clock();
		float seconds = (float)(end - start) / CLOCKS_PER_SEC;
		//if 3 minutes have passed, end the game
		if (seconds >= 180) {
			endGame = true;
		}
	}
	//displays win/lose message to the player with time taken/number of words found
	if (endGame) {
		printf("\nTimes up! You found %d out of %d words.\n\nGame over!\n", ansCount, wordCount);
	}
	else {
		clock_t end = clock();
		float seconds = (float)(end - start) / CLOCKS_PER_SEC;
		printArray(grid, size);
		if (seconds > 60) {
			float minutes = floor((seconds/60));
			seconds -= (minutes*60);
			printf("\nWinner winner, chicken dinner!\n\nYou won in %0.0f minutes and %0.0f seconds\n", minutes, seconds);
		}
		else {
			printf("\nWinner winner, chicken dinner!\n\nYou won in %0.0f seconds\n", seconds);
		}
	}
}

//prints welcome message and takes user's choice input(s)
int welcome()
{
	printf("---------------\nWordsearch Game\n---------------\n");
	printf("Welcome player. Which category would you like to play?\n");
	printf("1. Fruit\n2. Animals\n3. Pokemon\n:> ");
	int inp = 0;
	scanf_s("%d", &inp);
	while (inp > 3 || inp < 1) {
		printf("Invalid input!\n\n");
		scanf_s("%d", &inp);
	}
	if (inp == FRUIT) {
		printf("\nCategory: Fruit\n");
	}
	else if (inp == ANIMALS) {
		printf("\nCategory: Animals\n");
	}
	else {
		printf("\nCategory: Pokemon\n\nPlease enter a Pokemon generation [1-8]\n:> ");
		int gen = 0;
		scanf_s("%d", &gen);
		while (gen > 8 || gen < 1) {
			printf("Invalid input!\n\n");
			scanf_s("%d", &gen);
		}
		inp += gen-1;
	}
	return inp;
}

//initialises a new 2D grid array
char ** create2DArray()
{
	char** aRay = new char*[size];
	for (int i = 0; i < size; i++) {
		aRay[i] = new char[size];
	}
	return fillArray(aRay, blank);
}

//fills the array with '.' unless another character is passed, then filles with randomly generated letters
char ** fillArray(char ** array, char fillType)
{
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			if (fillType != blank) {
				if (array[i][j] == blank) {
					array[i][j] = (rand() % (alp)) + asc;
				}
			}
			else {
				array[i][j] = fillType;
			}
		}
	}
	return array;
}

//creates an array of words depending on the user's choice
char ** getCategoryWords(int choice)
{
	char** aRay = new char*[wordCount];
	for (int i = 0; i < wordCount; i++) {
		aRay[i] = new char[size];
	}
	if (choice == FRUIT) {
		strcpy_s(aRay[0], size, "ORANGE");
		strcpy_s(aRay[1], size, "APPLE");
		strcpy_s(aRay[2], size, "BANANA");
		strcpy_s(aRay[3], size, "MELON");
		strcpy_s(aRay[4], size, "KIWI");
		strcpy_s(aRay[5], size, "POMEGRANITE");
	}
	else if (choice == ANIMALS) {
		strcpy_s(aRay[0], size, "LION");
		strcpy_s(aRay[1], size, "TIGER");
		strcpy_s(aRay[2], size, "ZEBRA");
		strcpy_s(aRay[3], size, "HORSE");
		strcpy_s(aRay[4], size, "CAT");
		strcpy_s(aRay[5], size, "ELEPHANT");
	}
	else if (choice == GENI) {
		strcpy_s(aRay[0], size, "PIKACHU");
		strcpy_s(aRay[1], size, "MEW");
		strcpy_s(aRay[2], size, "MEWTWO");
		strcpy_s(aRay[3], size, "EEVEE");
		strcpy_s(aRay[4], size, "SNORLAX");
		strcpy_s(aRay[5], size, "MOLTRES");
	}
	else if (choice == GENII) {
		strcpy_s(aRay[0], size, "SUICUNE");
		strcpy_s(aRay[1], size, "SCIZOR");
		strcpy_s(aRay[2], size, "ENTEI");
		strcpy_s(aRay[3], size, "PICHU");
		strcpy_s(aRay[4], size, "LUGIA");
		strcpy_s(aRay[5], size, "TOGEPI");
	}
	else if (choice == GENIII) {
		strcpy_s(aRay[0], size, "GARDEVOIR");
		strcpy_s(aRay[1], size, "KYOGRE");
		strcpy_s(aRay[2], size, "METAGROSS");
		strcpy_s(aRay[3], size, "LATIOS");
		strcpy_s(aRay[4], size, "DEOXYS");
		strcpy_s(aRay[5], size, "LATIAS");
	}
	else if (choice == GENIV) {
		strcpy_s(aRay[0], size, "DARKRAI");
		strcpy_s(aRay[1], size, "GIRATINA");
		strcpy_s(aRay[2], size, "ABOMASNOW");
		strcpy_s(aRay[3], size, "LUCARIO");
		strcpy_s(aRay[4], size, "DIALGA");
		strcpy_s(aRay[5], size, "ARCEUS");
	}
	else if (choice == GENV) {
		strcpy_s(aRay[0], size, "VICTINI");
		strcpy_s(aRay[1], size, "ZOROARK");
		strcpy_s(aRay[2], size, "GENESECT");
		strcpy_s(aRay[3], size, "KYUREM");
		strcpy_s(aRay[4], size, "KELDEO");
		strcpy_s(aRay[5], size, "MELOETTA");
	}
	else if (choice == GENVI) {
		strcpy_s(aRay[0], size, "FENNEKIN");
		strcpy_s(aRay[1], size, "XERNEAS");
		strcpy_s(aRay[2], size, "CHESPIN");
		strcpy_s(aRay[3], size, "GRENINJA");
		strcpy_s(aRay[4], size, "DEDENNE");
		strcpy_s(aRay[5], size, "FLETCHLING");
	}
	else if (choice == GENVII) {
		strcpy_s(aRay[0], size, "INCINEROAR");
		strcpy_s(aRay[1], size, "SOLGALEO");
		strcpy_s(aRay[2], size, "LUNALA");
		strcpy_s(aRay[3], size, "MARSHADOW");
		strcpy_s(aRay[4], size, "MIMIKYU");
		strcpy_s(aRay[5], size, "PYUKUMUKU");
	}
	else if (choice == GENVIII) {
		strcpy_s(aRay[0], size, "MELTAN");
		strcpy_s(aRay[1], size, "MELMETAL");
		strcpy_s(aRay[2], size, "GROOKEY");
		strcpy_s(aRay[3], size, "SCORBUNNY");
		strcpy_s(aRay[4], size, "SOBBLE");
		strcpy_s(aRay[5], size, "ZERAORA");		
	}
	sortWords(aRay);
	return aRay;
}

//sorts the word array so that the biggest word is first
//this helps prevent words conflicting as more are added to the grid
void sortWords(char ** array)
{
	for (int i = 0; i < wordCount; i++) {
		for (int j = 0; j < wordCount - i - 1; j++) {
			if (strlen(array[j]) < strlen(array[j + 1])) {
				char* temp = array[j];
				array[j] = array[j + 1];
				array[j + 1] = temp;
			}
		}
	}
}

//creates a 3D array to store the locations of where each word in the category is added to the grid
//makes finding and highlighting a correctly found word later easier
int *** createAnsArray()
{
	int*** aRay = new int**[wordCount+1];
	for (int i = 0; i <= wordCount; i++) {
		aRay[i] = new int*[size];
		for (int j = 0; j < size; j++) {
			aRay[i][j] = new int[size];
		}
	}
	return aRay;
}

//prints the array passed into it
//Lowercase letters (of 'found' words) are changed to uppercase and coloured green
void printArray(char** array, int size)
{
	system("cls");
	printf("\n");
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			if (toupper(array[i][j]) != array[i][j]) {
				printf(ANSI_COLOR_GREEN " %c" ANSI_COLOR_RESET, toupper(array[i][j]));
			}
			else {
				printf(" %c", array[i][j]);
			}
		}
		printf("\n");
	}
	printf("\n------------------------------------\n");
}

//chooses which insert method to use for the next word based on a randomly generated number from 0-4
bool doInsert(char* word, char** array, int*** answers, int &ansCount, int choice)
{
	if (choice % 3 == 0) { //if 0 or 3
		return insertHorizontally(word, array, answers, ansCount);
	}
	else if (choice % 2 == 0) { //if 2 or 4
		return insertVertically(word, array, answers, ansCount);
	} //if 1
	return insertDiagonally(word, array, answers, ansCount);
}

//inserts the word horizontally (forward or backward, depending on space)
bool insertHorizontally(char* word, char** array, int*** answers, int &ansCount)
{
	int val = 0;
	int xPos = 0;
	int	yPos = 0;
	size_t len = strlen(word);
	int attempts = 0;
	while (val == 0 && attempts < len*2) {
		attempts++;
		xPos = (rand() % (size));
		yPos = (rand() % (size));
		val = validateCoords(X, len, array, word, yPos, xPos, 0, 1);
	}
	if (attempts >= len * 2) {
		return false;
	}
	insertWord(len, val, array, word, answers, ansCount, yPos, xPos, 0, 1);
	return true;
}

//inserts the word vertically ''
bool insertVertically(char* word, char** array, int*** answers, int &ansCount)
{
	int val = 0;
	int xPos = 0;
	int	yPos = 0;
	size_t len = strlen(word);
	int attempts = 0;
	while (val == 0 && attempts < len*2) {
		attempts++;
		xPos = (rand() % (size));
		yPos = (rand() % (size));
		val = validateCoords(Y, len, array, word, yPos, xPos, 1, 0);
	}
	if (attempts >= len * 2) {
		return false;
	}
	insertWord(len, val, array, word, answers, ansCount, yPos, xPos, 1, 0);
	return true;
}

//inserts the word diagonally (direction depends on space)
bool insertDiagonally(char* word, char** array, int*** answers, int &ansCount)
{
	int xVal = 0;
	int yVal = 0;
	int xPos = 0;
	int	yPos = 0;
	int attempts = 0;
	size_t len = strlen(word);	
	while ((xVal == 0 || yVal == 0) && attempts < len*2) {
		attempts++;
		xPos = (rand() % (size));
		yPos = (rand() % (size));
		xVal = diagonalValFit(xPos, len);
		yVal = diagonalValFit(yPos, len);
		if (xVal == yVal) {
			if (validateCoords(X, len, array, word, yPos, xPos, 1, 1) == 0) {
				xVal = 0;
			}
		}
		else {
			if (validateCoords(Y, len, array, word, yPos, xPos, 1, -1) == 0) {
				yVal = 0;
			}
		}
	}
	if (attempts >= len * 2) {
		return false;
	}
	if (xVal == yVal) {
		insertWord(len, xVal, array, word, answers, ansCount, yPos, xPos, 1, 1);
	}
	else {
		insertWord(len, yVal, array, word, answers, ansCount, yPos, xPos, 1, -1);
	}
	return true;
}

//checks a diagonal word will actually fit on the grid
int diagonalValFit(int pos, int len)
{
	if (pos + len <= size) {
		return 1;
	}
	else if (int(pos - (len - 1)) >= 0) {
		return 2;
	}
	return 0;
}

//checks a word to be inserted will fit on the grid, then checks if there are any other words in its path
int validateCoords(char dir, int len, char** array, char* word, int y, int x, int n, int m)
{
	int count = 0;
	int pos = 0;
	if (dir == Y) {
		pos = y;
	}
	else {
		pos = x;
	}
	if (pos + len <= size) {
		for (int i = 0; i < len; i++) {
			count += spaceCheck(array, word, dir, y, x, i*n, i*m);
		}
		return validatePos(count, len, 1);
	}
	else if (int(pos - (len - 1)) >= 0) {
		for (int i = int(1 - len); i <= 0; i++) {
			count += spaceCheck(array, word, dir, y, x, i*n, i*m);
		}
		return validatePos(count, len, 2);
	}
	return 0;
}

//checks if there are no words in the potential path of the word to be inserted
//makes an exception if the letter of a blocking word is the same letter as the one
//to be inserted in that position (non-conflicting cross-overs)
int spaceCheck(char** array, char* word, char dir, int y, int x, int i, int j)
{
	if (array[y + i][x + j] == blank) {
		return 1;
	}
	else if (dir == Y) {
		if (array[y + i][x + j] == word[i]) {
			return 1;
		}
	}
	else if (dir == X) {
		if (array[y + i][x + j] == word[j]) {
			return 1;
		}
	}
	return 0;
}

//if the count for each letter was incremented, then it's a valid position
int validatePos(int c, int len, int val)
{
	if (c == len) {
		return val;
	}
	return 0;
}

//inserts the word in the direction decided by parameters
//adds a copy of the word to the answers array
void insertWord(int len, int val, char** array, char* word, int*** answers, int  &ansCount, int y, int x, int n, int m)
{
	for (int i = 0; i < len; i++) {
		if (val == 1) {
			array[y + (i*n)][x + (i*m)] = word[i];
			answers[ansCount][y + (i*n)][x + (i*m)] = ansCount;
		}
		else if (val == 2) {
			array[int(y - (i*n))][int(x - (i*m))] = word[i];
			answers[ansCount][int(y - (i*n))][int(x - (i*m))] = ansCount;
		}
	}
	ansCount++;
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
